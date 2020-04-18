package in.bntu.lms.framework.ui.interfaces;

import in.bntu.lms.framework.ui.annotations.TableMap;

import java.lang.reflect.Field;
import java.util.Arrays;

public interface Table {
    default Field[] getFieldsForTable() {
        return Arrays.stream(this.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(TableMap.class))
                .toArray(Field[]::new);
    }
}
