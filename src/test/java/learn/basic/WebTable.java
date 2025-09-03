package learn.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTable {
    public static void main(String[] args) {
        // //table/tbody/tr[2]/td[2] : truy cập table -> tbody -> cột 2- > dòng 2
        String baseUrl = "https://demo.guru99.com/test/write-xpath-table.html";
        WebDriver driver = new ChromeDriver();

        driver.get(baseUrl);
        String innerText = driver.findElement(
                By.xpath("//table/tbody/tr[2]/td[2]")).getText();
        System.out.println(innerText);
        driver.quit();

        // Bảng lồng bảng :
        String baseUrl2 = "https://demo.guru99.com/test/accessing-nested-table.html";
        driver.get(baseUrl2);
        String innerText2 = driver.findElement(
                By.xpath("//table/tbody/tr[2]/td[2]/table/tbody/tr/td[2]")).getText();
        System.out.println(innerText2);
        driver.quit();
    }
}
