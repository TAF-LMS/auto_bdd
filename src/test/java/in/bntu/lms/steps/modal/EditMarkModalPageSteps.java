package in.bntu.lms.steps.modal;

import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.pages.modal.EditMarkModalPage;
import in.bntu.lms.steps.BasePageSteps;
import in.bntu.lms.steps.ElementSteps;
import io.cucumber.java.en.When;

import static in.bntu.lms.steps.ElementSteps.elementSteps;

public class EditMarkModalPageSteps extends BasePageSteps {
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

    @Override
    protected BasePage getPage() {
        return editMarkModalPage;
    }
}
