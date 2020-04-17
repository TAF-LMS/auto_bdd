package in.bntu.lms.steps;

import in.bntu.lms.models.ui.JoinLecturerTable;
import in.bntu.lms.pages.JoinLecturerPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static in.bntu.lms.steps.ElementSteps.elementSteps;
import static in.bntu.lms.util.Assert.getAssert;

public class JoinLecturerPageSteps {
    private final JoinLecturerPage joinLecturerPage = new JoinLecturerPage();

    @When("^Join the '(.+)' lecturer to the '(.+)' subject$")
    public void selectSubject(String lecturer, String subject) {
        checkPageHasOpened()
                .typeValue(joinLecturerPage.getSubjectSelect(), subject)
                .typeValue(joinLecturerPage.getLecturerSelect(), lecturer)
                .click(joinLecturerPage.getJoinLecturerButton());
    }

    @When("^Remove the '(.+)' lecturer$")
    public void removeLecturer(String lecturer) {
        checkPageHasOpened()
                .click(joinLecturerPage.getRemoveLecturerButton(lecturer));
    }

    @Then("^Check the lecturer table has lecturer '(.+)'$")
    public void checkHasLecturer(String lecturer) {
        checkSubjectInfo(new JoinLecturerTable(0, lecturer), true, "The lecturer was not found in the table.");
    }

    @Then("^Check the lecturer table doesn't have lecturer '(.+)'$")
    public void checkDoesntHaveLecturer(String lecturer) {
        checkSubjectInfo(new JoinLecturerTable(0, lecturer), false, "The lecturer was found in the table.");
    }

    private ElementSteps checkPageHasOpened() {
        return elementSteps().checkPageIsPresent(joinLecturerPage);
    }

    private void checkSubjectInfo(JoinLecturerTable table, boolean isPresent, String message) {
        List<JoinLecturerTable> actualLecturerTable = joinLecturerPage.getJoinLecturerTable().getModelsFromTable();
        getAssert().softAssert().isEqual(actualLecturerTable.contains(table), isPresent,
                message + "Actual list: %s", actualLecturerTable);
    }
}
