package demoblaze.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT);
    }

    protected WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForInvisibility(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForAlertIsPresentAndAccept() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    protected void click(WebElement element) {
        try {
            waitForClickable(element).click();
        } catch (ElementClickInterceptedException e) {
            // Scroll into view and retry with JS click fallback
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            try {
                waitForClickable(element).click();
            } catch (Exception ex) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }
        }
    }

    protected void set(WebElement element, String value) {
        WebElement el = waitForVisibility(element);
        el.clear();
        el.sendKeys(value);
    }

    protected String getText(WebElement element) {
        try {
            return waitForVisibility(element).getText();
        } catch (Exception e) {
            return "";
        }
    }

    protected boolean isDisplayedSafe(WebElement element) {
        try {
            return waitForVisibility(element).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
