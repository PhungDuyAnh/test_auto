package demoblaze.components;

import demoblaze.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class InformationOrderModal extends BasePage {

    // PageFactory elements
    @FindBy(xpath = "//input[@id='name']")
    private WebElement nameField;

    @FindBy(xpath = "//input[@id='country']")
    private WebElement countryField;

    @FindBy(xpath = "//input[@id='city']")
    private WebElement cityField;

    @FindBy(xpath = "//input[@id='card']")
    private WebElement cardField;

    @FindBy(xpath = "//input[@id='month']")
    private WebElement monthField;

    @FindBy(xpath = "//input[@id='year']")
    private WebElement yearField;

    @FindBy(xpath = "//button[@onclick='purchaseOrder()']")
    private WebElement buttonPurchaseOrder;

    @FindBy(xpath = "//div[@class='modal-content']")
    private WebElement modalContentPurchase;

    @FindBy(xpath = "//button[text()='OK']")
    private WebElement okButton;

    // Constructor - use AjaxElementLocatorFactory to allow waiting for elements
    public InformationOrderModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public void enterNameFiled(String name) {
        set(nameField, name);
    }

    public void enterCountryFiled(String country) {
        set(countryField, country);
    }

    public void enterCityFiled(String city) {
        set(cityField, city);
    }

    public void enterCardFiled(String card) {
        set(cardField, card);
    }

    public void enterMonthFiled(String month) {
        set(monthField, month);
    }

    public void enterYearFiled(String year) {
        set(yearField, year);
    }

    public void clickButtonPurchaseOrder() {
        click(buttonPurchaseOrder);
        waitForInvisibility(modalContentPurchase);
    }

    public void clickOKButton() {
        click(okButton);
    }

    // Convenience: input order information
    public void inputOrderInformation(String name, String country, String city, String card, String month, String year) {
        enterNameFiled(name);
        enterCountryFiled(country);
        enterCityFiled(city);
        enterCardFiled(card);
        enterMonthFiled(month);
        enterYearFiled(year);
        clickButtonPurchaseOrder();
        clickOKButton();
    }
}
