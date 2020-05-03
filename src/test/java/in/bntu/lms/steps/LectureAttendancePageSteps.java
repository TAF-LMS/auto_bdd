package in.bntu.lms.steps;

import in.bntu.lms.enums.DatePattern;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.driver.WebDriverRunner;
import in.bntu.lms.models.LectureAttendance;
import in.bntu.lms.pages.LecturesAttendancePage;
import in.bntu.lms.pages.SubjectPage;
import in.bntu.lms.util.ConditionWait;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.util.Maps;

import java.time.LocalDate;
import java.util.List;

import static in.bntu.lms.util.Assert.getAssert;

public class LectureAttendancePageSteps extends BasePageSteps {
    private final LecturesAttendancePage lecturesAttendancePage = new SubjectPage().getLecturesAttendancePage();

    @When("^Select the '(.*)' group$")
    public void clickAddLectureButton(String group) {
        checkPageHasOpened()
                .typeValue(lecturesAttendancePage.getGroupSelect(), group);
    }

    @When("Click the 'Schedule Management' button")
    public void clickLectureAttendanceButton() {
        checkPageHasOpened().click(lecturesAttendancePage.getScheduleManagementButton());
    }

    @When("Click the 'Show Attendance' button")
    public void clickAddAndRemoveScheduleButton() {
        checkPageHasOpened()
                .click(lecturesAttendancePage.getShowAttendanceButton());
    }

    @When("Add '{int}' h. for the '{int}' student")
    public void clickEditLectureButton(int hour, int studentNumber) {
        checkPageHasOpened();
        String nowDate = LocalDate.now().format(DatePattern.UI_FULL_DATE.getFormatter());
        lecturesAttendancePage.getLectureAttendanceTable().setValue(studentNumber - 1, Maps.newHashMap(nowDate, String.valueOf(hour)));
    }

    @When("Save Lecture Attendance changes")
    public void saveLectureAttendance() {
        checkPageHasOpened()
                .click(lecturesAttendancePage.getSaveAttendanceButton());
    }

    @When("Remove all dates")
    public void removeAllDates() {
        checkPageHasOpened().click(lecturesAttendancePage.getRemoveAllButton());
    }

    @Then("Check that '{int}' student has '{int}' h. on today")
    public void checkHoursInDateForStudent(int studentNumber, int hour) {
        String date = LocalDate.now().format(DatePattern.UI_FULL_DATE.getFormatter());
        LectureAttendance actualLectureAttendance = lecturesAttendancePage.getLectureAttendanceTable().getModelsFromTable().get(studentNumber - 1);
        getAssert().softAssert().isEqual(Integer.parseInt(actualLectureAttendance.getHours().get(date)), hour);
    }

    @Then("Check that NowDate was added on table")
    public void checkNewDateIsPresent() {
        String date = LocalDate.now().format(DatePattern.UI_FULL_DATE.getFormatter());
        ConditionWait.waitForTrue(driver -> {
            WebDriverRunner.refreshPage();
            new LecturesPageSteps().clickLectureAttendanceButton();
            return lecturesAttendancePage.getLectureAttendanceTable().getModelsFromTable().get(0).getHours().containsKey(date);
        });

        List<LectureAttendance> actualLectureAttendance = lecturesAttendancePage.getLectureAttendanceTable().getModelsFromTable();
        getAssert().softAssert().isEqual(actualLectureAttendance.stream().allMatch(lectureAttendance -> lectureAttendance.getHours().containsKey(date)),
                true,
                "Date %s was not added", date);
    }

    @Then("Check that all dates were deleted from the table")
    public void checkAllDatesIsAbsent() {
        ConditionWait.waitForTrue(driver -> {
            WebDriverRunner.refreshPage();
            new LecturesPageSteps().clickLectureAttendanceButton();
            return lecturesAttendancePage.getLectureAttendanceTable().getModelsFromTable().get(0).getHours().size() == 1;
        });

        List<LectureAttendance> actualLectureAttendance = lecturesAttendancePage.getLectureAttendanceTable().getModelsFromTable();
        actualLectureAttendance.forEach(lectureAttendance -> lectureAttendance.getHours().remove("№"));
        getAssert().softAssert().isEqual(actualLectureAttendance.stream().allMatch(lectureAttendance -> lectureAttendance.getHours().isEmpty()),
                true,
                "All date was not deleted");
    }

    @Override
    protected BasePage getPage() {
        return lecturesAttendancePage;
    }
}
