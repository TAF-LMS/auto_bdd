package in.bntu.lms.framework.uiparser;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class StringToTypeParser {
    private static final Object STATIC_METHOD = null;
    private final Map<Class<?>, TypeParser<?>> typeParsers;

    StringToTypeParser(Map<Class<?>, TypeParser<?>> typeParsers) {
        this.typeParsers = Collections.unmodifiableMap(new HashMap<>(typeParsers));
    }

    public static StringToTypeParserBuilder newBuilder() {
        return new StringToTypeParserBuilder();
    }

    /**
     * Parse String to T.
     *
     * @param <T>   the type parameter
     * @param value the value
     * @param type  the type
     * @return the Type value
     */
    public <T> T parse(String value, @Nonnull Class<T> type) {
        if (value == null || value.equals("")) {
            return null;
        }

        if (value.trim().equalsIgnoreCase("null")) {
            // primitive type can't map to null because primitive type contains value not link
            if (type.isPrimitive()) {
                throw new IllegalArgumentException(String.format("'%s' primitive can not be set to null.", type.getName()));
            }
            return null;
        }

        Object result = typeParsers.containsKey(type) ? callTypeParser(value, type) : null;
        //if parser from the Map of custom parser haven't parsed value then use standard static methods with name: valueOf or of
        result = result == null && isStaticFactoryMethodExists("valueOf", type) ? callFactoryMethod("valueOf", value, type) : result;
        result = result == null && isStaticFactoryMethodExists("of", type) ? callFactoryMethod("of", value, type) : result;

        if (result == null) {
            String message = "There is no registered 'TypeParser' for that type, or that type does not contain one of the following static factory methods: "
                    + "'%s.valueOf(String)', or '%s.of(String)'.";
            message = String.format(message, type.getSimpleName(), type.getSimpleName());
            message = canNotParseErrorMsg(value, type, message);
            log.warn(message);
            return null;
        }

        @SuppressWarnings("unchecked")
        T temp = (T) result;
        return temp;
    }

    private Object callTypeParser(String value, Class<?> type) {
        try {
            return typeParsers.get(type).parse(value);
        } catch (NumberFormatException e) {
            String message = canNotParseErrorMsg(value, type, numberFormatErrorMsg(e));
            throw new IllegalArgumentException(message, e);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException(canNotParseErrorMsg(value, type, e.getMessage()), e);
        }
    }

    private String numberFormatErrorMsg(NumberFormatException e) {
        return String.format("Number format exception %s.", e.getMessage());
    }

    private String canNotParseErrorMsg(String value, Class<?> type, String message) {
        return String.format("Can not parse \"%s\" to type '%s' due to: %s", value, type.getName(), message);
    }

    private Object callFactoryMethod(String methodName, String value, Class<?> type) {
        try {
            Method m = type.getDeclaredMethod(methodName, String.class);
            m.setAccessible(true);
            if (type.isEnum()) {
                value = value.trim();
            }
            return m.invoke(STATIC_METHOD, value);
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException(makeErrorMsg(methodName, value, type), e.getCause());
        } catch (Throwable t) {
            throw new IllegalArgumentException(makeErrorMsg(methodName, value, type), t);
        }
    }

    private boolean isStaticFactoryMethodExists(String methodName, Class<?> type) {
        try {
            Method m = type.getDeclaredMethod(methodName, String.class);
            m.setAccessible(true);
            if (!Modifier.isStatic(m.getModifiers())) {
                return false;
            }
        } catch (Exception ex) {
            log.warn("Exception in the method StringToTypeParser.isStaticFactoryMethodExists({}, {}).%n{}", methodName, type.getName(), ex);
            return false;
        }
        return true;
    }

    private String makeErrorMsg(String methodName, String value, Class<?> type) {
        String methodSignature = String.format("%s.%s('%s')", type.getName(), methodName, value);
        String message = " Exception thrown in static factory method '%s'. See underlying exception for additional information.";
        return canNotParseErrorMsg(value, type, String.format(message, methodSignature));
    }
}
