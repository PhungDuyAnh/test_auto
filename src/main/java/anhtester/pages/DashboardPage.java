package anhtester.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    // Expected URL after successful login
    public static final String DASHBOARD_URL_PATTERN = "https://crm.anhtester.com/admin";

    // Locators for Dashboard page elements
    @FindBy(xpath = "//h1[contains(text(),'Dashboard')] | //span[contains(text(),'Dashboard')]")
    private WebElement dashboardTitle;

    @FindBy(xpath = "//div[@class='user-panel']//img | //div[contains(@class,'user-panel')]//span")
    private WebElement userProfile;

    @FindBy(xpath = "//a[contains(@href,'logout')] | //span[contains(text(),'Logout')]")
    private WebElement logoutButton;

    @FindBy(xpath = "//nav[contains(@class,'main-sidebar')] | //aside[contains(@class,'main-sidebar')]")
    private WebElement sidebarNavigation;

    @FindBy(xpath = "//div[contains(@class,'content-wrapper')] | //section[contains(@class,'content')]")
    private WebElement contentArea;

    // Constructor
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // Verification Methods
    public boolean isDashboardPageDisplayed() {
        try {
            // Check if we're on the dashboard URL
            String currentUrl = getCurrentUrl();
            boolean urlCheck = currentUrl.contains("admin") && !currentUrl.contains("authentication");

            // Check for dashboard elements
            boolean elementsCheck = isElementDisplayed(contentArea) ||
                    isElementDisplayed(sidebarNavigation);

            return urlCheck && elementsCheck;
        } catch (Exception e) {
            return false;
        }
    }

    public String getDashboardTitle() {
        try {
            return isElementDisplayed(dashboardTitle) ? getElementText(dashboardTitle) : getPageTitle();
        } catch (Exception e) {
            return getPageTitle();
        }
    }

    public boolean isUserLoggedIn() {
        // Check if we're not on login page and dashboard elements are present
        String currentUrl = getCurrentUrl();
        return !currentUrl.contains("authentication") &&
                (isElementDisplayed(contentArea) || isElementDisplayed(sidebarNavigation));
    }

    public boolean isLogoutButtonDisplayed() {
        return isElementDisplayed(logoutButton);
    }

    public void clickLogout() {
        if (isElementDisplayed(logoutButton)) {
            clickElement(logoutButton);
        }
    }

    public String getCurrentPageUrl() {
        return getCurrentUrl();
    }

    // Wait for dashboard to load
    public boolean waitForDashboardToLoad() {
        try {
            // Wait for URL to change from authentication page
            wait.until(driver -> !getCurrentUrl().contains("authentication"));

            // Wait for main content area or sidebar to be visible
            return isElementDisplayed(contentArea) || isElementDisplayed(sidebarNavigation);
        } catch (Exception e) {
            return false;
        }
    }
}
