package in.bntu.lms.framework.ui.annotations;

import in.bntu.lms.framework.ui.interfaces.ElementEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation uses to add custom convert enum type for class variable
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EnumType {
    Class<? extends Enum<? extends ElementEnum>> enumType();
}
