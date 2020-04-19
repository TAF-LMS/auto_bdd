package in.bntu.lms.steps;

import in.bntu.lms.pages.SubjectPage;
import io.cucumber.java.en.When;

import static in.bntu.lms.steps.ElementSteps.elementSteps;

public class SubjectPageSteps {
    private final SubjectPage subjectPage = new SubjectPage();

    @When("^Click the 'News' button$")
    public void clickTheNewsButton() {
        elementSteps()
                .click(subjectPage.getNewsSideBarButton());
    }

    @When("^Click the 'Lectures' button$")
    public void clickTheLecturersButton() {
        elementSteps()
                .click(subjectPage.getLecturesSideBarButton());
    }
}
