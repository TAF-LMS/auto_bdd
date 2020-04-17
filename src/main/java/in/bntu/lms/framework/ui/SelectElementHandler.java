package in.bntu.lms.framework.ui;

import in.bntu.lms.framework.ui.interfaces.Settable;
import in.bntu.lms.util.ConditionWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SelectElementHandler extends BaseElement implements Settable<String> {
    public SelectElementHandler(By locator, String name) {
        super(locator, name, ElementState.EXISTS);
    }

    @Override
    public void setValue(String value) {
        click();
        waitLoadOptions();
        new Select(findElement()).selectByVisibleText(value);
        click();
    }

    protected void waitLoadOptions() {
        AtomicInteger size = new AtomicInteger(getOptions().size());
        AtomicInteger count = new AtomicInteger(2);
        ConditionWait.waitForTrue(() -> {
            if (size.get() == getOptions().size()) {
                count.getAndDecrement();
            }
            if (count.get() == 0) {
                return true;
            }
            size.set(getOptions().size());
            return false;
        });
    }

    public List<WebElement> getOptions() {
        return new Select(findElement()).getOptions();
    }

}
