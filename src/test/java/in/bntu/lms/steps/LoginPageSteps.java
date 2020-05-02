package in.bntu.lms.steps;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.ImmutableMap;
import in.bntu.lms.framework.driver.WebDriverRunner;
import in.bntu.lms.framework.ui.interfaces.Settable;
import in.bntu.lms.models.User;
import in.bntu.lms.pages.LoginPage;
import in.bntu.lms.util.RegexUtils;
import in.bntu.lms.util.yaml.YamlReader;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

import static in.bntu.lms.framework.configuration.SeleniumConfig.seleniumConfig;
import static in.bntu.lms.steps.ElementSteps.elementSteps;

public class LoginPageSteps {
    private final LoginPage loginPage = new LoginPage();

    @ParameterType(name = "user", value = "users::\\w+")
    public User readUserByType(String userType) {
        Map<String, User> userMap = YamlReader.readYamlConfig(
                String.format("%s/%s", seleniumConfig().getTestDataPath(), "users.yaml"),
                new TypeReference<Map<String, User>>() {});
        String type = RegexUtils.getValueByPattern(userType, "users::(\\w+)");
        return userMap.get(type);
    }

    @Given("Open 'LMS Login Page'")
    public void openUrl() {
        WebDriverRunner.open(seleniumConfig().getUrl());
    }

    @Given("^Login in 'LMS' as (users::\\w+)$")
    public void loginAdUser(User user) {
        openUrl();
        elementSteps()
                .typeValues(ImmutableMap.<Settable<String>, String>builder()
                        .put(loginPage.getLoginInput(), user.getLogin())
                        .put(loginPage.getPasswordInput(), user.getPassword())
                        .build())
                .click(loginPage.getLoginButton());
    }

    @When("^Type login = '(\\w+)' and password = '(\\w+)'$")
    public void typeLoginAdnPassword(String login, String password) {
        elementSteps()
                .typeValue(loginPage.getLoginInput(), login)
                .typeValue(loginPage.getPasswordInput(), password);
    }

    @When("Click the 'Login' button")
    public void login() {
        elementSteps()
                .click(loginPage.getLoginButton());
    }

    @Then("Check the 'LMS Login page' has opened")
    public void checkLoginPageHasOpened() {
        elementSteps().checkPageIsPresent(loginPage);
    }
}
