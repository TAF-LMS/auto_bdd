package in.bntu.lms.framework.ui.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableMap {
    String title() default EMPTY;
    String attrName() default EMPTY;
    String attrValue() default EMPTY;
}
