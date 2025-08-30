package saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    public static WebDriver driver;

    public void setDriver(WebDriver driver) {
        BasePage.driver = driver;
    }

    protected WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    protected void set(By locator, String value) {
        findElement(locator).click();
        findElement(locator).sendKeys(value);
    }

    protected void click(By locator) {
        findElement(locator).click();
    }
}
