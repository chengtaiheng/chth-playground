package chth.playground.front.util;

import chth.playground.front.activemdb.ActiveMQUtils;

import java.util.Objects;

/**
 * @author: 程泰恒
 * @date: 2019/5/30 14:53
 */
public final class ActiveQueueUtil {

    private ActiveQueueUtil() {
        super();
    }

    public static void timingForDispodPrescription(String pid, long milliseconds) {
        Objects.requireNonNull(pid);

        ActiveMQUtils.sendStringMessage(ActiveMQUtils.PRESCRIPTION_WAITING_DELAY_QUEUE, pid.toString(), milliseconds);
    }
}
