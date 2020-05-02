package in.bntu.lms.models;

import in.bntu.lms.framework.ui.annotations.TableMap;
import in.bntu.lms.framework.ui.interfaces.Table;
import lombok.Data;

import java.util.Map;

@Data
public class LectureAttendance implements Table {

    @TableMap(title = "Студент")
    private String studentFullName;

    @TableMap(title = "", isMap = true)
    private Map<String, String> hours;
}
