package in.bntu.lms.framework.ui;

import in.bntu.lms.framework.ui.interfaces.Settable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class DropDownCheckBoxesHandler extends BaseElement implements Settable<String> {
    public DropDownCheckBoxesHandler(By locator, String name) {
        super(locator, name, ElementState.EXISTS);
    }

    @Override
    public void setValue(String value) {
        openDropDown();
        findElement().findElement(By.xpath(String.format("./..//ul/li[contains(., '%s')]//input", value))).click();
    }

    public String getSelectedText() {
        openDropDown();
        return getOptions()
                .get(0)
                .findElements(By.cssSelector(":scope > li.active"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.joining(","));
    }

    private void openDropDown() {
        if (!isDropDownOpened()) {
            findElement().click();
        }
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
