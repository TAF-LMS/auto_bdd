package in.bntu.lms.models;

import com.google.common.collect.Sets;
import in.bntu.lms.models.enums.Modules;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
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
    private Set<String> groups;

    public Subject(Map<String, String> table) {
        this(table.get("subjectName"),
                table.get("shortSubjectName"),
                Arrays.stream(table.get("modules").split(",")).map(Modules::valueOf).collect(Collectors.toSet()),
                table.get("hex"),
                Sets.newHashSet(table.get("groups")));
    }
}
