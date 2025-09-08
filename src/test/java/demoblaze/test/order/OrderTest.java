package demoblaze.test.order;

import demoblaze.base.BaseTest;
import demoblaze.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class OrderTest extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = DataProviders.class, groups = {"smoke"})
    public void testLoginSuccess(String username, String password) {
        homePage.clickLoginLink();
        loginModal.loginSuccess(username, password);
        Assert.assertTrue(homePage.getNameOfUser().contains(username), "UserName must be displayed after login");
    }

    @Test(dataProvider = "invalidLoginData", dataProviderClass = DataProviders.class, groups = {"negative"})
    public void testLoginFail(String username, String password) {
        homePage.clickLoginLink();
        loginModal.loginFail(username, password);
        String alertText = loginModal.getLoginAlertText();
        Assert.assertEquals(alertText, "Wrong password.", "Alert message should match expected");
    }

    @Test(priority = 2, dataProvider = "orderData", dataProviderClass = DataProviders.class, groups = {"smoke"})
    public void testOrderSuccess(String name, String country, String city,
                                 String creditCard, String month, String year) {
        homePage.clickProductDetail();
        productDetailPage.clickAddToCartButton();
        productDetailPage.handleAlertAndAccept();
        homePage.clickCartLink();
        Assert.assertTrue(orderPage.isTotalPriceDisplayed(), "Total price must be displayed after add to cart");
        orderPage.clickPlaceOrderButton();
        informationOrderModal.inputOrderInformation(name, country, city, creditCard, month, year);
        informationOrderModal.clickButtonPurchaseOrderSuccess();
        informationOrderModal.clickOKButton();
    }

    @Test(priority = 3, dataProvider = "emptyOrderData", dataProviderClass = DataProviders.class, groups = {"negative"})
    public void testOrderWithEmptyFields(String name, String country, String city,
                                         String creditCard, String month, String year) {
        homePage.clickProductDetail();
        productDetailPage.clickAddToCartButton();
        productDetailPage.handleAlertAndAccept();
        homePage.clickCartLink();
        orderPage.clickPlaceOrderButton();
        informationOrderModal.inputOrderInformation(name, country, city, creditCard, month, year);
        informationOrderModal.clickButtonPurchaseOrderFail();
        String alertText = informationOrderModal.getOrderAlterText();
        Assert.assertEquals(alertText, "Please fill out Name and Creditcard.", "Alert message should match expected");
    }
}
