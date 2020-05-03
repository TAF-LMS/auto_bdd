package in.bntu.lms.steps.modal;

import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.pages.modal.ConfirmationModalPage;
import in.bntu.lms.steps.BasePageSteps;
import io.cucumber.java.en.When;

import static in.bntu.lms.steps.ElementSteps.elementSteps;

public class ConfirmationModalPageSteps extends BasePageSteps {
    private final ConfirmationModalPage confirmationModalPage = new ConfirmationModalPage();

    @When("Accept the action")
    public void accept() {
        elementSteps()
                .click(confirmationModalPage.getAcceptButton());
    }

    @When("Decline the action")
    public void decline() {
        elementSteps()
                .click(confirmationModalPage.getDeclineButton());
    }

    @Override
    protected BasePage getPage() {
        return confirmationModalPage;
    }
}
