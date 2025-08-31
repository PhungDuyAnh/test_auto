package learn.practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class OrderTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String baseUrl = "https://www.demoblaze.com/index.html";

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 0)
    public void testOrder() {
        driver.get(baseUrl);

        // Đăng nhập
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='login2']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='loginusername']"))).sendKeys("admin");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='loginpassword']"))).sendKeys("admin");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='logIn()']"))).click();

        // Đợi login thành công (modal biến mất)
        // invisibilityOfElementLocated dùng để chờ element 'logInModal' biến mất.
        // elementToBeClickable dùng để chờ sẵn sàng thao tác
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='logInModal']")));

        // Chi tiết sản phẩm
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Samsung galaxy s6']"))).click();

        // Add vào cart
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@onclick='addToCart(1)']"))).click();

        // Xử lý alter
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

        // Vào giỏ hàng
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='cartur']"))).click();

        // Mua
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Place Order']"))).click();

        // Nhập thông tin mua hàng
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='name']"))).sendKeys("DuyAnh");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='country']"))).sendKeys("VN");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='city']"))).sendKeys("HN");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='card']"))).sendKeys("012031923123");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='month']"))).sendKeys("9");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='year']"))).sendKeys("2025");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='purchaseOrder()']"))).click();

        // Xong
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='purchaseOrder()']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']"))).click();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
