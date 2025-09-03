package anhtester.base;

import anhtester.pages.DashboardPage;
import anhtester.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;

    // Test data
    protected static final String VALID_EMAIL = "admin@example.com";
    protected static final String VALID_PASSWORD = "123456";
    protected static final String INVALID_EMAIL = "invalid@email.com";
    protected static final String INVALID_PASSWORD = "wrongpassword";

    @Parameters({"browser"})
    @BeforeClass
    public void setUpClass(@Optional("chrome") String browser) {
        setupDriver(browser);
        initializePages();
    }

    @BeforeMethod
    public void setUp() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        // Navigate to login page
        loginPage.navigateToLoginPage();
    }

    @AfterMethod
    public void tearDownMethod() {
        // Take screenshot on failure if needed
        // Clear any alerts or popups
        try {
            if (driver.switchTo().alert() != null) {
                driver.switchTo().alert().dismiss();
            }
        } catch (Exception e) {
            // No alert present
        }
    }

    @AfterClass
    public void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void setupDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--disable-gpu");
                // chromeOptions.addArguments("--headless"); // Uncomment for headless mode
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                // firefoxOptions.addArguments("--headless"); // Uncomment for headless mode
                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
    }

    private void initializePages() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    // Helper methods for common test operations
    protected void performValidLogin() {
        loginPage.performLogin(VALID_EMAIL, VALID_PASSWORD);
    }

    protected void performInvalidLogin() {
        loginPage.performLogin(INVALID_EMAIL, INVALID_PASSWORD);
    }

    protected void waitForPageLoad() {
        try {
            Thread.sleep(2000); // Simple wait - can be improved with explicit waits
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
