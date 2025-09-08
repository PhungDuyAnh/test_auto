package demoblaze.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ScreenshotUtils {

    private static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);

    public static String takeScreenshot(WebDriver driver, String name) {
        if (driver == null) {
            logger.error("WebDriver is null. Cannot take screenshot.");
            return null;
        }
        if (!(driver instanceof TakesScreenshot)) {
            logger.error("Driver does not support screenshots: " + driver.getClass().getName());
            return null;
        }

        String safeName = name == null ? "screenshot" : name.replaceAll("[^a-zA-Z0-9-_\\.]", "_");
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
        String unique = safeName + "_" + timestamp + "_" + UUID.randomUUID() + ".png";

        Path dir = Paths.get(System.getProperty("user.dir"), "screenshots");
        try {
            Files.createDirectories(dir);
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path dest = dir.resolve(unique);
            Files.copy(srcFile.toPath(), dest);
            logger.info("Screenshot saved: " + dest.toString());
            return dest.toString();
        } catch (WebDriverException | IOException e) {
            logger.error("Failed to take/save screenshot: " + e.getMessage(), e);
            return null;
        }
    }
}
