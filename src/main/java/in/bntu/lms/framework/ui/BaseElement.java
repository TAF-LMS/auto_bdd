package in.bntu.lms.framework.ui;

import in.bntu.lms.framework.driver.WebDriverRunner;
import in.bntu.lms.util.ConditionWait;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static in.bntu.lms.framework.configuration.SeleniumConfig.getConfig;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseElement {
    protected final By locator;
    protected final String name;

    public void setValue(String value) {
        WebElement element = findElement();
        element.clear();
        element.sendKeys(value);
    }

    public void click() {
        findElement().click();
    }

    public boolean isPresent() {
        return isPresent(getConfig().getConditionTimeOut().getTimeOut());
    }

    public boolean isPresent(Duration duration) {
        return !findElements(duration).isEmpty();
    }

    private WebElement findElement() {
        return findElement(getConfig().getConditionTimeOut().getTimeOut());
    }

    private WebElement findElement(Duration duration) {
        List<WebElement> elements = findElements(duration);
        if (elements.isEmpty()) {
            throw new NoSuchElementException(logElementNotFound("exists"));
        }
        return elements.get(0);
    }

    private List<WebElement> findElements(Duration duration) {
        WebDriver driver = WebDriverRunner.getWebDriver();
        List<WebElement> elementsResult = new ArrayList<>();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        ConditionWait.waitForTrue(() -> tryToFindElements(driver, elementsResult), duration);
        return elementsResult;
    }

    private boolean tryToFindElements(WebDriver webDriver, List<WebElement> resultElements) {
        List<WebElement> elements = webDriver.findElements(locator);
        if (elements.isEmpty()){
            return false;
        }
        resultElements.addAll(elements);
        return true;
    }

    private String logElementNotFound(String state) {
        return String.format("Element not found {%s}.\nExpected: %s" ,locator.toString(), state);
    }
}
