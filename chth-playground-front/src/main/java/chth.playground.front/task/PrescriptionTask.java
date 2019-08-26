package chth.playground.front.task;

import chth.playground.common.Prescription;
import chth.playground.front.dao.PrescriptionDao;
import chth.playground.front.repository.PrescriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author: 程泰恒
 * @date: 2019/5/31 11:49
 */

@Slf4j
@Component
public class PrescriptionTask {

    @Autowired
    private PrescriptionDao prescriptionDao;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Scheduled(fixedRate = 1000 * 30)
    public void task() {


        Date date1 = DateUtils.truncate(DateUtils.addDays(new Date(), +1), Calendar.DAY_OF_MONTH);
        Date date2 = DateUtils.truncate(DateUtils.addDays(new Date(), 0), Calendar.DAY_OF_MONTH);

        log.debug("date1: {}", date1);
        log.debug("date2: {}", date2);


        List<Prescription> list = prescriptionRepository.findByCreateTimeBetween(date2, date1);

        log.debug("前一天产生的处方数是： {}", list.size());
    }

}
