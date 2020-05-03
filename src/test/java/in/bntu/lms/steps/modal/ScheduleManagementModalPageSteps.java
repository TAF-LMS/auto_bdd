package in.bntu.lms.steps.modal;

import in.bntu.lms.enums.DatePattern;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.pages.modal.ScheduleManagementModalPage;
import in.bntu.lms.steps.BasePageSteps;
import io.cucumber.java.en.When;

import java.time.LocalDate;

import static in.bntu.lms.steps.ElementSteps.elementSteps;

public class ScheduleManagementModalPageSteps extends BasePageSteps {
    private final ScheduleManagementModalPage scheduleManagementModalPage = new ScheduleManagementModalPage();

    @When("Click the 'Add' date and close modal page")
    public void clickAddDateAndCloseForm() {
        elementSteps().click(scheduleManagementModalPage.getAddButton())
                .click(scheduleManagementModalPage.getCloseButton());
    }

    @When("Remove the now date")
    public void removeNowDate() {
        elementSteps().click(scheduleManagementModalPage.getRemoveButton(LocalDate.now().format(DatePattern.UI_FULL_DATE.getFormatter())));
    }

    @Override
    protected BasePage getPage() {
        return scheduleManagementModalPage;
    }
}
