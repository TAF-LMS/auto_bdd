package in.bntu.lms.steps.modal;

import com.google.common.collect.ImmutableMap;
import in.bntu.lms.framework.ui.interfaces.Settable;
import in.bntu.lms.models.Laboratory;
import in.bntu.lms.pages.modal.EditLaboratoryModalPage;
import in.bntu.lms.steps.ElementSteps;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.When;

import java.util.Map;

import static in.bntu.lms.steps.ElementSteps.elementSteps;

public class EditLaboratoryModalPageSteps {
    private final EditLaboratoryModalPage editLaboratoryModalPage = new EditLaboratoryModalPage();

    @DataTableType
    public Laboratory entryLaboratory(Map<String, String> entry) {
        return new Laboratory(entry);
    }

    @When("Type laboratory information:")
    public void typeLaboratoryInfo(Laboratory laboratory) {
        checkPageHasOpened()
                .typeValues(ImmutableMap.<Settable<String>, String>builder()
                        .put(editLaboratoryModalPage.getLaboratoryNameInput(), laboratory.getName())
                        .put(editLaboratoryModalPage.getShortLaboratoryNameInput(), laboratory.getShortName())
                        .put(editLaboratoryModalPage.getOrderLaboratoryNameInput(), laboratory.getNumber())
                        .put(editLaboratoryModalPage.getDurationLaboratoryNameInput(), laboratory.getDuration())
                        .build());
    }

    @When("Save Laboratory")
    public void saveLaboratory() {
        elementSteps().click(editLaboratoryModalPage.getSaveButton());
    }

    private ElementSteps checkPageHasOpened() {
        return elementSteps()
                .checkPageIsPresent(editLaboratoryModalPage);
    }
}
