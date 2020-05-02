package in.bntu.lms.steps;

import in.bntu.lms.enums.AssertMessages;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.driver.WebDriverRunner;
import in.bntu.lms.framework.ui.BaseElement;
import in.bntu.lms.framework.ui.ElementHandler;
import in.bntu.lms.framework.ui.interfaces.Clickable;
import in.bntu.lms.framework.ui.interfaces.Settable;
import in.bntu.lms.framework.ui.interfaces.Visible;
import in.bntu.lms.util.ResourcesUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Map;

import static in.bntu.lms.framework.configuration.SeleniumConfig.seleniumConfig;
import static in.bntu.lms.util.Assert.getAssert;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ElementSteps {
    private static ElementSteps instance;

    public static ElementSteps elementSteps() {
        if (instance == null) {
            instance = new ElementSteps();
        }
        return instance;
    }

    public ElementSteps checkElementsArePresent(Iterable<Visible> elements) {
        elements.forEach(element -> getAssert().softAssert().isEqual(element.isPresent(), true, "Element '%s' is absent", element.toString()));
        return this;
    }

    public ElementSteps uploadFile(ElementHandler elementHandler, String fileName) {
        elementHandler.uploadFile(ResourcesUtils.getResourceFile(seleniumConfig().getTestDataPath(), fileName).getAbsolutePath());
        return this;
    }

    public <T> ElementSteps typeValue(Settable<T> element, T value) {
        element.setValue(value);
        return this;
    }

    public <T> ElementSteps typeValues(Map<Settable<T>, T> valuesMap) {
        valuesMap.forEach(Settable::setValue);
        return this;
    }

    public ElementSteps click(Clickable clickable) {
        clickable.click();
        return this;
    }

    public ElementSteps doubleClick(WebElement webElement) {
        new Actions(WebDriverRunner.getWebDriver()).doubleClick(webElement).perform();
        return this;
    }

    public ElementSteps checkPageIsPresent(BasePage page) {
        getAssert().hardAssert().isEqual(page.isPagePresent(), true,
                AssertMessages.PAGE_WAS_NOT_OPENED.getMessage(), page, seleniumConfig().getConditionTimeOut().getTimeOut().getSeconds());
        return this;
    }

    public String getText(BaseElement element) {
        return element.isPresent() ? element.getText() : "";
    }
}
