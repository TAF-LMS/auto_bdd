package in.bntu.lms.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface PageInfo {
    String id() default "";
    String xpath() default "";
    String css() default "";
    String pageName();
}
