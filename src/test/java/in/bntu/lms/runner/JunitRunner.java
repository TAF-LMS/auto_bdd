package in.bntu.lms.runner;

import in.bntu.lms.extensions.ScreenshotExtension;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm",
                "pretty",
                "json:build/reports/cucumber-report/report.json"
        },
        features = "src/test/java/in/bntu/lms",
        glue = {
                "in.bntu.lms.steps",
                "in.bntu.lms.hooks"
        })
public class JunitRunner {
    @RegisterExtension
    static final ScreenshotExtension SCREENSHOT_EXTENSION = new ScreenshotExtension();
}
