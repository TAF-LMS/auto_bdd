package in.bntu.lms.framework.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import in.bntu.lms.util.yaml.YamlReader;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class AllureConfig {
    @Getter
    @JsonProperty("allure.results.directory")
    private final String resultsDirectory;

    public AllureConfig() {
        this(null);
    }

    public static AllureConfig allureConfig() {
        return YamlReader.readYamlConfig("allure.yaml", AllureConfig.class);
    }
}
