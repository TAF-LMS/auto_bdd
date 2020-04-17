package in.bntu.lms.models.ui;

import in.bntu.lms.framework.ui.interfaces.Table;
import in.bntu.lms.framework.ui.interfaces.TableMap;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JoinLecturerTable implements Table {

    @TableMap(title = "№")
    private Integer number;
    @TableMap(title = "Преподаватель")
    private String lecturer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoinLecturerTable that = (JoinLecturerTable) o;
        return new EqualsBuilder()
                .append(lecturer.toLowerCase(), that.lecturer.toLowerCase())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(lecturer)
                .toHashCode();
    }
}
