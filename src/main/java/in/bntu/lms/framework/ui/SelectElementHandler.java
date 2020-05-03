package in.bntu.lms.framework.ui;

import in.bntu.lms.framework.ui.interfaces.Settable;
import in.bntu.lms.util.ConditionWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectElementHandler extends BaseElement implements Settable<String> {
    public SelectElementHandler(By locator, String name) {
        super(locator, name, ElementState.EXISTS);
    }

    @Override
    public void setValue(String value) {
        ConditionWait.waitForTrue(driver -> {
            click();
            Select select = new Select(findElement());
            select.selectByVisibleText(value);
            String value1 = findElement().getAttribute("value");
            String selectedValue = select.getOptions()
                    .stream()
                    .filter(el -> el.getAttribute("value").equalsIgnoreCase(value1))
                    .map(WebElement::getText)
                    .findFirst()
                    .orElse("");
            return selectedValue.equalsIgnoreCase(value);
        });
    }

    public List<WebElement> getOptions() {
        return new Select(findElement()).getOptions();
    }
}
