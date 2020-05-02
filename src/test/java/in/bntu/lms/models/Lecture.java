package in.bntu.lms.models;

import in.bntu.lms.framework.ui.annotations.TableMap;
import in.bntu.lms.framework.ui.interfaces.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lecture implements Table {
    @TableMap(title = "Тема лекции")
    private String name;
    @TableMap(title = "Количество часов")
    private Integer time;

    public Lecture(Map<String, String> entry) {
        this(entry.get("name"), Integer.parseInt(entry.get("time")));
    }
}
