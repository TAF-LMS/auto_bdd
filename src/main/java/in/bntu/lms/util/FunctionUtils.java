package in.bntu.lms.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FunctionUtils {

    public static <E, T> void ifValueNotNull(E checkClass, Function<E, T> lambda) {
        if (checkClass != null) {
            lambda.apply(checkClass);
        }
    }

    public static <T> T ifStringNotEmpty(String value, Function<String, T> lambda) {
        if (value != null && !value.isEmpty()) {
            return lambda.apply(value);
        }
        return null;
    }
}
