package demoblaze.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class OrderPage extends BasePage{

    // PageFactory elements
    @FindBy(xpath = "//button[text()='Place Order']")
    private WebElement placeOrderButton;

    @FindBy(xpath = "//h3[@id='totalp']")
    private WebElement totalPrice;

    // Constructor - use AjaxElementLocatorFactory to allow waiting for elements
    public OrderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    // Actions
    public void clickPlaceOrderButton() {
        click(placeOrderButton);
    }

    public boolean isTotalPriceDisplayed() {
        return isDisplayedSafe(totalPrice);
    }
}
