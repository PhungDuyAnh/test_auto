package demoblaze.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    // Logging helper
    protected void log(String message) {
        System.out.println("[BasePage] " + message);
    }

    // Screenshot helper
    protected String takeScreenshot(String name) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = "screenshots/" + name + "_" + timestamp + ".png";
        try {
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(srcFile.toPath(), Paths.get(filePath));
            log("Screenshot saved: " + filePath);
        } catch (IOException e) {
            log("Failed to save screenshot: " + e.getMessage());
        }
        return filePath;
    }

    // Navigation helpers
    protected void goTo(String url) {
        driver.get(url);
    }
    protected void back() {
        driver.navigate().back();
    }
    protected void refresh() {
        driver.navigate().refresh();
    }

    // Generic find methods
    protected WebElement findElement(By by) {
        return driver.findElement(by);
    }
    protected List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    // Dropdown select
    protected void selectByVisibleText(WebElement element, String text) {
        Select select = new Select(waitForVisibility(element));
        select.selectByVisibleText(text);
    }

    // Mouse actions
    protected void hover(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(waitForVisibility(element)).perform();
    }
    protected void dragAndDrop(WebElement source, WebElement target) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(waitForVisibility(source), waitForVisibility(target)).perform();
    }

    // File upload
    protected void uploadFile(WebElement element, String filePath) {
        waitForVisibility(element).sendKeys(filePath);
    }
}
