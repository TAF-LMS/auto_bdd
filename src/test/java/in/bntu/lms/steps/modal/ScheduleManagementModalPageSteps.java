package in.bntu.lms.steps.modal;

import in.bntu.lms.pages.modal.ScheduleManagementModalPage;
import io.cucumber.java.en.When;

import static in.bntu.lms.steps.ElementSteps.elementSteps;

public class ScheduleManagementModalPageSteps {
    private final ScheduleManagementModalPage scheduleManagementModalPage = new ScheduleManagementModalPage();

    @When("Click the 'Add' date and close modal page")
    public void clickAddDateAndCloseForm() {
        elementSteps().click(scheduleManagementModalPage.getAddButton())
                .click(scheduleManagementModalPage.getCloseButton());
    }

}
