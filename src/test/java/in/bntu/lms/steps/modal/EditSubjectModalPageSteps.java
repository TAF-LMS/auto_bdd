package in.bntu.lms.steps.modal;

import in.bntu.lms.framework.ui.interfaces.Settable;
import in.bntu.lms.models.Subject;
import in.bntu.lms.models.enums.Modules;
import in.bntu.lms.pages.modal.EditSubjectModalPage;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import static in.bntu.lms.steps.ElementSteps.elementSteps;
import static in.bntu.lms.util.Assert.getAssert;

public class EditSubjectModalPageSteps {
    private final EditSubjectModalPage editSubjectModalPage = new EditSubjectModalPage();

    @DataTableType
    public Subject subjectEntry(Map<String, String> entry) {
        return new Subject(entry);
    }

    @ParameterType(name = "module", value = "[A-Za-z0-9_]+")
    public Modules module(String value) {
        return Modules.valueOf(value);
    }

    @Then("Check the 'Edit Subject' page has opened")
    public void checkPageHasOpened() {
        elementSteps()
                .checkPageIsPresent(editSubjectModalPage);
    }

    @When("Check subject info:")
    public void checkSubjectInfo(Subject subject) {
        SortedSet<String> groups = new TreeSet<>(Arrays.asList(editSubjectModalPage.getGroupsDropDown().getSelectedText().split(",")));
        String color = elementSteps().getText(editSubjectModalPage.getSubjectColorInput());
        Set<Modules> modules = new HashSet<>();
        Arrays.stream(Modules.values())
                .forEach(module -> {
                    if (editSubjectModalPage.getModuleCheckBox(module).isValueChecked()) {
                        modules.add(module);
                    }
                });
        String subjectName = elementSteps().getText(editSubjectModalPage.getSubjectNameInput());
        String shortSubjectName = elementSteps().getText(editSubjectModalPage.getShortSubjectNameInput());
        Subject actualSubject = new Subject(subjectName, shortSubjectName, modules, color, groups);
        getAssert().softAssert().isEqual(actualSubject, subject);
    }

    @When("Type and Save the subject information:")
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

    @When("^Change the subject name to '(.*)' and short subject name to '(.*)' and color to '(.*)'$")
    public void editSubjectInfo(String subjectName, String shortSubjectName, String color) {
        elementSteps()
                .typeValue(editSubjectModalPage.getSubjectNameInput(), subjectName)
                .typeValue(editSubjectModalPage.getShortSubjectNameInput(), shortSubjectName)
                .typeValue(editSubjectModalPage.getSubjectColorInput(), color);
    }

    @When("Unchecked the module: '{module}'")
    public void editSubjectInfo(Modules modules) {
        elementSteps()
                .typeValue(editSubjectModalPage.getModuleCheckBox(modules), false);

    }

    @When("^Add the group: '(.*)'$")
    public void editSubjectInfo(String group) {
        elementSteps()
                .typeValue(editSubjectModalPage.getGroupsDropDown(), group);
    }

    @When("Save subject info")
    public void clickSaveButton() {
        elementSteps()
                .click(editSubjectModalPage.getSaveButton());
    }

}
