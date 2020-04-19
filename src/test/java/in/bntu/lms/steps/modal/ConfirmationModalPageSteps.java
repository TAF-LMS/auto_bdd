package in.bntu.lms.steps.modal;

import in.bntu.lms.pages.modal.ConfirmationModalPage;
import io.cucumber.java.en.When;

import static in.bntu.lms.steps.ElementSteps.elementSteps;

public class ConfirmationModalPageSteps {
    private final ConfirmationModalPage confirmationModalPage = new ConfirmationModalPage();

    @When("^Accept the action$")
    public void accept() {
        elementSteps()
                .click(confirmationModalPage.getAcceptButton());
    }

    @When("^Decline the action$")
    public void decline() {
        elementSteps()
                .click(confirmationModalPage.getDeclineButton());
    }

}
