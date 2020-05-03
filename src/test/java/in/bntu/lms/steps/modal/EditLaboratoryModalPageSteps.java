package in.bntu.lms.steps.modal;

import com.google.common.collect.ImmutableMap;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.interfaces.Settable;
import in.bntu.lms.models.Laboratory;
import in.bntu.lms.pages.modal.EditLaboratoryModalPage;
import in.bntu.lms.steps.BasePageSteps;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.When;

import java.util.Map;

import static in.bntu.lms.steps.ElementSteps.elementSteps;

public class EditLaboratoryModalPageSteps extends BasePageSteps {
    private final EditLaboratoryModalPage editLaboratoryModalPage = new EditLaboratoryModalPage();

    @DataTableType
    public Laboratory entryLaboratory(Map<String, String> entry) {
        return new Laboratory(entry);
    }

    @When("^Upload the file '(.*)' for laboratory$")
    public void uploadFile(String fileName) {
        checkPageHasOpened()
                .uploadFile(editLaboratoryModalPage.getUploadFileInput(), fileName);
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

    @Override
    protected BasePage getPage() {
        return editLaboratoryModalPage;
    }
}
