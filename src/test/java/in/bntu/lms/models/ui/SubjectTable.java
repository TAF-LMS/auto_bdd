package in.bntu.lms.models.ui;

import in.bntu.lms.framework.ui.interfaces.Table;
import in.bntu.lms.framework.ui.annotations.TableMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Map;

import static org.apache.commons.lang3.StringUtils.normalizeSpace;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubjectTable implements Table {

    @Getter
    @TableMap(title = "Название предмета")
    private String subjectName;
    @Getter
    @TableMap(title = "Аббревиатура")
    private String shortSubjectName;

    public SubjectTable(Map<String, String> table) {
        this(table.get("subjectName"), table.get("shortSubjectName"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectTable that = (SubjectTable) o;
        return new EqualsBuilder()
                .append(normalizeSpace(subjectName), normalizeSpace(that.subjectName))
                .append(normalizeSpace(shortSubjectName), normalizeSpace(that.shortSubjectName))
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(subjectName)
                .append(shortSubjectName)
                .toHashCode();
    }
}
