package in.bntu.lms.util;

import in.bntu.lms.framework.driver.WebDriverRunner;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.function.Function;

@Slf4j
@UtilityClass
public class ScreenshotUtils {

    public static byte[] makeFullPageScreen() {
        Function<WebDriver, byte[]> makeFullPageScreenshot = driver -> {
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                BufferedImage image = new AShot()
                        .shootingStrategy(ShootingStrategies.viewportPasting(100))
                        .coordsProvider(new WebDriverCoordsProvider())
                        .takeScreenshot(driver)
                        .getImage();
                ImageIO.write(image, "png", out);
                return out.toByteArray();
            } catch (IOException | NullPointerException e) {
                log.error(e.getMessage());
            }
            return new byte[0];
        };
        return ifDriverStarted(makeFullPageScreenshot);
    }

    private <T> T ifDriverStarted(Function<WebDriver, T> lambda) {
        if (!WebDriverRunner.hasWebDriverStarted()) {
            log.warn("Cannot take screenshot. No WebDriver is bound to current thread");
            return null;
        }
        return lambda.apply(WebDriverRunner.getWebDriver());
    }
}
