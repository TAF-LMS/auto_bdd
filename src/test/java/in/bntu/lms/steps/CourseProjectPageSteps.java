package in.bntu.lms.steps;

import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.models.CourseProject;
import in.bntu.lms.pages.CourseProjectPage;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.When;

import java.util.Map;

import static in.bntu.lms.steps.ElementSteps.elementSteps;

public class CourseProjectPageSteps extends BasePageSteps {
    private final CourseProjectPage courseProjectPage = new CourseProjectPage();

    @DataTableType
    public CourseProject entry(Map<String, String> entry) {
        return new CourseProject(entry);
    }

    @When("Click the 'Course themes' button")
    public void clickTheCourseThemeButton() {
        checkPageHasOpened()
                .click(courseProjectPage.getCourseThemeSideBarButton());
    }

    @When("Click the 'Course Percent Schedule' button")
    public void clickTheCoursePercentScheduleButton() {
        checkPageHasOpened()
                .click(courseProjectPage.getCoursePercentScheduleSideBarButton());
    }

    @When("^Click the 'Edit button' for the '(.*)' course theme$")
    public void editTheme(String name) {
        elementSteps()
                .click(courseProjectPage.getEditThemeButton(name));
    }

    @Override
    protected BasePage getPage() {
        return courseProjectPage;
    }
}
