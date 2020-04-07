package in.bntu.lms.hooks;

import in.bntu.lms.framework.driver.WebDriverRunner;
import in.bntu.lms.util.AllureAttachmentUtils;
import in.bntu.lms.util.Assert;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;

public class BrowserHook {

    @AfterStep
    public void afterStep() {
        if (!Assert.getTHREAD_LOCAL().wasSuccess()) {
            AllureAttachmentUtils.attachScreenshot();
            AllureAttachmentUtils.attachBrowserLogs();
        }
    }

    @After
    public void closeBrowser(Scenario scenario) {
        if (!scenario.getStatus().equals(Status.PASSED)) {
            AllureAttachmentUtils.attachScreenshot();
        }
        WebDriverRunner.close();
    }
}
