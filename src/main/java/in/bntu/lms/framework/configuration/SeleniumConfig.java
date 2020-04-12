package in.bntu.lms.framework.configuration;

import in.bntu.lms.util.yaml.YamlReader;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class SeleniumConfig {
    @Getter
    private final String browser;
    @Getter
    private final String url;
    @Getter
    private final String testDataPath;
    @Getter
    private final TimeOutConfig conditionTimeOut;
    @Getter
    private final TimeOutConfig pollingTimeOut;

    private SeleniumConfig() {
        this(null, null, null, null, null);
    }

    public static SeleniumConfig seleniumConfig() {
        return YamlReader.readYamlConfig("selenium.yaml", SeleniumConfig.class);
    }
}
