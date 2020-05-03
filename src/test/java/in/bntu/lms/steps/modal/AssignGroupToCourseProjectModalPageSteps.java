package in.bntu.lms.steps.modal;

import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.pages.modal.AssignGroupToCourseProjectModalPage;
import in.bntu.lms.steps.BasePageSteps;
import io.cucumber.java.en.When;

public class AssignGroupToCourseProjectModalPageSteps extends BasePageSteps {
    private final AssignGroupToCourseProjectModalPage assignGroupToCourseProjectModalPage = new AssignGroupToCourseProjectModalPage();

    @When("^Assign '(.*)' for course project$")
    public void assignStudent(String name) {
        checkPageHasOpened()
                .click(assignGroupToCourseProjectModalPage.getAssignButton(name));
    }

    @Override
    protected BasePage getPage() {
        return assignGroupToCourseProjectModalPage;
    }
}
