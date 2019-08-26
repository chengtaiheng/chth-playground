package chth.playground.front;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

import javax.jms.ConnectionFactory;

@Slf4j
@Configuration
public class ApplicationJmsCnf {

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory, DefaultJmsErrorHandler defaultJmsErrorHandler) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setErrorHandler(defaultJmsErrorHandler);
        return factory;
    }

    @Slf4j
    @Component
    private static class DefaultJmsErrorHandler implements ErrorHandler {

        @Override
        public void handleError(Throwable throwable) {
            log.error("JMS 处理信息的时候发生异常");
            log.error(throwable.getMessage(), throwable);
        }
    }
}
