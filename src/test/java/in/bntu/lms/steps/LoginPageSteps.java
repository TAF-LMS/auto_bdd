package in.bntu.lms.steps;

import in.bntu.lms.framework.driver.WebDriverRunner;
import in.bntu.lms.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static in.bntu.lms.framework.configuration.SeleniumConfig.getConfig;

public class LoginPageSteps {
    private final LoginPage loginPage = new LoginPage();

    @Given("^Open 'LMS Login Page'$")
    public void openUrl() {
        WebDriverRunner.open(getConfig().getUrl());
    }

    @When("^Type login = '(\\w+)' and password = '(\\w+)'$")
    public void typeLoginAdnPassword(String login, String password) {
        Assert.assertTrue(loginPage.isPagePresent());
        loginPage.getLoginInput().setValue(login);
        loginPage.getPasswordInput().setValue(password);
    }

    @When("^Click the 'Login' button$")
    public void login() {
        Assert.assertTrue(loginPage.isPagePresent());
        loginPage.getLoginButton().click();
    }

    @Then("^Check page has opened$")
    public void checkPageHasOpened() {
        // Implementation step
    }
}
