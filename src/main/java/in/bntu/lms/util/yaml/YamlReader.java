package in.bntu.lms.util.yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@UtilityClass
@Slf4j
public class YamlReader {
    public static <T> T readYamlConfig(String fileName, Class<T> clazz) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream in = classLoader.getResourceAsStream(fileName)) {
            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            return om.readValue(Objects.requireNonNull(in), clazz);
        } catch (NullPointerException ex) {
            log.error("Nullable stream.", ex);
            throw ex;
        } catch (IOException ex) {
            log.error("Yaml parse exception.", ex);
            throw new YAMLParseException(clazz.getTypeName(), fileName, ex);
        }
    }
}
