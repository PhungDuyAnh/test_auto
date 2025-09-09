package learn;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileDownload {
    private static final String PAGE = "https://eternallybored.org/misc/wget/";
    private static final Path DOWNLOAD_DIR = Paths.get(System.getProperty("user.dir"), "downloads");

    public static void main(String[] args) throws Exception {
        // ensure download dir exists
        Files.createDirectories(DOWNLOAD_DIR);

        // Setup Chrome with prefs
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        // chỉ định nơi lưu file
        prefs.put("download.default_directory", DOWNLOAD_DIR.toAbsolutePath().toString());
        // tắt dialog “Save As”, browser tự lưu
        prefs.put("download.prompt_for_download", false);
        // cho phép overwrite nếu thư mục đã được cấu hình
        prefs.put("download.directory_upgrade", true);
        // tắt pop-up khi download
        prefs.put("profile.default_content_settings.popups", 0);
        // optional tries (may or may not be effective depending on Chrome/Chromedriver versions):
        prefs.put("safebrowsing.enabled", false);
        prefs.put("safebrowsing.disable_download_protection", true);

        options.setExperimentalOption("prefs", prefs);
        // you can add --headless if you want, but headless downloads sometimes need extra handling
        // options.addArguments("--headless=new");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            driver.get(PAGE);

            // find links that end with .zip or .exe (choose first matching)
            List<WebElement> links = driver.findElements(By.cssSelector("a[href$='.zip'], a[href$='.exe']"));
            if (links.isEmpty()) {
                throw new RuntimeException("No .zip/.exe links found on page");
            }

            // pick first link (or choose by index/text)
            WebElement link = links.get(0);
            String href = link.getAttribute("href");
            System.out.println("Found href: " + href);

            // derive file name from href
            String filename = Paths.get(new java.net.URI(href).getPath()).getFileName().toString();
            Path targetFile = DOWNLOAD_DIR.resolve(filename);
            System.out.println("Expecting file -> " + targetFile);

            // delete if already exists
            Files.deleteIfExists(targetFile);

            // click to start download
            link.click();

            // wait for download to complete:
            waitForFileDownload(DOWNLOAD_DIR, filename, Duration.ofSeconds(60));
            System.out.println("Download finished: " + targetFile.toAbsolutePath());

        } finally {
            driver.quit();
        }
    }

    // Wait loop: check file exists and no .crdownload temp file (Chrome)
    private static void waitForFileDownload(Path dir, String expectedFileName, Duration timeout) throws InterruptedException {
        long end = System.currentTimeMillis() + timeout.toMillis();
        Path expected = dir.resolve(expectedFileName);
        while (System.currentTimeMillis() < end) {
            // if final file exists and no temporary file exists for it -> done
            boolean hasFinal = Files.exists(expected);
            boolean hasTmp = Files.exists(dir.resolve(expectedFileName + ".crdownload")); // chrome partial
            // also check for any .crdownload in dir (in case name differs)
            try {
                boolean anyPartial = Files.list(dir).anyMatch(p -> p.getFileName().toString().endsWith(".crdownload"));
                if (hasFinal && !anyPartial) return;
            } catch (IOException e) {
                // ignore, continue polling
            }

            Thread.sleep(500);
        }
        throw new RuntimeException("Timeout waiting for download of: " + expectedFileName);
    }
}
