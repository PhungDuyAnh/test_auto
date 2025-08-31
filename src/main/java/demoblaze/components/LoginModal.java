package demoblaze.components;

import demoblaze.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginModal extends BasePage {

    // PageFactory elements
    @FindBy(xpath = "//input[@id='loginusername']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='loginpassword']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@onclick='logIn()']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@id='logInModal']")
    private WebElement loginModal;

    // Constructor - use AjaxElementLocatorFactory to allow waiting for elements
    public LoginModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public void enterUsername(String username) {
        set(usernameField, username);
    }

    public void enterPassword(String password) {
        set(passwordField, password);
    }

    public void clickLoginButton() {
        click(loginButton);
        waitForInvisibility(loginModal);
    }

    // Convenience: login method
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
