package in.bntu.lms.util;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.net.URL;
import java.util.Objects;

import static in.bntu.lms.framework.configuration.SeleniumConfig.seleniumConfig;

@UtilityClass
public class ResourcesUtils {

    public static File getResourceFile(String dir, String fileName) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(String.format("%s/%s", seleniumConfig().getTestDataPath(), fileName));
        return new File(Objects.requireNonNull(resource).getFile());
    }
}
