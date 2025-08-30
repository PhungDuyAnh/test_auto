package learn.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Form {
    public static void main(String[] args) {

        // khai báo và khởi tạo các đối tượng/biến
        WebDriver driver = new ChromeDriver();

        // Base url
        String baseUrl = "https://demo.guru99.com/test/login.html";
        driver.get(baseUrl);

        // Lấy WebElement tương ứng với Địa chỉ Email(TextField)
        WebElement email = driver.findElement(By.id("email"));

        // Lấy WebElement tương ứng với Trường Mật khẩu
        WebElement password = driver.findElement(By.name("passwd"));

        // Điền email và pass
        email.sendKeys("abcd@gmail.com");
        password.sendKeys("abcdefghlkjl");
        System.out.println("Trường Văn bản đã được Đặt");

        // Xóa giá trị trong hộp văn bản
        email.clear();
        password.clear();
        System.out.println("Trường Văn bản đã Xóa");

        // Tìm nút gửi
//        WebElement login = driver.findElement(By.id("SubmitLogin"));

        // Sử dụng phương thức click để gửi biểu mẫu
//        email.sendKeys("abcd@gmail.com");
//        password.sendKeys("abcdefghlkjl");
//        login.click();
//        System.out.println("Đăng nhập xong bằng Click");

        // sử dụng phương thức submit để gửi biểu mẫu. Submit được sử dụng trên trường mật khẩu
        driver.get(baseUrl);
        driver.findElement(By.id("email")).sendKeys("abcd@gmail.com");
        driver.findElement(By.name("passwd")).sendKeys("abcdefghlkjl");
        driver.findElement(By.id("SubmitLogin")).submit();
        System.out.println("Đăng nhập xong với Submit");

//        driver.quit();

    }
}
