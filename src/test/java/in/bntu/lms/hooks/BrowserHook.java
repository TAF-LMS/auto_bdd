package in.bntu.lms.hooks;

import in.bntu.lms.framework.driver.WebDriverRunner;
import in.bntu.lms.util.AllureAttachmentUtils;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class BrowserHook {

    @After
    public void closeBrowser(Scenario scenario) {
        if (scenario.isFailed()) {
            AllureAttachmentUtils.attachBrowserLogs();
        }
        WebDriverRunner.close();
    }
}
