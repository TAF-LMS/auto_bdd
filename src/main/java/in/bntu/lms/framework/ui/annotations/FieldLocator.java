package in.bntu.lms.framework.ui.annotations;

import in.bntu.lms.framework.ui.conf.LocatorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldLocator {
    LocatorType locatorType();
    String locator();
}
