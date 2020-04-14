package in.bntu.lms.framework.ui.interfaces;

import java.lang.reflect.Field;
import java.util.Arrays;

public interface Table {
    default Field[] getFieldsForTable() {
        return Arrays.stream(this.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(TableMap.class))
                .toArray(Field[]::new);
    }
}
