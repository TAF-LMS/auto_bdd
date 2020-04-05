package in.bntu.lms.steps;

import in.bntu.lms.pages.LmsMainPage;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class LmsMainPageSteps {
    private final LmsMainPage lmsMainPage = new LmsMainPage();

    @Then("^Check the 'Lms Main Page' had opened$")
    public void checkLmsMainPageHasOpened() {
        Assert.assertTrue("Lms page had not opened", lmsMainPage.isPagePresent());
    }
}
