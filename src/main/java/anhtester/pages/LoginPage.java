package anhtester.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    // Page URL
    public static final String LOGIN_URL = "https://crm.anhtester.com/admin/authentication";

    // Locators using @FindBy annotations
    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(className = "alert-danger")
    private WebElement errorMessage;

    @FindBy(xpath = "//div[@class='text-center']//h1")
    private WebElement loginTitle;

    @FindBy(xpath = "//a[contains(text(),'Forgot Your Password?')]")
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//label[@for='email']")
    private WebElement emailLabel;

    @FindBy(xpath = "//label[@for='password']")
    private WebElement passwordLabel;

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    public void navigateToLoginPage() {
        driver.get(LOGIN_URL);
    }

    public void enterEmail(String email) {
        enterText(emailField, email);
    }

    public void enterPassword(String password) {
        enterText(passwordField, password);
    }

    public void clickLoginButton() {
        clickElement(loginButton);
    }

    public void performLogin(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    // Verification Methods
    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(loginTitle) &&
                isElementDisplayed(emailField) &&
                isElementDisplayed(passwordField);
    }

    public String getLoginTitle() {
        return getElementText(loginTitle);
    }

    public String getErrorMessage() {
        return isElementDisplayed(errorMessage) ? getElementText(errorMessage) : "";
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public boolean isEmailFieldDisplayed() {
        return isElementDisplayed(emailField);
    }

    public boolean isPasswordFieldDisplayed() {
        return isElementDisplayed(passwordField);
    }

    public boolean isLoginButtonDisplayed() {
        return isElementDisplayed(loginButton);
    }

    public boolean isForgotPasswordLinkDisplayed() {
        return isElementDisplayed(forgotPasswordLink);
    }

    public String getEmailLabel() {
        return getElementText(emailLabel);
    }

    public String getPasswordLabel() {
        return getElementText(passwordLabel);
    }

    public String getLoginButtonText() {
        return getElementText(loginButton);
    }

    public void clickForgotPasswordLink() {
        clickElement(forgotPasswordLink);
    }

    // Helper methods for validation
    public String getEmailFieldValue() {
        return emailField.getAttribute("value");
    }

    public String getPasswordFieldValue() {
        return passwordField.getAttribute("value");
    }

    public boolean isEmailFieldEnabled() {
        return emailField.isEnabled();
    }

    public boolean isPasswordFieldEnabled() {
        return passwordField.isEnabled();
    }

    public boolean isLoginButtonEnabled() {
        return loginButton.isEnabled();
    }
}
