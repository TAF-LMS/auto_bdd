package in.bntu.lms.framework.ui;

import in.bntu.lms.framework.driver.WebDriverRunner;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseElement {
    protected final By locator;
    protected final String name;

    public void setValue(String value) {
        WebElement element = WebDriverRunner.getWebDriver().findElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    public void click() {
        WebElement element = WebDriverRunner.getWebDriver().findElement(locator);
        element.click();
    }
}
