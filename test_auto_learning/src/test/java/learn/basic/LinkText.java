package learn.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LinkText {
    //Đoạn code của bạn minh họa cách Selenium tìm và click link theo text (link text / partial link text).
    //By.linkText("...") → tìm chính xác toàn bộ text trong link.
    //
    //By.partialLinkText("...") → tìm link chỉ cần chứa chuỗi con.
    public static void main(String[] args) {
        String baseUrl = "https://demo.guru99.com/test/block.html";
        WebDriver driver = new ChromeDriver();

        driver.get(baseUrl);
        //By.partialLinkText("Inside") → tìm thẻ <a> có chứa chữ "Inside" trong nội dung link text.
        driver.findElement(By.partialLinkText("Inside")).click();
        System.out.println(driver.getTitle());
        driver.navigate().back();
        //By.partialLinkText("Inside") → tìm thẻ <a> có chứa chữ "Outside" trong nội dung link text.
        driver.findElement(By.partialLinkText("Outside")).click();
        System.out.println(driver.getTitle());
        driver.quit();
    }
}
