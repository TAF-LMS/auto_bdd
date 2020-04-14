package in.bntu.lms.framework.ui.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EnumType {
    Class<? extends Enum<? extends ElementEnum>> enumType();
}
