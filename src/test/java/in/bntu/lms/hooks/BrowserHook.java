package in.bntu.lms.hooks;

import in.bntu.lms.framework.driver.WebDriverRunner;
import in.bntu.lms.util.AllureAttachmentUtils;
import in.bntu.lms.util.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class BrowserHook {

    /**
     * The hook closes browser and removes all asserts and adds browser logs (scenario is fail) after scenario
     *
     * @param scenario the scenario
     */
    @After
    public void closeBrowser(Scenario scenario) {
        Assert.clear();
        if (scenario.isFailed()) {
            AllureAttachmentUtils.attachBrowserLogs();
        }
        WebDriverRunner.close();
    }
}
