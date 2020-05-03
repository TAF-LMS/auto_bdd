package in.bntu.lms.steps;

import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.pages.FilesPage;
import io.cucumber.java.en.Then;

import java.util.Collections;

public class FilesPageSteps extends BasePageSteps {
    private final FilesPage filesPage = new FilesPage();

    @Then("^Check file '(.*)' is present in the files page$")
    public void checkFileIsPresent(String name) {
        checkPageHasOpened()
                .checkElementsArePresent(Collections.singletonList(filesPage.getFileByName(name)));
    }

    @Override
    protected BasePage getPage() {
        return filesPage;
    }
}
