package in.bntu.lms.steps;

import in.bntu.lms.framework.driver.WebDriverRunner;
import in.bntu.lms.models.ui.SubjectTable;
import in.bntu.lms.pages.SubjectManagementPage;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static in.bntu.lms.steps.ElementSteps.elementSteps;
import static in.bntu.lms.util.Assert.getAssert;

public class SubjectManagementPageSteps {
    private final SubjectManagementPage subjectManagementPage = new SubjectManagementPage();

    @DataTableType
    public SubjectTable subjectEntry(Map<String, String> entry) {
        return new SubjectTable(entry);
    }

    @When("^Click the 'Add Subject' button$")
    public void clickAddSubjectButton() {
        elementSteps().click(subjectManagementPage.getAddSubjectButton());
    }

    @When("^Click the 'Edit subject' button for the subject = '(\\w+)'$")
    public void clickEditSubjectButton(String subjectName) {
        elementSteps()
                .click(subjectManagementPage.getEditSubjectButton(subjectName));
    }

    @Then("^Check subject table has subject:$")
    public void checkSubjectInfo(SubjectTable subjectTable) {
        checkSubjectInfo(subjectTable, true, "The Subject was not found in the table.");
    }

    @Then("^Check subject table doesn't have the subject:$")
    public void checkSubjectInfoNotPresent(SubjectTable subjectTable) {
        checkSubjectInfo(subjectTable, false, "The Subject was found in the table.");
    }

    @Then("^Check the 'Subject Management' page has opened$")
    public void checkPageHasOpened() {
        elementSteps().checkPageIsPresent(subjectManagementPage);
    }

    private void checkSubjectInfo(SubjectTable table, boolean isPresent, String message) {
        WebDriverRunner.refreshPage();
        List<SubjectTable> actualSubjects = subjectManagementPage.getSubjects().getModelsFromTable();
        getAssert().softAssert().isEqual(actualSubjects.contains(table), isPresent,
                message + "Actual list: %s", actualSubjects);
    }
}
