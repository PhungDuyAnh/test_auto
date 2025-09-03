package anhtester.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TestUtils {

    /**
     * Take screenshot and save to specified directory
     */
    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + fileName;

            File destFile = new File(screenshotPath);
            destFile.getParentFile().mkdirs(); // Create directory if it doesn't exist

            FileUtils.copyFile(sourceFile, destFile);
            return screenshotPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Generate random email for testing
     */
    public static String generateRandomEmail() {
        String[] domains = {"gmail.com", "yahoo.com", "outlook.com", "test.com"};
        Random random = new Random();

        String username = "testuser" + random.nextInt(10000);
        String domain = domains[random.nextInt(domains.length)];

        return username + "@" + domain;
    }

    /**
     * Generate random password for testing
     */
    public static String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }

        return password.toString();
    }

    /**
     * Wait for specified milliseconds
     */
    public static void waitFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Get current timestamp as string
     */
    public static String getCurrentTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * Get current date as string
     */
    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * Create test data directory if it doesn't exist
     */
    public static void createTestDataDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Validate email format
     */
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(emailRegex);
    }

    /**
     * Generate test report name with timestamp
     */
    public static String generateReportName(String baseName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return baseName + "_" + timestamp;
    }

    /**
     * Clean string for file naming (remove special characters)
     */
    public static String cleanStringForFileName(String input) {
        return input.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}
