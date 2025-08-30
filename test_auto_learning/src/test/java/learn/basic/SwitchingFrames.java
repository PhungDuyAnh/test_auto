package learn.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SwitchingFrames {
    /**
     * Một trang web có thể chia nhỏ thành nhiều “khung” (frame/iframe).
     * Mỗi frame giống như một trang web con nằm trong trang chính. S
     * Selenium chỉ làm việc được trong một frame tại một thời điểm.
     * <frameset rows="20%,60%,20%">
     *    <frame src="..." name="packageListFrame">
     *    <frame src="..." name="packageFrame">
     *    <frame src="..." name="classFrame">
     * </frameset>
     * @param args
     */
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.guru99.com/selenium/deprecated.html");
        driver.switchTo().frame("classFrame");
        driver.findElement(By.linkText("Deprecated")).click();
        driver.quit();
    }
}
