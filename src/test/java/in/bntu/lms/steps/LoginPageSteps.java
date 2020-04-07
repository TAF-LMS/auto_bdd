package in.bntu.lms.steps;

import in.bntu.lms.framework.driver.WebDriverRunner;
import in.bntu.lms.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static in.bntu.lms.framework.configuration.SeleniumConfig.getConfig;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageSteps {
    private final LoginPage loginPage = new LoginPage();

    @Given("^Open 'LMS Login Page'$")
    public void openUrl() {
        WebDriverRunner.open(getConfig().getUrl());
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
        assertThat(loginPage.isPagePresent())
                .overridingErrorMessage("LMS Login page has not opened with timeOut: {}",
                        getConfig().getConditionTimeOut().getTimeOut().toString())
                .isEqualTo(true);
    }
}
