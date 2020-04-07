package in.bntu.lms.steps;

import in.bntu.lms.enums.AssertMessages;
import in.bntu.lms.pages.LmsMainPage;
import io.cucumber.java.en.Then;

import static in.bntu.lms.framework.configuration.SeleniumConfig.getConfig;
import static org.assertj.core.api.Assertions.assertThat;

public class LmsMainPageSteps {
    private final LmsMainPage lmsMainPage = new LmsMainPage();

    @Then("^Check the 'Lms Main Page' has opened$")
    public void checkLmsMainPageHasOpened() {
        assertThat(lmsMainPage.isPagePresent())
                .overridingErrorMessage(AssertMessages.PAGE_WAS_NOT_OPENED.getMessage(),
                "LMS Main Page", getConfig().getConditionTimeOut().getTimeOut().toString())
                .isEqualTo(true);
    }
}
