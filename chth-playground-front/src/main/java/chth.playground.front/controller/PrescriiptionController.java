package chth.playground.front.controller;

import chth.playground.common.ComplexModel;
import chth.playground.common.Prescription;
import chth.playground.common.form.PrescriptionForm;
import chth.playground.front.dao.DrugDao;
import chth.playground.front.dao.PrescriptionDao;
import chth.playground.front.repository.PrescriptionRepository;
import chth.playground.front.security.RequiersAdmin;
import chth.playground.front.util.ActiveQueueUtil;
import com.github.yingzhuo.carnival.common.datamodel.DateTimeFormat;
import com.github.yingzhuo.carnival.id.util.IdGeneratorUtils;
import com.github.yingzhuo.carnival.json.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author: 程泰恒
 * @date: 2019/5/23 13:53
 */

@Slf4j
@Validated
@RestController
@RequestMapping("/prescription")
public class PrescriiptionController {

    @Autowired
    private PrescriptionDao prescriptionDao;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private DrugDao drugDao;

    @PostMapping
    public Json savePrescription(@RequestBody PrescriptionForm form) {
        log.debug("form: {}", form);

        prescriptionDao.savePrescription(form);

        return Json.newInstance();
    }

    @GetMapping
    public Json findById(@NotBlank @RequestParam("Did") String Did) {
        log.debug("Did：{}", Did);

        Optional<Prescription> prescription = prescriptionRepository.findById(Did);
//        Prescription prescription = prescriptionDao.findById(Did);

        log.debug("prescription:{}", prescription);

        return Json.newInstance()
                .payload("result", prescription);
    }

    @GetMapping("/all")
    public Json findAll() {
        List<Prescription> list = prescriptionDao.findList();
        return Json.newInstance()
                .payload("list", list);
    }

    @PostMapping("/complex")
    @RequiersAdmin
    public Json saveComplexModel(@RequestBody ComplexModel complexModel) {
        log.debug("complexModel: {}", complexModel);

        String id = IdGeneratorUtils.nextId();

        log.debug("id: {}", id);

        complexModel.getBody().setId(id);

        complexModel.getBody().getDrugList().forEach(drugModel -> {
            drugModel.setId(IdGeneratorUtils.nextId());
            drugModel.setPId(id);
        });

        prescriptionDao.saveComplexModel(complexModel);

        prescriptionDao.saveIcdCodes(id, complexModel.getBody().getIcdCodes());

        drugDao.saveDrugList(id, complexModel.getBody().getDrugList());


        ActiveQueueUtil.timingForDispodPrescription(id, 30 * 1000);


        return Json.newInstance();
    }

    @GetMapping("/time-and-status")
    public Json findByCreateTime(@RequestParam("startTime") @DateTimeFormat Date startTime, @RequestParam("endDate") @DateTimeFormat Date endTime, @RequestParam("status") Integer status) {

        log.debug("startTime: {}", startTime);
        log.debug("endTime : {}", endTime);

        List<Prescription> prescriptions = prescriptionRepository.findByCreateTimeBetweenAndStatus(startTime, endTime, status);


        return Json.newInstance()
                .payload("result", prescriptions);
    }

    @GetMapping("/time")
    public Json findByCreateTime(@RequestParam("startDate") @DateTimeFormat Date startDate, @RequestParam("endDate") @DateTimeFormat Date endDate) {
        log.debug("startDate: {}", startDate);
        log.debug("endDate: {}", endDate);

        List<Prescription> prescriptions = prescriptionRepository.findByCreateTimeBetween(startDate, endDate);

        return Json.newInstance()
                .payload("list", prescriptions);

    }

    @GetMapping("/status")
    public Json findByStatus(@RequestParam("status") Integer status) {
        log.debug("status: {}", status);

        List<Prescription> list = prescriptionRepository.findByStatus(status);

        return Json.newInstance()
                .payload("list", list);
    }

    @GetMapping("/sql")
    public Json getByPid(@RequestParam("pid") String pid) {
        log.debug("pid: /n/n{}", pid);

        Prescription prescription = prescriptionDao.findById(pid);
        prescription.setIcdCodes(prescriptionDao.findIcdCodesByPid(pid));
        prescription.setDrugList(drugDao.findByPrescriptionId(pid));

        return Json.newInstance()
                .payload("result", prescription);
    }

}
