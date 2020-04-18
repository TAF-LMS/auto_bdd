package in.bntu.lms.steps;

import in.bntu.lms.framework.driver.WebDriverRunner;
import in.bntu.lms.models.News;
import in.bntu.lms.pages.NewsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Collections;
import java.util.List;

import static in.bntu.lms.steps.ElementSteps.elementSteps;
import static in.bntu.lms.util.Assert.getAssert;

public class NewsPageSteps {
    private final NewsPage newsPage = new NewsPage();

    @When("^Click the 'Add News' button$")
    public void clickAddNews() {
        checkPageHasOpened()
                .click(newsPage.getAddNewsButton());
    }

    @When("^Delete the '(.+)' news")
    public void clickRemoveNews(String news) {
        checkPageHasOpened()
                .click(newsPage.getRemoveNewsButton(news));
    }

    @When("^Edit the '(.+)' news")
    public void clickEditNews(String news) {
        checkPageHasOpened()
                .click(newsPage.getEditNewsButton(news));
    }

    @When("^Click the 'Show All News' button$")
    public void showAllNews() {
        checkPageHasOpened()
                .click(newsPage.getShowAllNewsButton());
    }

    @When("^Click the 'Hide All News' button$")
    public void hideAllNews() {
        checkPageHasOpened()
                .click(newsPage.getHideAllNewsButton());
    }

    @Then("^Check the information message: '(.+)' has displayed$")
    public void checkInformationMessage(String message) {
        checkPageHasOpened()
                .checkElementsArePresent(Collections.singleton(newsPage.getInformationMessageLabel(message)));
    }

    @Then("^Check news table has the news:$")
    public void checkNewsInfo(News news) {
        checkSubjectInfo(news, true, "The news was not found in the table.");
    }

    @Then("^Check news table doesn't have the news:$")
    public void checkNewsInfoNotPresent(News news) {
        checkSubjectInfo(news, false, "The news was found in the table.");
    }

    private ElementSteps checkPageHasOpened() {
        return elementSteps().checkPageIsPresent(newsPage);
    }

    private void checkSubjectInfo(News expectedNews, boolean isPresent, String message) {
        WebDriverRunner.refreshPage();
        List<News> actualNews = newsPage.getNewsElementsContainer().mapToObjects();
        getAssert().softAssert().isEqual(actualNews.contains(expectedNews), isPresent,
                message + "Actual list: %s", actualNews);
    }
}
