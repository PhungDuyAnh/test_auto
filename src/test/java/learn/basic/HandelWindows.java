package learn.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

public class HandelWindows {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver=new ChromeDriver();

        //Đang khởi chạy trang web.
        driver.get("https://demo.guru99.com/popup.php");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).click();

        String MainWindow=driver.getWindowHandle();

        // Để xử lý tất cả các cửa sổ mới mở.
        Set<String> s1=driver.getWindowHandles();
        Iterator<String> i1=s1.iterator();

        while(i1.hasNext())
        {
            String ChildWindow=i1.next();

            if(!MainWindow.equalsIgnoreCase(ChildWindow))
            {

                // Chuyển sang cửa sổ con
                driver.switchTo().window(ChildWindow);
                driver.findElement(By.name("emailid"))
                        .sendKeys("gaurav.3n@gmail.com");

                driver.findElement(By.name("btnLogin")).click();

                // Đóng cửa sổ con.
                driver.close();
            }
        }
        // Chuyển sang cửa sổ cha tức là cửa sổ chính.
        driver.switchTo().window(MainWindow);
    }
}
