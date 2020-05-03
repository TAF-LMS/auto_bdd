package in.bntu.lms.steps;

import in.bntu.lms.framework.base.BasePage;

import static in.bntu.lms.steps.ElementSteps.elementSteps;

public abstract class BasePageSteps {
    protected abstract BasePage getPage();

    protected ElementSteps checkPageHasOpened() {
        return elementSteps()
                .checkPageIsPresent(getPage());
    }
}
