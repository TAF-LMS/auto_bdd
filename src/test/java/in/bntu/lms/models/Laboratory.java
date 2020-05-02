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
public class Laboratory implements Table {

    @TableMap(title = "Название")
    private String name;
    @TableMap(title = "Краткое название")
    private String shortName;
    @TableMap(title = "№")
    private String number;
    @TableMap(title = "Часы")
    private String duration;

    public Laboratory(Map<String, String> entry) {
        this(entry.get("name"), entry.get("shortName"), entry.get("number"), entry.get("duration"));
    }
}
