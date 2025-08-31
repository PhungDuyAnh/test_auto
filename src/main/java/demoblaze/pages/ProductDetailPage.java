package demoblaze.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ProductDetailPage extends BasePage {

    // PageFactory elements
    @FindBy(xpath = "//a[@onclick='addToCart(1)']")
    private WebElement addToCartButton;

    // Constructor - use AjaxElementLocatorFactory to allow waiting for elements
    public ProductDetailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public void clickAddToCartButton() {
        click(addToCartButton);
    }

    public void handleAlertAndAccept() {
        waitForAlertIsPresentAndAccept();
    }
}
