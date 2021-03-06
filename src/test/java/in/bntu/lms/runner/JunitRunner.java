package in.bntu.lms.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm",
                "pretty",
                "json:build/reports/cucumber-report/report.json"
        },
        features = "src/test/java/in/bntu/lms",
        strict = true,
        glue = {
                "in.bntu.lms.steps",
                "in.bntu.lms.hooks"
        })
public class JunitRunner {
}
