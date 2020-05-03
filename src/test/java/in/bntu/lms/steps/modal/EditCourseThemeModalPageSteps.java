package in.bntu.lms.steps.modal;

import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.pages.modal.EditCourseThemeModalPage;
import in.bntu.lms.steps.BasePageSteps;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Set;

public class EditCourseThemeModalPageSteps extends BasePageSteps {
    private final EditCourseThemeModalPage editCourseThemeModalPage = new EditCourseThemeModalPage();

    @When("^Type '(.*)' course name and add groups:$")
    public void fillThemeFields(String name, List<String> groups) {
        checkPageHasOpened()
                .typeValue(editCourseThemeModalPage.getThemeInput(), name);
        groups.forEach(group -> checkPageHasOpened().click(editCourseThemeModalPage.getGroupOption(group)));
    }

    @When("^Type '(.*)' course name$")
    public void fillThemeFields(String name) {
        checkPageHasOpened()
                .typeValue(editCourseThemeModalPage.getThemeInput(), name);
    }

    @When("Save course theme information")
    public void save() {
        checkPageHasOpened()
                .click(editCourseThemeModalPage.getSaveButton());
    }

    @Override
    protected BasePage getPage() {
        return editCourseThemeModalPage;
    }
}
