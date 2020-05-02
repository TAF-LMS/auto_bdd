package in.bntu.lms.framework.ui.annotations;

import in.bntu.lms.framework.ui.conf.LocatorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation saves element attributes.
 * Use the annotation for mapping ui element to models.
 * locatorType - locator type (XPATH, CSS, XPATH, CSS, CLASS_NAME, ID, TAG_NAME
 * locator - locator value
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldLocator {
    LocatorType locatorType();
    String locator();
}
