package in.bntu.lms.util;

import in.bntu.lms.framework.uiparser.StringToTypeParser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectionUtils {

    public static <T> T createInstance(Class<T> refClass) {
        try {
            return refClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            String message = "ReflectionsUtils. Create instance exception";
            log.error(message, e);
            throw new IllegalArgumentException(message, e);
        }
    }

    public static <T> void writeField(T obj, Field field, String value, StringToTypeParser parser) throws IllegalAccessException {
        if (value == null) {
            return;
        }
        field.setAccessible(true);
        field.set(obj, parser.parse(value, field.getType()));
    }
}
