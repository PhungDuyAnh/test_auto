package learn.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Condition {
    public static void main(String[] args) {
        //isEnabled() → Kiểm tra element có enabled hay không (có thể nhập, click).
        //
        //isDisplayed() → Kiểm tra element có hiển thị trên giao diện hay không.
        //
        //isSelected() → Kiểm tra element có được chọn không (radio button, checkbox, option).
        // Khởi tạo ChromeDriver
        WebDriver driver = new ChromeDriver();
        driver.get("https://example.com");

        // isEnabled()
        WebElement username = driver.findElement(By.id("username"));
        if (username.isEnabled()) {
            // Nhập dữ liệu tutorial vào ô
            username.sendKeys("tutorial");
            // Xóa dữ liệu trong ô
            username.clear();
            System.out.println("Textbox enabled, nhập dữ liệu thành công");
        } else {
            System.out.println("Textbox bị disable");
        }

        // isDisplayed()
        WebElement loginBtn = driver.findElement(By.id("loginBtn"));
        if (loginBtn.isDisplayed()) {
            loginBtn.click();
            System.out.println("Button hiển thị và đã click");
        } else {
            System.out.println("Button không hiển thị");
        }

        // isSelected()
        WebElement rememberMe = driver.findElement(By.id("remember"));
        if (!rememberMe.isSelected()) {
            rememberMe.click();
            System.out.println("Checkbox chưa chọn, bây giờ đã chọn");
        } else {
            System.out.println("Checkbox đã chọn sẵn");
        }

        // Đóng browser
        driver.quit();
    }
}
