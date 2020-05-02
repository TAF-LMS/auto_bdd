package in.bntu.lms.models;

import com.google.common.collect.Sets;
import in.bntu.lms.models.enums.Modules;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Subject {
    @Getter
    private String subjectName;
    @Getter
    private String shortSubjectName;
    @Getter
    private Set<Modules> modules;
    @Getter
    private String hex;
    @Getter
    private SortedSet<String> groups;

    public Subject(Map<String, String> table) {
        this(table.get("subjectName"),
                table.get("shortSubjectName"),
                Arrays.stream(table.get("modules").split(",")).map(Modules::valueOf).collect(Collectors.toSet()),
                table.get("hex"),
                Sets.newTreeSet(Arrays.stream(table.get("groups").split(",")).collect(Collectors.toList())));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;

        return new EqualsBuilder()
                .append(subjectName, subject.subjectName)
                .append(shortSubjectName, subject.shortSubjectName)
                .append(modules, subject.modules)
                .append(hex, subject.hex)
                .append(groups, subject.groups)
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
