package in.bntu.lms.steps;

import in.bntu.lms.pages.SubjectManagementPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static in.bntu.lms.steps.ElementSteps.elementSteps;

public class SubjectManagementPageSteps {
    private final SubjectManagementPage subjectManagementPage = new SubjectManagementPage();

    @When("^Click the 'Add Subject' button$")
    public void clickAddSubjectButton() {
        elementSteps().click(subjectManagementPage.getAddSubjectButton());
    }

    @Then("^Check the 'Subject Management' page has opened$")
    public void checkPageHasOpened() {
        elementSteps().checkPageIsPresent(subjectManagementPage);
    }
}
