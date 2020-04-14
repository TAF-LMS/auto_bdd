package in.bntu.lms.framework.uiparser;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import static in.bntu.lms.util.FunctionUtils.ifStringNotEmpty;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DefaultTypeParser {
    private static final Map<Class<?>, TypeParser<?>> DEFAULT_TYPE_PARSERS = new HashMap<>();
    private static final Map<Class<?>, Class<?>> WRAPPER_TO_PRIMITIVE = new HashMap<>();

    static {
        WRAPPER_TO_PRIMITIVE.put(Boolean.class, boolean.class);
        WRAPPER_TO_PRIMITIVE.put(Integer.class, int.class);
        WRAPPER_TO_PRIMITIVE.put(Long.class, long.class);
        WRAPPER_TO_PRIMITIVE.put(Double.class, double.class);
    }

    static {
        put(Boolean.class, value -> ifStringNotEmpty(value, val -> Boolean.parseBoolean(val.trim().toLowerCase()) || val.equals("1")));
        put(Integer.class, value -> ifStringNotEmpty(value, val -> Integer.valueOf(val.trim())));
        put(Long.class, value -> ifStringNotEmpty(value, val -> Long.valueOf(val.trim())));
        put(Double.class, value -> ifStringNotEmpty(value, val -> Double.valueOf(val.trim())));
        put(String.class, value -> value);
    }

    static Map<Class<?>, TypeParser<?>> copy() {
        return new HashMap<>(DEFAULT_TYPE_PARSERS);
    }

    private static <T> void put(Class<T> type, TypeParser<T> typeParser) {
        DEFAULT_TYPE_PARSERS.put(type, typeParser);
        if (WRAPPER_TO_PRIMITIVE.containsKey(type)) {
            DEFAULT_TYPE_PARSERS.put(WRAPPER_TO_PRIMITIVE.get(type), typeParser);
        }
    }
}
