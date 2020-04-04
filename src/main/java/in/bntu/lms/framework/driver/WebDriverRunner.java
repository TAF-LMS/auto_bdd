package in.bntu.lms.framework.driver;

import in.bntu.lms.framework.browser.Browsers;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class WebDriverRunner {
    private static final ThreadLocal<WebDriver> WEB_DRIVER_CONTAINER = new ThreadLocal<>();

    public static WebDriver getWebDriver() {
        if (WEB_DRIVER_CONTAINER.get() != null) {
            return WEB_DRIVER_CONTAINER.get();
        }
        throw new IllegalStateException("WebDriver has not started");
    }

    public static void open(String url) {
        synchronized (WebDriverRunner.class) {
            WebDriver webDriver = Browsers.getFactory().getWebDriver();
            WEB_DRIVER_CONTAINER.set(webDriver);
        }
        WEB_DRIVER_CONTAINER.get().get(url);
    }
}
