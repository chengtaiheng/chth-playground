package chth.playground.front.activemdb;

import chth.playground.common.Prescription;
import chth.playground.front.dao.PrescriptionDao;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

/**
 * @author: 程泰恒
 * @date: 2019/5/30 10:44
 */
@Slf4j
@Component
public class PrescriptionDisposeMdb {

    @Autowired
    private PrescriptionDao prescriptionDao;

    @JmsListener(destination = ActiveMQUtils.PRESCRIPTION_WAITING_DELAY_QUEUE)
    public void onMessage(TextMessage textMessage) throws Exception {


        log.debug("开始接受消息");
        val text = textMessage.getText();
        log.debug("id: {}", text);

        Prescription prescription = prescriptionDao.findById(text);
        prescriptionDao.savePrescriptionBySql(prescription);

        log.debug("任务处理完成");

    }


}
