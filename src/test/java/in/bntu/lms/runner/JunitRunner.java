package in.bntu.lms.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/in/bntu/lms/feature",
        glue = "in.bntu.lms.steps",
        snippets = CucumberOptions.SnippetType.UNDERSCORE)
public class JunitRunner {
}
