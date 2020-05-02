package in.bntu.lms.steps.modal;

import in.bntu.lms.pages.modal.EditMarkModalPage;
import in.bntu.lms.steps.ElementSteps;
import io.cucumber.java.en.When;

import static in.bntu.lms.steps.ElementSteps.elementSteps;

public class EditMarkModalPageSteps {
    private final EditMarkModalPage editMarkModalPage = new EditMarkModalPage();

    @When("^Type mark value '(.*)'$")
    public void setValue(String value) {
        checkPageHasOpened()
                .typeValue(editMarkModalPage.getMarkInput(), value);
    }

    @When("^Save mark$")
    public void save() {
        checkPageHasOpened()
                .click(editMarkModalPage.getSaveButton());
    }

    private ElementSteps checkPageHasOpened() {
        return elementSteps().checkPageIsPresent(editMarkModalPage);
    }

}
