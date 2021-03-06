package in.bntu.lms.framework.driver;

import in.bntu.lms.framework.browser.Browsers;
import in.bntu.lms.util.FileUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static in.bntu.lms.framework.configuration.SeleniumConfig.seleniumConfig;
import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * The static class works with WebDriver.
 * WebDriver is thread safe value (ThreadLocal). One thread = one WebDriver
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class WebDriverRunner {
    private static final ThreadLocal<WebDriver> WEB_DRIVER_CONTAINER = new ThreadLocal<>();

    public static WebDriver getWebDriver() {
        if (WEB_DRIVER_CONTAINER.get() != null) {
            return WEB_DRIVER_CONTAINER.get();
        }
        throw new IllegalStateException(logBrowserHasNotStarted());
    }

    public static void setWebDriver(WebDriver webDriver) {
        WEB_DRIVER_CONTAINER.set(webDriver);
    }

    public static void open(String url) {
        synchronized (WebDriverRunner.class) {
            WebDriver webDriver = Browsers.getFactory().getWebDriver();
            WEB_DRIVER_CONTAINER.set(webDriver);
        }
        getWebDriver().get(url);
    }

    public static void close() {
        if (hasWebDriverStarted()) {
            getWebDriver().close();
        }
    }

    public static boolean hasWebDriverStarted() {
        return getWebDriver() != null && ((RemoteWebDriver) getWebDriver()).getSessionId() != null;
    }

    public static void refreshPage() {
        getWebDriver().navigate().refresh();
    }

    public static Object executeScript(WebElement element, JavaScript script, Object... args) {
        String js = FileUtils.readFile(Paths.get(JavaScript.MAIN_RESOURCES, script.getFileName()));
        return ((JavascriptExecutor) getWebDriver()).executeScript(js, element, args);
    }

    public static String getBrowserLogs(String logType, Level logLevel) {
        if (hasWebDriverStarted()) {
            Logs logs = getWebDriver().manage().logs();
            if (logs.getAvailableLogTypes().contains(logType)) {
                return logs.get(logType)
                        .getAll()
                        .stream()
                        .filter(logEntry -> logEntry.getLevel().equals(logLevel))
                        .map(LogEntry::toString)
                        .collect(Collectors.joining("\n"));
            }
            log.error("Browser profile doesn't support the LogType: {}", logType);
            return EMPTY;
        }
        log.error(logBrowserHasNotStarted());
        return EMPTY;
    }

    private static String logBrowserHasNotStarted() {
        return String.format("WebDriver has not started for browser: %s", seleniumConfig().getBrowser());
    }
}
