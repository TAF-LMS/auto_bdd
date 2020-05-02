package in.bntu.lms.framework.ui;

import in.bntu.lms.framework.ui.interfaces.Settable;
import org.openqa.selenium.By;

public class CheckBoxHandler extends BaseElement implements Settable<Boolean> {
    public CheckBoxHandler(By locator, String name) {
        super(locator, name, ElementState.EXISTS);
    }

    @Override
    public void setValue(Boolean value) {
        if (value != isValueChecked()) {
            findElement().findElement(By.xpath("./..")).findElement(By.tagName("label")).click();
        }
    }

    public boolean isValueChecked() {
        return findElement().isSelected();
    }
}
