package chth.playground.front.activemdb;

import com.github.yingzhuo.carnival.exception.business.BusinessException;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.nio.charset.StandardCharsets;

/**
 * @author: 程泰恒
 * @date: 2019/5/30 9:24
 */
public abstract class AbstractMd {
    protected final String getStringContext(TextMessage message) {
        try {
            return new String(message.getText().getBytes(), StandardCharsets.UTF_8);
        } catch (JMSException e) {

        }
        throw BusinessException.of("000001.1");
    }
}
