package in.bntu.lms.steps.modal;

import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.pages.modal.ConfirmationDeletedModalPage;
import in.bntu.lms.steps.BasePageSteps;
import io.cucumber.java.en.When;

public class ConfirmationUnAssignedModalPageSteps extends BasePageSteps {
    private final ConfirmationDeletedModalPage confirmationDeletedModalPage = new ConfirmationDeletedModalPage();

    @When("Accept the Deleted action")
    public void accept() {
        checkPageHasOpened()
                .click(confirmationDeletedModalPage.getAcceptButton());
    }

    @When("Decline the Deleted action")
    public void decline() {
        checkPageHasOpened()
                .click(confirmationDeletedModalPage.getDeclineButton());
    }

    @Override
    protected BasePage getPage() {
        return confirmationDeletedModalPage;
    }
}
