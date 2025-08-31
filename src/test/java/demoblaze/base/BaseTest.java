package demoblaze.base;

import demoblaze.components.InformationOrderModal;
import demoblaze.components.LoginModal;
import demoblaze.pages.OrderPage;
import demoblaze.pages.ProductDetailPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import demoblaze.pages.HomePage;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected HomePage homePage;
    protected LoginModal loginModal;
    protected ProductDetailPage productDetailPage;
    protected OrderPage orderPage;
    protected InformationOrderModal informationOrderModal;
    protected final String BASE_URL = "https://www.demoblaze.com/index.html";

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // rely on explicit waits in BasePage; set reasonable page load timeout
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        driver.get(BASE_URL);
        homePage = new HomePage(driver);
        loginModal = new LoginModal(driver);
        productDetailPage = new ProductDetailPage(driver);
        orderPage = new OrderPage(driver);
        informationOrderModal = new InformationOrderModal(driver);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}
