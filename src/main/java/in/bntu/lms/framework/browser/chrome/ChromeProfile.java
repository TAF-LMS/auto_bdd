package in.bntu.lms.framework.browser.chrome;

import com.fasterxml.jackson.annotation.JsonProperty;
import in.bntu.lms.util.yaml.YamlReader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URL;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@ToString(of = {"name", "version"})
class ChromeProfile {
    @Getter
    private final String name;
    @Getter
    private final String version;
    @Getter
    @JsonProperty(value = "is_remote")
    private final boolean isRemote;
    @Getter
    private final URL remote;
    @Getter
    private final List<String> args;
    @Getter
    private final Map<String, Object> capabilities;
    @Getter
    private final Map<String, Object> options;

    static ChromeProfile downloadProfileFromYaml() {
        return YamlReader.readYamlConfig("browsers/chrome.yaml", ChromeProfile.class);
    }

    ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("version", getVersion());
        options.addArguments(getArgs());
        getOptions().forEach(options::setExperimentalOption);
        getCapabilities().forEach(options::setCapability);
        return options;
    }
}
