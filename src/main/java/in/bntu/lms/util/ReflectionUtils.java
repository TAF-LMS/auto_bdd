package in.bntu.lms.util;

import in.bntu.lms.framework.uiparser.StringToTypeParser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectionUtils {

    public static <T> T createInstance(Class<T> refClass) {
        return createInstance(refClass, Collections.emptyMap());
    }

    public static <T> T createInstance(Class<T> refClass, Map<Class<?>, ?> args) {
        try {
            if (args.isEmpty()) {
                return refClass.getConstructor().newInstance();
            } else {
                Class<?>[] constructorsArgs = args.keySet().stream().map(cls -> (Class<?>) cls).toArray(Class[]::new);
                return refClass.getConstructor(constructorsArgs).newInstance(args.values().toArray());
            }
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

    public static <T> void putValueInMapField(T obj, Field field, String key, String value) throws IllegalAccessException {
        if (value == null) {
            return;
        }
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        Map<Object, Object> oldValue = (Map<Object, Object>) field.get(obj);
        if (oldValue == null) {
            oldValue = new HashMap<>();
        }
        oldValue.put(key, value);
        field.set(obj, oldValue);
    }
}
