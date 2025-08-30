package learn.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SwitchingPopup {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        String alertMessage = "";

        driver.get("http://jsbin.com/usidix/1");
        driver.findElement(By.cssSelector("input[value=\"Go!\"]")).click();
        //Chuyển Selenium sang alert context
        //Lấy nội dung text hiển thị trên alert (ví dụ "This is an alert box").
        alertMessage = driver.switchTo().alert().getText();

        //Nhấn OK (accept) để đóng alert popup.
        driver.switchTo().alert().accept();

        System.out.println(alertMessage);
    }
}
