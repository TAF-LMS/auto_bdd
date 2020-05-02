package in.bntu.lms.framework.ui.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * The annotation saves table attributes.
 * Use the annotation for mapping ui table to models.
 * title - header column name
 * attrName - element attribute name (For example: class, id)
 * attrValue - element attribute value
 * attrName and attrValue are not required (use to clarify row/column element details)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableMap {
    String title();
    boolean isMap() default false;
    String attrName() default EMPTY;
    String attrValue() default EMPTY;
}
