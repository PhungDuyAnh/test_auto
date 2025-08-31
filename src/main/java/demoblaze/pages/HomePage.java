package demoblaze.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage extends BasePage {

    // PageFactory elements
    @FindBy(xpath = "//a[@id='login2']")
    private WebElement loginLink;

    @FindBy(xpath = "//a[@id='nameofuser']")
    private WebElement nameOfUser;

    @FindBy(xpath = "//a[text()='Samsung galaxy s6']")
    private WebElement productDetail;

    @FindBy(xpath = "//a[@id='cartur']")
    private WebElement cartLink;

    // Constructor - use AjaxElementLocatorFactory to allow waiting for elements
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    // Actions
    public void clickLoginLink() {
        click(loginLink);
    }

    public void clickProductDetail() {
        click(productDetail);
    }

    public void clickCartLink() {
        click(cartLink);
    }

    public String getNameOfUser() {
        return getText(nameOfUser);
    }
}
