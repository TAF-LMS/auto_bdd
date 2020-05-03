package in.bntu.lms.steps;

import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.driver.WebDriverRunner;
import in.bntu.lms.models.Lecture;
import in.bntu.lms.pages.LecturesPage;
import in.bntu.lms.pages.SubjectPage;
import in.bntu.lms.util.ConditionWait;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static in.bntu.lms.steps.ElementSteps.elementSteps;
import static in.bntu.lms.util.Assert.getAssert;

public class LecturesPageSteps extends BasePageSteps {
    private final LecturesPage lecturesPage = new SubjectPage().getLecturersPage();

    @When("Click the 'Add Lecture' button")
    public void clickAddLectureButton() {
        checkPageHasOpened().click(lecturesPage.getAddLectureButton());
    }

    @When("Click the 'Lecture Attendance' button")
    public void clickLectureAttendanceButton() {
        checkPageHasOpened().click(lecturesPage.getLectureAttendanceTabButton());
    }

    @When("^Remove the '(.+)' lecture$")
    public void clickRemoveLectureButton(String lecture) {
        checkPageHasOpened()
                .click(lecturesPage.getRemoveLectureButton(lecture));
    }

    @When("^Edit the '(.+)' lecture$")
    public void clickEditLectureButton(String lecture) {
        checkPageHasOpened()
                .click(lecturesPage.getEditLectureButton(lecture));
    }

    @Then("Check lecture table has lecture:")
    public void checkLectureInfo(Lecture lecture) {
        checkSubjectInfo(lecture, true, "The Lecture was not found in the table.");
    }

    @Then("Check lecture table doesn't have lecture:")
    public void checkLectureInfoNotPresent(Lecture lecture) {
        checkSubjectInfo(lecture, false, "The lecture was found in the table.");
    }

    private void checkSubjectInfo(Lecture table, boolean isPresent, String message) {
        ConditionWait.waitForTrue(driver -> {
            WebDriverRunner.refreshPage();
            return lecturesPage.getLecturersTable().getModelsFromTable().contains(table) == isPresent;
        });
        List<Lecture> actualSubjects = lecturesPage.getLecturersTable().getModelsFromTable();
        getAssert().softAssert().isEqual(actualSubjects.contains(table), isPresent,
                message + "Actual list: %s", actualSubjects);
    }

    @Override
    protected BasePage getPage() {
        return lecturesPage;
    }
}
