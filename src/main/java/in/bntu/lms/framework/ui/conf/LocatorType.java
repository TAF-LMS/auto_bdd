package in.bntu.lms.framework.ui.conf;

import in.bntu.lms.util.ReflectionUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.assertj.core.util.Maps;
import org.openqa.selenium.By;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum LocatorType {
    XPATH(By.ByXPath.class),
    CSS(By.ByCssSelector.class),
    CLASS_NAME(By.ByClassName.class),
    ID(By.ById.class),
    TAG_NAME(By.ByTagName.class);

    private final Class<? extends By> locatorTypeValue;

    public By getLocator(String locator) {
        return ReflectionUtils.createInstance(this.locatorTypeValue, Maps.newHashMap(String.class, locator));
    }
}
