package chth.playground.front.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.Random;

/**
 * @author: 程泰恒
 * @date: 2019/5/9 15:30
 */
@Slf4j
//@Component
public class MessageTask {

    public static final String[] MESSAGE_ARRAY = {"亲,活动活动", "亲，喝口水", "亲,该动一动了"};
    public static final String MESSAGE_EAT = "亲，该吃饭了，吃了饭再回来继续努力";
    public static final String MESSAGE_OFF_DUTY = "亲，夜里风霜冷，请穿紧外套，回家休息，明天继续加油";


    @Scheduled(fixedRate = 15 * 60 * 1000L)
    public void excute() {
        //休息小提示
        Random random = new Random();
        log.debug("*******小姐姐温馨提示********");
        int i = random.nextInt(MESSAGE_ARRAY.length);
        log.debug(MESSAGE_ARRAY[i]);

        Date date = new Date(System.currentTimeMillis());
        int hours = date.getHours();
        int minutes = date.getMinutes();

        if (ifBetween(hours, 16, 18)) {
            if (ifBetween(minutes, 0, 30)) {
                log.debug(MESSAGE_EAT);
            }

        }
        if (ifBetween(hours, 19, 21)) {
            log.debug(MESSAGE_OFF_DUTY);
        }

    }

    public boolean ifBetween(Integer cardinal, Integer start, Integer end) {
        boolean flag = false;

        if (cardinal > start && cardinal < end) {
            flag = true;
        }
        return flag;
    }
}
