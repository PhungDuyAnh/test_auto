package demoblaze.base;

import demoblaze.components.InformationOrderModal;
import demoblaze.components.LoginModal;
import demoblaze.pages.OrderPage;
import demoblaze.pages.ProductDetailPage;
import demoblaze.utils.ScreenshotUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;
import demoblaze.pages.HomePage;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected HomePage homePage;
    protected LoginModal loginModal;
    protected ProductDetailPage productDetailPage;
    protected OrderPage orderPage;
    protected InformationOrderModal informationOrderModal;
    protected final String BASE_URL = "https://www.demoblaze.com/index.html";
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    // alwaysRun = true : test này vẫn sẽ được chạy dù test khác failed
    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass(@Optional("chrome") String browser) {
        log("Setting up WebDriver for browser: " + browser);
        if (browser == null || browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        log("Navigating to base URL: " + BASE_URL);
        driver.get(BASE_URL);
        if (homePage == null) homePage = new HomePage(driver);
        if (loginModal == null) loginModal = new LoginModal(driver);
        if (productDetailPage == null) productDetailPage = new ProductDetailPage(driver);
        if (orderPage == null) orderPage = new OrderPage(driver);
        if (informationOrderModal == null) informationOrderModal = new InformationOrderModal(driver);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        log("Quitting WebDriver");
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                log("Error quitting WebDriver: " + e.getMessage());
            }
        }
    }

    // Logging helper
    protected void log(String message) {
        logger.info(message);
    }

    // Screenshot helper for listener
    public String takeScreenshot(String name) {
        return ScreenshotUtils.takeScreenshot(driver, name);
    }
}
