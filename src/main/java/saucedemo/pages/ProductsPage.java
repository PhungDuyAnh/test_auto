package saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ProductsPage extends BasePage{
    @FindBy(xpath = "//span[text()='Products']")
    private WebElement productsHeader;

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public boolean isProductsHeaderDisplayed() {
        return isDisplayedSafe(productsHeader);
    }

    public String getProductsHeaderText() {
        return getText(productsHeader);
    }
}
