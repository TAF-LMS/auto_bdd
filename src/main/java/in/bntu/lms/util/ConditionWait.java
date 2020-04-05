package in.bntu.lms.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.awaitility.core.ConditionTimeoutException;
import org.hamcrest.Matcher;

import java.time.Duration;
import java.util.concurrent.Callable;

import static in.bntu.lms.framework.configuration.SeleniumConfig.getConfig;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.core.IsEqual.equalTo;

@UtilityClass
@Slf4j
public class ConditionWait {

    public static boolean waitForTrue(Callable<Boolean> condition) {
        return waitForTrue(condition, getConfig().getConditionTimeOut().getTimeOut());
    }

    public static boolean waitForTrue(Callable<Boolean> condition, Duration timeOut) {
        return until(condition, equalTo(true), timeOut, getConfig().getPollingTimeOut().getTimeOut(), false);
    }

    private static <T> T until(Callable<T> condition, Matcher<? super T> matcher, Duration timeOut, Duration polling, T failedValue) {
        try {
            return await().pollInterval(polling)
                    .atMost(timeOut)
                    .ignoreExceptions()
                    .until(condition, matcher);
        } catch (ConditionTimeoutException ex) {
            log.warn("ConditionTimeoutException: {}", ex.getMessage());
            return failedValue;
        }
    }
}
