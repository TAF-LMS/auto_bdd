package in.bntu.lms.framework.browser.chrome;

import in.bntu.lms.framework.browser.IBrowserFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeBrowserFactory implements IBrowserFactory {

    @Override
    public WebDriver getWebDriver() {
        ChromeProfile chromeProfile = ChromeProfile.downloadProfileFromYaml();
        ChromeBrowser browser = new ChromeBrowser(chromeProfile);
        return chromeProfile.isRemote() ? browser.getRemoteWebDriver() : browser.getLocalWebDriver();

    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class ChromeBrowser {
        private final ChromeProfile profile;

        private WebDriver getRemoteWebDriver() {
            return new RemoteWebDriver(profile.getRemote(), profile.chromeOptions());
        }

        private WebDriver getLocalWebDriver() {
            WebDriverManager.chromedriver().version(profile.getVersion());
            return new ChromeDriver(profile.chromeOptions());
        }
    }
}
