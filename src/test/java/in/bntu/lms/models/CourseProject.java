package in.bntu.lms.models;

import in.bntu.lms.framework.ui.annotations.TableMap;
import in.bntu.lms.framework.ui.interfaces.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseProject implements Table {

    @TableMap(title = "Тема курсового проекта (работы)")
    private String name;
    @TableMap(title = "Студент")
    private String studentFullName;
    @TableMap(title = "Группа")
    private String group;
    @TableMap(title = "Дата")
    private String date;

    public CourseProject(Map<String, String> entry) {
        this(entry.get("name"), entry.get("studentFullName"), entry.get("group"), entry.get("date"));
    }
}
