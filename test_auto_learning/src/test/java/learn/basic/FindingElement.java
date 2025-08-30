package learn.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindingElement {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demo.guru99.com/test/ajax.html");

        // Find the radio button for “No” using its ID and click on it
        driver.findElement(By.id("no")).click();

        //Click on Check Button
        WebElement element = driver.findElement(By.id("no"));
        String value = element.getAttribute("value");

        System.out.println(value);
    }
}
