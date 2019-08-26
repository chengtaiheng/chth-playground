package chth.playground.front.controller;

import chth.playground.common.DrugModel;
import chth.playground.common.Prescription;
import chth.playground.front.repository.PrescriptionRepository;
import chth.playground.front.util.ExcelUtil;
import com.github.yingzhuo.carnival.common.datamodel.DateTimeFormat;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: 程泰恒
 * @date: 2019/6/14 10:15
 */
@Slf4j
@Controller
@RequestMapping("/prescription")
public class ExcelExportController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @GetMapping("/export")
    public void ExcelExport(HttpServletRequest request, HttpServletResponse response,
                            @DateTimeFormat @RequestParam("startDate") Date startDate,
                            @DateTimeFormat @RequestParam("endDate") Date endDate) {

        List<Prescription> prescriptions = prescriptionRepository.findByCreateTimeBetween(startDate, endDate);

        final String[] titles = {"处方ID", "组织ID", "组织名称", "药房ID", "药房名称", "患者ID", "患者名称", "患者性别", "科室名称", "医生姓名", "药师姓名", "疾病代码", "备注信息", "花费", "有效天数", "处方状态", "创建时间"};

        final String[] drugTitles = {"处方ID", "药品编码", "药品名称", "药品数量", "药品单价", "药品规格", "药品剂型", "总计服药数", "用药方式", "用药频率", "频率单位", "用量", "用量单位"};


        HashMap<String, List<DrugModel>> drugListMap = new HashMap<>();

        String[][] content = null;


        if (!prescriptions.isEmpty()) {
            content = new String[prescriptions.size()][titles.length];

            System.out.println("文档为空");

            for (int i = 0; i < prescriptions.size(); i++) {
                Prescription prescription = prescriptions.get(i);
                drugListMap.put(prescription.getId(), prescription.getDrugList());

                content[i][0] = prescription.getId();
                content[i][1] = prescription.getOrganizationId();
                content[i][2] = prescription.getOrganizationName();
                content[i][3] = prescription.getPharmacyId();
                content[i][4] = prescription.getPharmacyName();
                content[i][5] = prescription.getPatientId();
                content[i][6] = prescription.getPatientName();
                content[i][7] = prescription.getPatientSex();
                content[i][8] = prescription.getDepartmentName();
                content[i][9] = prescription.getDoctorName();
                content[i][10] = prescription.getPharmacistName();
                content[i][11] = prescription.getIcdCodes().stream().collect(Collectors.joining(","));
                content[i][12] = prescription.getAdvice();
                content[i][13] = prescription.getCost().toString();
                content[i][14] = prescription.getEffectiveDays().toString();
                content[i][15] = prescription.getStatus().toString();
                content[i][16] = prescription.getCreateTime().toString();
            }
        }
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook("处方详情表", titles, content, null);

        wb = ExcelUtil.createDrugList("处方药品表", drugTitles, drugListMap, wb);

        try {
            ExcelUtil.setResponseHeader(response, "处方.xls");
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
