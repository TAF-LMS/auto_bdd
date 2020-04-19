package in.bntu.lms.steps;

import in.bntu.lms.pages.LmsMainPage;
import in.bntu.lms.pages.SubjectsForm;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static in.bntu.lms.steps.ElementSteps.elementSteps;

public class LmsMainPageSteps {
    private final LmsMainPage lmsMainPage = new LmsMainPage();

    @When("^Click the 'Subjects' button$")
    public void openSubjectsForm() {
        elementSteps()
                .click(lmsMainPage.getSubjectSideBarButton());
    }

    @When("^Click the 'Subject management' button$")
    public void clickTheSubjectManagementButton() {
        elementSteps()
                .click(lmsMainPage.getSubjectsForm().getSubjectManagementButton());
    }

    @When("^Open the '(.+)' subject$")
    public void openSubject(String subject) {
        elementSteps()
                .click(lmsMainPage.getSubjectsForm().getSubjectButton(subject));
    }

    @When("^Click the 'Lecturers' button$")
    public void clickTheLecturersButton() {
        elementSteps()
                .click(lmsMainPage.getSubjectsForm().getLecturersButton());
    }

    @Then("^Check the 'Subject Form' has opened$")
    public void checkSubjectFormHasOpened() {
        SubjectsForm subjectsForm = lmsMainPage.getSubjectsForm();
        elementSteps()
                .checkPageIsPresent(subjectsForm)
                .checkElementsArePresent(subjectsForm.getRequiredFormElement());
    }

    @Then("^Check the 'Lms Main Page' has opened$")
    public void checkLmsMainPageHasOpened() {
        elementSteps()
                .checkPageIsPresent(lmsMainPage);
    }
}
