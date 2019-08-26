package chth.playground.front.activemdb;

import com.github.yingzhuo.carnival.spring.SpringUtils;
import org.apache.activemq.ScheduledMessage;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author: 程泰恒
 * @date: 2019/5/30 9:47
 */
public class ActiveMQUtils {

    //处方等待处理延迟队列
    public static final String PRESCRIPTION_WAITING_DELAY_QUEUE = "PLAGROUND.prescription..witing.dispose.queue";

    //构造方法私有化
    private ActiveMQUtils() {
        super();
    }

    /**
     * 发送文本消息（使用默认交换机）（不延迟）
     */
    public static void sendStringMessage(String queueName, String messageContent) {
        sendStringMessage(queueName, messageContent, -1);
    }

    public static void sendStringMessage(String queueName, String messageContent, long delayInMillisends) {
        JmsTemplate bean = SpringUtils.getBean(JmsTemplate.class);

        if (delayInMillisends >= 0) {
            bean.send(queueName, session -> {
                javax.jms.Message message = session.createTextMessage(messageContent);
                message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delayInMillisends);
                return message;
            });
        } else {
            bean.send(queueName, session -> session.createTextMessage(messageContent));
        }
    }
}
