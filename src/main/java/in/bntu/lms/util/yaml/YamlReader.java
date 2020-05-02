package in.bntu.lms.util.yaml;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.function.BiFunction;

@UtilityClass
@Slf4j
public class YamlReader {

    public static <T> T readYamlConfig(String fileName, TypeReference<T> reference) {
        return readValue(fileName, (mapper, file) -> {
            try {
                return mapper.readValue(file, reference);
            } catch (IOException e) {
                throw logYamlParseException(e, reference.getType(), fileName);
            }
        });
    }

    public static <T> T readYamlConfig(String fileName, Class<T> clazz) {
        return readValue(fileName, (mapper, file) -> {
            try {
                return mapper.readValue(file, clazz);
            } catch (IOException e) {
                throw logYamlParseException(e, clazz, fileName);
            }
        });
    }

    private static <T> T readValue(String fileName, BiFunction<ObjectMapper, File, T> function) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            return function.apply(om, file);
        } catch (NullPointerException ex) {
            log.error("Resource has been null.", ex);
            throw ex;
        }
    }

    private static YAMLParseException logYamlParseException(IOException exception, Type type, String fileName) {
        log.error("Yaml parse exception.", exception);
        return new YAMLParseException(type.getTypeName(), fileName, exception);
    }
}
