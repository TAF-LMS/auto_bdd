package in.bntu.lms.util;

import in.bntu.lms.framework.driver.WebDriverRunner;
import io.qameta.allure.Attachment;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.logging.LogType;

import javax.annotation.Nonnull;
import java.util.logging.Level;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@UtilityClass
public class AllureAttachmentUtils {

    /**
     * Attach browser logs string.
     * Use standard allure method to attach files in report
     *
     * @return the string
     */
    @Nonnull
    @Attachment(value = "Browser logs", type = "text/plain")
    @SuppressWarnings("UnusedReturnValue")
    public static String attachBrowserLogs() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            return WebDriverRunner.getBrowserLogs(LogType.BROWSER, Level.SEVERE);
        }
        return EMPTY;
    }
}
