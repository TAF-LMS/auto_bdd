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
public class CoursePercentage implements Table {
    @TableMap(title = "Название этапа")
    private String name;
    @TableMap(title = "Процент выполнения")
    private String percent;
    @TableMap(title = "Дата")
    private String date;

    public CoursePercentage(Map<String, String> entry) {
        this(entry.get("name"), entry.get("percent"), entry.get("date"));
    }
}
