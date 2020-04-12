package in.bntu.lms.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Slf4j
@UtilityClass
public class FileUtils {

    public static File writeBytesToFile(byte[] bytes, String path) {
        File screen = new File(path, UUID.randomUUID().toString() + "attachment");
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
