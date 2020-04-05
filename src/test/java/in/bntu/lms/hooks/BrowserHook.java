package in.bntu.lms.hooks;

import in.bntu.lms.framework.driver.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class BrowserHook {

    @After
    public void closeBrowser(Scenario scenario) {
        WebDriverRunner.close();
    }
}
