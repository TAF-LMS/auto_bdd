package in.bntu.lms.steps;

import in.bntu.lms.framework.driver.WebDriverRunner;
import in.bntu.lms.models.Laboratory;
import in.bntu.lms.pages.LaboratoryPage;
import in.bntu.lms.pages.SubjectPage;
import in.bntu.lms.util.ConditionWait;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static in.bntu.lms.steps.ElementSteps.elementSteps;
import static in.bntu.lms.util.Assert.getAssert;

public class LaboratoryPageSteps {
    private final LaboratoryPage laboratoryPage = new SubjectPage().getLaboratoryPage();

    @When("Click the 'Add new Laboratory' button")
    public void clickAddLaboratory() {
        checkPageHasOpened()
                .click(laboratoryPage.getLaboratoryButton());
    }

    @When("^Remove the '(.+)' laboratory$")
    public void clickRemoveLaboratoryButton(String laboratory) {
        checkPageHasOpened()
                .click(laboratoryPage.getRemoveLaboratoryButton(laboratory));
    }

    @When("^Edit the '(.+)' laboratory$")
    public void clickEditLaboratoryButton(String laboratory) {
        checkPageHasOpened()
                .click(laboratoryPage.getEditLaboratoryButton(laboratory));
    }

    @Then("Check laboratory table doesn't have the laboratory:")
    public void checkSubjectInfoNotPresent(Laboratory laboratory) {
        checkLaboratoryInfo(laboratory, false, "The Laboratory was found in the table.");
    }

    @Then("Check laboratory table has the laboratory:")
    public void checkSubjectInfoPresent(Laboratory laboratory) {
        checkLaboratoryInfo(laboratory, true, "The Laboratory was not found in the table.");
    }


    private void checkLaboratoryInfo(Laboratory table, boolean isPresent, String message) {
        ConditionWait.waitForTrue(driver -> {
            WebDriverRunner.refreshPage();
            return laboratoryPage.getLaboratoryTable().getModelsFromTable().contains(table) == isPresent;
        });
        List<Laboratory> actualLaboratories = laboratoryPage.getLaboratoryTable().getModelsFromTable();
        getAssert().softAssert().isEqual(actualLaboratories.contains(table), isPresent,
                message + "Actual list: %s", actualLaboratories);
    }

    private ElementSteps checkPageHasOpened() {
        return elementSteps().checkPageIsPresent(laboratoryPage);
    }

}
