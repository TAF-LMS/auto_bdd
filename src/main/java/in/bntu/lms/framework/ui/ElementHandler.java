package in.bntu.lms.framework.ui;

import org.openqa.selenium.By;

public class ElementHandler extends BaseElement {
    public ElementHandler(By locator, String name) {
        super(locator, name);
    }

    @Override
    public String toString() {
        return String.format("ElementHandler['%s'] with locator: '%s'", this.name, this.locator.toString());
    }
}
