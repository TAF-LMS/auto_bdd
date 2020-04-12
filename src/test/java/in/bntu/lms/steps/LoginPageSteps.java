package in.bntu.lms.steps;

import in.bntu.lms.enums.AssertMessages;
import in.bntu.lms.framework.driver.WebDriverRunner;
import in.bntu.lms.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static in.bntu.lms.framework.configuration.SeleniumConfig.seleniumConfig;
import static in.bntu.lms.util.Assert.getAssert;

public class LoginPageSteps {
    private final LoginPage loginPage = new LoginPage();

    @Given("^Open 'LMS Login Page'$")
    public void openUrl() {
        WebDriverRunner.open(seleniumConfig().getUrl());
    }

    @When("^Type login = '(\\w+)' and password = '(\\w+)'$")
    public void typeLoginAdnPassword(String login, String password) {
        loginPage.getLoginInput().setValue(login);
        loginPage.getPasswordInput().setValue(password);
    }

    @When("^Click the 'Login' button$")
    public void login() {
        loginPage.getLoginButton().click();
    }

    @Then("^Check the 'LMS Login page' has opened$")
    public void checkLoginPageHasOpened() {
        getAssert().hardAssert().isEqual(loginPage.isPagePresent(), true, AssertMessages.PAGE_WAS_NOT_OPENED.getMessage(),
                "LMS Login page", seleniumConfig().getConditionTimeOut().getTimeOut().getSeconds());
    }
}
