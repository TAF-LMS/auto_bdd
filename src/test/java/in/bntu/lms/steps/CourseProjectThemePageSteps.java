package in.bntu.lms.steps;

import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.driver.WebDriverRunner;
import in.bntu.lms.models.CourseProject;
import in.bntu.lms.pages.CourseProjectThemePage;
import in.bntu.lms.util.ConditionWait;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static in.bntu.lms.util.Assert.getAssert;

public class CourseProjectThemePageSteps extends BasePageSteps {
    private final CourseProjectThemePage courseProjectThemePage = new CourseProjectThemePage();

    @When("Click the 'Add new course theme' button")
    public void addNewTheme() {
        checkPageHasOpened().click(courseProjectThemePage.getEditProjectButton());
    }

    @When("^Click the 'Assign group' button for course theme '(.*)'$")
    public void clickAssignGroup(String name) {
        checkPageHasOpened().click(courseProjectThemePage.getAssignGroupButton(name));
    }

    @Then("Check Course Project Theme table has course project:")
    public void checkCourseProjectInfoIsPresent(CourseProject courseProject) {
        checkPageHasOpened();
        checkCourseProjectInfo(courseProject, true, "The CourseProject was not found in the table.");
    }

    @Then("Check Course Project Theme table has course project for current date:")
    public void checkCourseProjectInfoIsPresentForCurrentDate(CourseProject courseProject) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        courseProject.setDate(date);
        checkPageHasOpened();
        checkCourseProjectInfo(courseProject, true, "The CourseProject was not found in the table.");
    }

    private void checkCourseProjectInfo(CourseProject table, boolean isPresent, String message) {
        ConditionWait.waitForTrue(driver -> {
            WebDriverRunner.refreshPage();
            return courseProjectThemePage.getCourseProjectTable().getModelsFromTable().contains(table) == isPresent;
        });
        List<CourseProject> actualSubjects = courseProjectThemePage.getCourseProjectTable().getModelsFromTable();
        getAssert().softAssert().isEqual(actualSubjects.contains(table), isPresent,
                message + "Actual list: %s", actualSubjects);
    }

    @Override
    protected BasePage getPage() {
        return courseProjectThemePage;
    }
}
