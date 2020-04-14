package in.bntu.lms.steps;

import in.bntu.lms.framework.ui.interfaces.Settable;
import in.bntu.lms.models.Subject;
import in.bntu.lms.pages.modal.EditSubjectModalPage;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

import static in.bntu.lms.steps.ElementSteps.elementSteps;

public class EditSubjectModalPageSteps {
    private final EditSubjectModalPage editSubjectModalPage = new EditSubjectModalPage();

    @DataTableType
    public Subject subjectEntry(Map<String, String> entry) {
        return new Subject(entry);
    }

    @Then("^Check the 'Edit Subject' page has opened$")
    public void checkPageHasOpened() {
        elementSteps()
                .checkPageIsPresent(editSubjectModalPage);
    }

    @When("^Type and Save the subject information:$")
    public void typeAndSaveSubjectInfo(Subject subject) {
        Map<Settable<String>, String> valuesStringMap = new HashMap<>();
        Map<Settable<Boolean>, Boolean> valuesBoolMap = new HashMap<>();
        valuesStringMap.put(editSubjectModalPage.getSubjectNameInput(), subject.getSubjectName());
        valuesStringMap.put(editSubjectModalPage.getShortSubjectNameInput(), subject.getShortSubjectName());
        valuesStringMap.put(editSubjectModalPage.getSubjectColorInput(), subject.getHex());
        subject.getGroups().forEach(group -> valuesStringMap.put(editSubjectModalPage.getGroupsDropDown(), group));
        subject.getModules().forEach(module -> valuesBoolMap.put(editSubjectModalPage.getModuleCheckBox(module), true));
        elementSteps()
                .typeValues(valuesStringMap)
                .typeValues(valuesBoolMap)
                .click(editSubjectModalPage.getSaveButton());
    }

}
