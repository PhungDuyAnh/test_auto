package learn.practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Objects;

public class LoginTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup(); // ensure chromedriver binary
        driver = new ChromeDriver();             // assign to class field
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void testOpenLoginPage() {
        driver.get("https://github.com/");
        String title = driver.getTitle();
        // ví dụ kiểm tra title
        Assert.assertNotNull(title);
        Assert.assertFalse(title.isEmpty(), "Page title check");
    }

    @Test(priority = 2)
    public void testValidLogin() {
        driver.get("https://github.com/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_field"))).sendKeys("user1");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("password");
        wait.until(ExpectedConditions.elementToBeClickable(By.name("commit"))).click();

        // chờ thông báo/redirect
        wait.until(ExpectedConditions.urlContains("/github"));
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("/github"), "Should be on dashboard");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
