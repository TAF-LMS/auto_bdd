package in.bntu.lms.steps;

import in.bntu.lms.util.Assert;
import io.cucumber.java.en.Then;

public class CommonSteps {

    @Then("Assert all")
    public void assertAll() {
        Assert.getAssert().softAssert().assertAll();
    }
}
