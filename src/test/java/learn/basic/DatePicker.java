package learn.basic;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.format.TextStyle;
import java.util.Locale;

public class DatePicker {

    private WebDriver driver;
    private WebDriverWait wait;
    // demo page
    private static final String URL = "https://jqueryui.com/datepicker/";

    @BeforeClass
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Example: select 15 Oct 2025 from the jQuery UI Datepicker demo.
     * The demo example lives inside an iframe, so we must switch to it first.
     */
    @Test
    public void selectDateOnJQueryUIDatepicker() {
        driver.get(URL);

        // switch to demo iframe (the example calendar is inside an <iframe class="demo-frame">)
        WebElement demoFrame = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".demo-frame")));
        driver.switchTo().frame(demoFrame);

        // target date
        int targetDay = 15;
        int targetMonth = 10; // October (1-based)
        int targetYear = 2025;

        // locate the input
        By inputLocator = By.id("datepicker");
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputLocator));
        input.click(); // open calendar

        // select date using helper
        selectDateInJQueryUiDatepicker(targetDay, targetMonth, targetYear);

        // read value from input and assert something present (format depends on widget)
        String selected = input.getAttribute("value");
        System.out.println("Selected value: " + selected);
        Assert.assertTrue(selected != null && !selected.isEmpty(), "Date should be populated in input");

        // switch back to default content
        driver.switchTo().defaultContent();
    }

    /**
     * Helper: navigate month/year and click the day.
     * Works with jQuery UI Datepicker markup:
     * - header contains .ui-datepicker-month and .ui-datepicker-year
     * - navigation buttons have classes .ui-datepicker-prev and .ui-datepicker-next
     * - days are anchors inside calendar table (e.g. //a[text()='15'])
     */
    private void selectDateInJQueryUiDatepicker(int day, int month, int year) {
        // wait for calendar displayed root
        By monthLocator = By.cssSelector(".ui-datepicker-month");
        By yearLocator = By.cssSelector(".ui-datepicker-year");

        wait.until(ExpectedConditions.visibilityOfElementLocated(monthLocator));
        wait.until(ExpectedConditions.visibilityOfElementLocated(yearLocator));

        // loop until displayed month/year matches target
        for (int attempts = 0; attempts < 24; attempts++) { // safety cap: 24 months
            String displayedMonthText = driver.findElement(monthLocator).getText().trim();
            String displayedYearText = driver.findElement(yearLocator).getText().trim();

            int displayedMonth = monthNameToNumber(displayedMonthText); // 1..12
            int displayedYear = Integer.parseInt(displayedYearText);

            if (displayedMonth == month && displayedYear == year) {
                // found correct month/year -> click day
                String dayXpath = String.format("//a[text()='%d']", day);
                WebElement dayElem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dayXpath)));
                dayElem.click();
                return;
            }

            // decide whether to go next or prev
            if (displayedYear > year || (displayedYear == year && displayedMonth > month)) {
                // displayed is after target -> click prev
                WebElement prev = driver.findElement(By.cssSelector(".ui-datepicker-prev"));
                prev.click();
            } else {
                // displayed is before target -> click next
                WebElement next = driver.findElement(By.cssSelector(".ui-datepicker-next"));
                next.click();
            }

            // small wait for header to change
            wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(monthLocator, displayedMonthText)));
        }

        throw new RuntimeException("Unable to navigate to target month/year within attempt limit");
    }

    private int monthNameToNumber(String monthName) {
        // convert "October" -> 10
        try {
            return java.time.Month.valueOf(monthName.toUpperCase(Locale.ROOT)).getValue();
        } catch (IllegalArgumentException e) {
            // fallback: try matching by first 3 letters (in case of localized short names)
            for (java.time.Month m : java.time.Month.values()) {
                if (m.getDisplayName(TextStyle.FULL, Locale.ENGLISH).equalsIgnoreCase(monthName)
                        || m.getDisplayName(TextStyle.SHORT, Locale.ENGLISH).equalsIgnoreCase(monthName)) {
                    return m.getValue();
                }
            }
            throw new RuntimeException("Unknown month name: " + monthName);
        }
    }
}
