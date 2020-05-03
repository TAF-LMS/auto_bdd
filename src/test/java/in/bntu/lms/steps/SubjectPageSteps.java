package in.bntu.lms.steps;

import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.pages.SubjectPage;
import io.cucumber.java.en.When;

import static in.bntu.lms.steps.ElementSteps.elementSteps;

public class SubjectPageSteps extends BasePageSteps {
    private final SubjectPage subjectPage = new SubjectPage();

    @When("Click the 'News' button")
    public void clickTheNewsButton() {
        elementSteps()
                .click(subjectPage.getNewsSideBarButton());
    }

    @When("Click the 'Lectures' button")
    public void clickTheLecturersButton() {
        elementSteps()
                .click(subjectPage.getLecturesSideBarButton());
    }

    @When("Click the 'Laboratory' button")
    public void clickTheLaboratoryButton() {
        elementSteps()
                .click(subjectPage.getLaboratorySideBarButton());
    }

    @When("Click the 'Files' button")
    public void clickTheFilesButton() {
        elementSteps()
                .click(subjectPage.getFilesSideBarButton());
    }

    @When("Click the 'Course Project' button")
    public void clickTheCourseButton() {
        elementSteps()
                .click(subjectPage.getCourseProjectSideBarButton());
    }

    @Override
    protected BasePage getPage() {
        return subjectPage;
    }
}
