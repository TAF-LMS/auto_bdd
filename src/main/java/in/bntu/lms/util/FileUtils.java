package in.bntu.lms.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

@Slf4j
@UtilityClass
public class FileUtils {

    public static String readFile(Path path) {
        try (InputStream inputStream = new FileInputStream(path.toFile())) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("Read file '{}' exception", path);
        }
        return "";
    }

    public static File writeBytesToFile(byte[] bytes, String path) {
        File screen = new File(path);
        try (OutputStream out = new FileOutputStream(screen)) {
            out.write(bytes);
        } catch (IOException e) {
            log.error("Save data in the file exception. FilePath: {}", screen.getPath());
        }
        return screen;
    }

    public static File writeStringToFile(String message, String fileName) {
        return writeBytesToFile(message.getBytes(), fileName);
    }
}
