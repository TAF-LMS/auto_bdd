package in.bntu.lms.steps;

import in.bntu.lms.pages.FilesPage;
import io.cucumber.java.en.Then;

import java.util.Collections;

import static in.bntu.lms.steps.ElementSteps.elementSteps;

public class FilesPageSteps {
    private final FilesPage filesPage = new FilesPage();

    @Then("^Check file '(.*)' is present in the files page$")
    public void checkFileIsPresent(String name) {
        checkPageHasOpened()
                .checkElementsArePresent(Collections.singletonList(filesPage.getFileByName(name)));
    }

    private ElementSteps checkPageHasOpened() {
        return elementSteps()
                .checkPageIsPresent(filesPage);
    }
}
