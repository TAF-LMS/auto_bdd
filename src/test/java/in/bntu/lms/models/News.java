package in.bntu.lms.models;

import in.bntu.lms.framework.ui.annotations.FieldLocator;
import in.bntu.lms.framework.ui.conf.LocatorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @FieldLocator(locatorType = LocatorType.TAG_NAME, locator = "h4")
    private String name;
    @FieldLocator(locatorType = LocatorType.CSS, locator = "div[ng-bind-html]")
    private String text;

    public News(Map<String, String> table) {
        this(table.get("name"), table.get("text"));
    }
}
