package demoblaze.bdd.stepdefs;

import demoblaze.pages.HomePage;
import demoblaze.components.LoginModal;
import demoblaze.pages.ProductDetailPage;
import demoblaze.pages.OrderPage;
import demoblaze.components.InformationOrderModal;
import demoblaze.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import java.time.Duration;

public class TestHooks {
    public static WebDriver driver;
    public static HomePage homePage;
    public static LoginModal loginModal;
    public static ProductDetailPage productDetailPage;
    public static OrderPage orderPage;
    public static InformationOrderModal informationOrderModal;
    public static final String BASE_URL = ConfigReader.get("base.url");

    @Before
    public void setUp() {
        String browser = "chrome";
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        homePage = new HomePage(driver);
        loginModal = new LoginModal(driver);
        productDetailPage = new ProductDetailPage(driver);
        orderPage = new OrderPage(driver);
        informationOrderModal = new InformationOrderModal(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

