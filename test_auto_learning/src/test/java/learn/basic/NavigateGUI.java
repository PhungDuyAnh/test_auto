package learn.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigateGUI {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        String baseUrl = "https://github.com";

        driver.get(baseUrl);
        driver.findElement(By.linkText("Sign in")).click();

        String tagName = driver.findElement(By.id("login_field")).getTagName();
        String title = driver.getTitle();
        String currentUrl = driver.getCurrentUrl();

        System.out.println(tagName);
        System.out.println(title);
        System.out.println(currentUrl);

// --- sử dụng navigate ---
        Thread.sleep(2000);
        driver.navigate().back();  // quay lại trang GitHub
        System.out.println("Back to: " + driver.getCurrentUrl());

        Thread.sleep(2000);
        driver.navigate().forward();  // đi tới lại trang login
        System.out.println("Forward to: " + driver.getCurrentUrl());

        Thread.sleep(2000);
        driver.navigate().refresh();  // refresh trang hiện tại
        System.out.println("Page refreshed: " + driver.getTitle());

        Thread.sleep(2000);
        driver.navigate().to("https://google.com");  // mở trang mới
        System.out.println("Navigate to Google: " + driver.getTitle());


        driver.get("https://demo.guru99.com/selenium/deprecated.html");
        driver.switchTo().frame("classFrame");
        driver.findElement(By.linkText("Deprecated")).click();
        driver.close();

        driver.quit();
    }
}
