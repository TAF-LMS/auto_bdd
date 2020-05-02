package in.bntu.lms.framework.browser;

import org.openqa.selenium.WebDriver;

/**
 * The interface Browser factory.
 * The all browser factories should implements this interface
 */
public interface IBrowserFactory {
    WebDriver getWebDriver();
}
