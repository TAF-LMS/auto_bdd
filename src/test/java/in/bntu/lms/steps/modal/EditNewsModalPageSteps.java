package in.bntu.lms.steps.modal;

import in.bntu.lms.models.News;
import in.bntu.lms.pages.modal.EditNewsModalPage;
import in.bntu.lms.steps.ElementSteps;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.When;

import java.util.Map;

import static in.bntu.lms.steps.ElementSteps.elementSteps;

public class EditNewsModalPageSteps {
    private final EditNewsModalPage editNewsModalPage = new EditNewsModalPage();

    @DataTableType
    public News newsEntry(Map<String, String> table) {
        return new News(table);
    }

    @When("^Type and save news information:$")
    public void typeNewsInfoAndSave(News news) {
        checkPageHasOpened()
                .typeValue(editNewsModalPage.getNewsTitleInput(), news.getName())
                .typeValue(editNewsModalPage.getNewsContentTextArea(), news.getText())
                .click(editNewsModalPage.getSaveNewsButton());
    }

    private ElementSteps checkPageHasOpened() {
        return elementSteps().checkPageIsPresent(editNewsModalPage);
    }
}
