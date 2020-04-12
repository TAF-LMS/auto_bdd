package in.bntu.lms.steps;

import in.bntu.lms.enums.AssertMessages;
import in.bntu.lms.pages.LmsMainPage;
import io.cucumber.java.en.Then;

import static in.bntu.lms.framework.configuration.SeleniumConfig.seleniumConfig;
import static in.bntu.lms.util.Assert.getAssert;

public class LmsMainPageSteps {
    private final LmsMainPage lmsMainPage = new LmsMainPage();

    @Then("^Check the 'Lms Main Page' has opened$")
    public void checkLmsMainPageHasOpened() {
        getAssert().hardAssert().isEqual(lmsMainPage.isPagePresent(), true, AssertMessages.PAGE_WAS_NOT_OPENED.getMessage(),
                "LMS Main Page", seleniumConfig().getConditionTimeOut().getTimeOut().getSeconds());
    }
}
