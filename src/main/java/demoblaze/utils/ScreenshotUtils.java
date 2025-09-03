package demoblaze.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    private static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);

    public static String takeScreenshot(WebDriver driver, String name) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = name + "_" + timestamp + ".png";
        String dirPath = System.getProperty("user.dir") + "/screenshots/";
        String filePath = dirPath + fileName;
        try {
            Files.createDirectories(Paths.get(dirPath));
            Files.copy(srcFile.toPath(), Paths.get(filePath));
            logger.info("Screenshot saved: " + filePath);
        } catch (IOException e) {
            logger.error("Failed to save screenshot: " + e.getMessage());
        }
        return filePath;
    }
}
