package in.bntu.lms.framework.ui;

import in.bntu.lms.framework.driver.WebDriverRunner;
import in.bntu.lms.framework.ui.interfaces.Clickable;
import in.bntu.lms.framework.ui.interfaces.Visible;
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
import java.util.stream.Collectors;

import static in.bntu.lms.framework.configuration.SeleniumConfig.seleniumConfig;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseElement implements Visible, Clickable {
    protected final By locator;
    protected final String name;
    protected final ElementState state;

    @Override
    public void click() {
        WebElement element = findElement();
        ConditionWait.waitForTrue(() -> element.isDisplayed() && element.isEnabled());
        findElement().click();
    }

    public String getText() {
        String value = findElement().getAttribute("value");
        return value == null || value.isEmpty() ? findElement().getText() : value;
    }

    @Override
    public boolean isExists(Duration duration) {
        return !findElements(duration, ElementState.EXISTS).isEmpty();
    }

    @Override
    public boolean isAbsent(Duration duration) {
        return !findElements(duration, ElementState.HIDDEN).isEmpty();
    }

    @Override
    public boolean isPresent(Duration duration) {
        return !findElements(duration, ElementState.VISIBLE).isEmpty();
    }

    @Override
    public String toString() {
        return String.format("Element['%s'] with locator: '%s'", this.name, this.locator.toString());
    }

    protected List<WebElement> findElements(Duration duration, ElementState state) {
        return findElements(duration, locator, state);
    }

    protected WebElement findElement() {
        return findElement(seleniumConfig().getConditionTimeOut().getTimeOut(), locator, state);
    }

    protected WebElement findElement(Duration duration, By locator,  ElementState state) {
        List<WebElement> elements = findElements(duration, locator, state);
        if (elements.isEmpty()) {
            throw new NoSuchElementException(logElementNotFound(state.name()));
        }
        return elements.get(0);
    }

    protected List<WebElement> findElements(Duration duration, By locator, ElementState state) {
        WebDriver driver = WebDriverRunner.getWebDriver();
        List<WebElement> elementsResult = new ArrayList<>();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        ConditionWait.waitForTrue(() -> tryToFindElements(driver, locator, elementsResult, state), duration);
        return elementsResult;
    }

    protected boolean tryToFindElements(WebDriver webDriver, By locator, List<WebElement> resultElements, ElementState state) {
        List<WebElement> elements = webDriver.findElements(locator);
        if (elements.isEmpty()) {
            return false;
        }
        resultElements.addAll(elements.stream().filter(state.getPredicate()).collect(Collectors.toList()));
        return true;
    }

    protected String logElementNotFound(String state) {
        return String.format("Element not found {%s}.\nExpected: %s", locator.toString(), state);
    }
}
