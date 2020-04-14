package in.bntu.lms.framework.uiparser;

import in.bntu.lms.framework.ui.interfaces.ElementEnum;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public class ElementEnumTypeParser<T extends ElementEnum> implements TypeParser<T> {

    private final Class<? extends Enum<? extends ElementEnum>> parseClass;

    @Override
    @SuppressWarnings("unchecked")
    public T parse(String value) {
        return (T) Arrays.stream(parseClass.getEnumConstants())
                .filter(value0 -> ((ElementEnum)value0).get().equalsIgnoreCase(value) || value0.name().equals(value))
                .findFirst()
                .orElse(null);
    }
}
