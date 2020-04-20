package in.bntu.lms.util;

import in.bntu.lms.framework.driver.WebDriverRunner;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.awaitility.core.ConditionTimeoutException;
import org.hamcrest.Matcher;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

import static in.bntu.lms.framework.configuration.SeleniumConfig.seleniumConfig;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * The type Condition wait.
 * !Important: callable conditions invokes in the different thread.
 */
@UtilityClass
@Slf4j
public class ConditionWait {

    public static boolean waitForTrue(Predicate<WebDriver> condition) {
        WebDriver webDriver = WebDriverRunner.getWebDriver();
        return waitForTrue(() -> {
                    WebDriverRunner.setWebDriver(webDriver);
                    return condition.test(webDriver);
                },
                seleniumConfig().getConditionTimeOut().getTimeOut());
    }

    public static boolean waitForTrue(Callable<Boolean> condition) {
        return waitForTrue(condition, seleniumConfig().getConditionTimeOut().getTimeOut());
    }

    public static boolean waitForTrue(Callable<Boolean> condition, Duration timeOut) {
        return until(condition, equalTo(true), timeOut, seleniumConfig().getPollingTimeOut().getTimeOut(), false);
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
