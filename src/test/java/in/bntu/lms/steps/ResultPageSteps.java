package in.bntu.lms.steps;

import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.driver.WebDriverRunner;
import in.bntu.lms.pages.ResultPage;
import in.bntu.lms.pages.SubjectPage;
import in.bntu.lms.util.Assert;
import in.bntu.lms.util.ConditionWait;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ResultPageSteps extends BasePageSteps {
    private final ResultPage resultPage = new SubjectPage().getLaboratoryPage().getResultPage();

    @When("^Open edit mark for '(\\d)' student in the '(.*)' laboratory$")
    public void openMarkInput(int studentNumber, String laboratoryName) {
        checkPageHasOpened()
                .doubleClick(resultPage.getLaboratoryResultTable().getColumnByColumnNameAndRowNumber(studentNumber - 1, laboratoryName));

    }

    @Then("^Check mark = '(.*)' for '(\\d)' student in the '(.*)' laboratory$")
    public void checkMarkValue(String value, int studentNumber, String labName) {
        checkPageHasOpened();
        ConditionWait.waitForTrue(driver -> {
                    WebDriverRunner.refreshPage();
                    new LaboratoryPageSteps().clickResultButton();
                    return resultPage.getLaboratoryResultTable().getColumnByColumnNameAndRowNumber(studentNumber - 1, labName).getText().contains(value);
                }
        );
        Assert.getAssert().softAssert().isEqual(
                resultPage.getLaboratoryResultTable().getColumnByColumnNameAndRowNumber(studentNumber - 1, labName).getText(),
                value);
    }

    @Override
    protected BasePage getPage() {
        return resultPage;
    }
}
