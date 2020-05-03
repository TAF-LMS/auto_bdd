package in.bntu.lms.steps;

import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.driver.WebDriverRunner;
import in.bntu.lms.models.CoursePercentage;
import in.bntu.lms.models.ui.SubjectTable;
import in.bntu.lms.pages.CoursePercentSchedulePage;
import in.bntu.lms.util.ConditionWait;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static in.bntu.lms.util.Assert.getAssert;

public class CoursePercentSchedulePageSteps extends BasePageSteps {
    private final CoursePercentSchedulePage coursePercentSchedulePage = new CoursePercentSchedulePage();

    @When("Click the 'Add New Percentage' button")
    public void clickAddNewPercentage() {
        checkPageHasOpened()
                .click(coursePercentSchedulePage.getAddNewPercentageButton());
    }

    @When("^Click the 'Edit' button for '(.*)' percentage$")
    public void clickEditButtonForPercentage(String name) {
        checkPageHasOpened()
                .click(coursePercentSchedulePage.getEditCoursePercentageButton(name));
    }

    @When("^Click the 'Remove' button for '(.*)' percentage$")
    public void clickRemoveButtonForPercentage(String name) {
        checkPageHasOpened()
                .click(coursePercentSchedulePage.getDeleteCoursePercentageButton(name));
    }

    @Then("Check Course Percentage table doesn't have CoursePercentage:")
    public void checkCoursePercentageIsAbsent(CoursePercentage expectedCoursePercentage) {
        checkPageHasOpened();
        checkCoursePercentageInfo(expectedCoursePercentage, false, "The CoursePercentage was found in the table.");
    }

    @Then("Check Course Percentage table has CoursePercentage:")
    public void checkCoursePercentageIsPresent(CoursePercentage expectedCoursePercentage) {
        checkPageHasOpened();
        checkCoursePercentageInfo(expectedCoursePercentage, true, "The CoursePercentage was not found in the table.");
    }

    private void checkCoursePercentageInfo(CoursePercentage table, boolean isPresent, String message) {
        ConditionWait.waitForTrue(driver -> {
            WebDriverRunner.refreshPage();
            return coursePercentSchedulePage.getCoursePercentScheduleTable().getModelsFromTable().contains(table) == isPresent;
        });
        List<CoursePercentage> actualCoursesPercentage = coursePercentSchedulePage.getCoursePercentScheduleTable().getModelsFromTable();
        getAssert().softAssert().isEqual(actualCoursesPercentage.contains(table), isPresent,
                message + "Actual list: %s", actualCoursesPercentage);
    }

    @Override
    protected BasePage getPage() {
        return coursePercentSchedulePage;
    }
}
