package in.bntu.lms.framework.ui;

import in.bntu.lms.framework.ui.interfaces.Settable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DropDownHandler extends BaseElement implements Settable<String> {
    public DropDownHandler(By locator, String name) {
        super(locator, name, ElementState.EXISTS);
    }

    @Override
    public void setValue(String value) {
        if (!isDropDownOpened()) {
            findElement().click();
        }
        findElement().findElement(By.xpath(String.format("./..//ul/li[contains(., '%s')]//input", value))).click();
    }

    private boolean isDropDownOpened() {
        List<WebElement> options = getOptions();
        if (options.isEmpty()) {
            return false;
        }
        return options.stream().allMatch(WebElement::isDisplayed);
    }

    private List<WebElement> getOptions() {
        return findElement().findElements(By.xpath("./..//ul"));
    }
}
