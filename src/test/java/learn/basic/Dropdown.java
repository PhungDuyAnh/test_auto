package learn.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Dropdown {
    //Ngoài selectByVisibleText(), Selenium còn cung cấp nhiều cách chọn:
    //
    //selectByIndex(int index)
    //
    //Chọn option theo vị trí (0-based index).
    //
    //Ví dụ: drpCountry.selectByIndex(5); → chọn option ở vị trí số 5.
    //
    //selectByValue(String value)
    //
    //Chọn option dựa vào giá trị trong attribute value.
    //
    //Ví dụ: <option value="BUR">BURMA</option>
    //→ drpCountry.selectByValue("BUR");
    public static void main(String[] args) {
        String baseURL = "https://demo.guru99.com/test/newtours/register.php";
        WebDriver driver = new ChromeDriver();
        driver.get(baseURL);

        Select drpCountry = new Select(driver.findElement(By.name("country")));
        // chọn option có text hiển thị là "BURMA"
        drpCountry.selectByVisibleText("BURMA");

//        //Selecting Items in a Multiple SELECT elements
//        driver.get("http://jsbin.com/osebed/2");
//        Select fruits = new Select(driver.findElement(By.id("fruits")));
//        fruits.selectByVisibleText("Burma");
//        fruits.selectByIndex(1);
    }
}
