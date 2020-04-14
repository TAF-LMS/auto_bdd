package in.bntu.lms.framework.uiparser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.format.DateTimeFormatter;

@Slf4j
@AllArgsConstructor
public class DateTypeParser<T> implements TypeParser<T> {
    @Getter
    private final DateTimeFormatter formatter;
    private final Class<T> parseClass;

    @Override
    @SuppressWarnings("unchecked")
    public T parse(String value) {
        Method method = getParseMethod();
        if (method != null && !value.isEmpty()) {
            try {
                return (T) method.invoke(parseClass, value, formatter);
            } catch (IllegalAccessException | InvocationTargetException e) {
                String message = String.format("Run %s exception.", method.getName());
                log.error(message, e);
                throw new IllegalArgumentException(message, e);
            }
        }
        return null;
    }

    private Method getParseMethod() {
        Method method;
        try {
            method = parseClass.getDeclaredMethod("parse", CharSequence.class, DateTimeFormatter.class);
            method.setAccessible(true);
            if (Modifier.isStatic(method.getModifiers())) {
                return method;
            }
        } catch (NoSuchMethodException e) {
            String message = "There is no registered 'TypeParser' for that type, or that type does not contain one of the following static factory method: " +
                    "parse(CharSequence text, DateTimeFormatter formatter)";
            log.error(message, e);
            throw new IllegalArgumentException(message, e);
        }
        return null;
    }
}
