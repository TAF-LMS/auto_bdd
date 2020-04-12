package in.bntu.lms.framework.ui;

import in.bntu.lms.framework.ui.interfaces.Settable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ElementHandler extends BaseElement implements Settable<String> {
    public ElementHandler(By locator, String name) {
        super(locator, name, ElementState.EXISTS);
    }

    @Override
    public void setValue(String value) {
        WebElement element = findElement();
        element.clear();
        element.sendKeys(value);
    }
}
