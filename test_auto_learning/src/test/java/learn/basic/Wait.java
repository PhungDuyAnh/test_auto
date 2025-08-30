package learn.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Wait {
    public static void main(String[] args) {

        //Trong Selenium, Waits được dùng để xử lý tình huống web load chậm hoặc
        // element xuất hiện trễ. Nếu không dùng wait, Selenium thường ném lỗi
        // NoSuchElementException hoặc ElementNotInteractableException.
        WebDriver driver = new ChromeDriver();

        // Đặt implicit wait = 10 giây
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.google.com");

        // Selenium sẽ chờ tối đa 10 giây để tìm thấy ô search
        driver.findElement(By.name("q")).sendKeys("Selenium WebDriver");

        driver.quit();
    }
}
