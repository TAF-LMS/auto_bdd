package in.bntu.lms.steps.modal;

import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.models.News;
import in.bntu.lms.pages.modal.EditNewsModalPage;
import in.bntu.lms.steps.BasePageSteps;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.When;

import java.util.Map;

public class EditNewsModalPageSteps extends BasePageSteps {
    private final EditNewsModalPage editNewsModalPage = new EditNewsModalPage();

    @DataTableType
    public News newsEntry(Map<String, String> table) {
        return new News(table);
    }

    @When("Type and save news information:")
    public void typeNewsInfoAndSave(News news) {
        checkPageHasOpened()
                .typeValue(editNewsModalPage.getNewsTitleInput(), news.getName())
                .typeValue(editNewsModalPage.getNewsContentTextArea(), news.getText())
                .click(editNewsModalPage.getSaveNewsButton());
    }

    @Override
    protected BasePage getPage() {
        return editNewsModalPage;
    }

}
