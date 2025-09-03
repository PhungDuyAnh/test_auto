package demoblaze.test.order;

import demoblaze.base.BaseTest;
import demoblaze.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class OrderTest extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = DataProviders.class)
    public void testLoginSuccess(String username, String password) {
        homePage.clickLoginLink();
        loginModal.login(username,password);
        Assert.assertTrue(homePage.getNameOfUser().contains(username), "UserName must be displayed after login");
    }

    @Test(priority = 2, dataProvider = "orderData", dataProviderClass = DataProviders.class)
    public void testOrderSuccess(String name, String country, String city,
                                 String creditCard, String month, String year) {

        // click product, add to cart, navigate to cart
        homePage.clickProductDetail();
        productDetailPage.clickAddToCartButton();
        productDetailPage.handleAlertAndAccept();
        homePage.clickCartLink();

        Assert.assertTrue(orderPage.isTotalPriceDisplayed(), "Total price must be displayed after add to cart");

        orderPage.clickPlaceOrderButton();
        informationOrderModal.inputOrderInformation(name, country, city, creditCard, month, year);
    }

//    @Test(priority = 2)
//    public void testOrderWithMissingName() {
//        homePage.clickProductDetail();
//        productDetailPage.clickAddToCartButton();
//        productDetailPage.handleAlertAndAccept();
//        homePage.clickCartLink();
//        orderPage.clickPlaceOrderButton();
//        informationOrderModal.inputOrderInformation("", "VN", "HN", "01230120321", "09", "2025");
//        Assert.assertTrue(informationOrderModal.isErrorMessageDisplayed(), "Error message must be displayed when name is missing");
//    }
//
//    @Test(priority = 3)
//    public void testOrderWithInvalidCreditCard() {
//        homePage.clickProductDetail();
//        productDetailPage.clickAddToCartButton();
//        productDetailPage.handleAlertAndAccept();
//        homePage.clickCartLink();
//        orderPage.clickPlaceOrderButton();
//        informationOrderModal.inputOrderInformation("DuyAnh", "VN", "HN", "invalid_card", "09", "2025");
//        Assert.assertTrue(informationOrderModal.isErrorMessageDisplayed(), "Error message must be displayed for invalid credit card");
//    }
//
//    @Test(priority = 4)
//    public void testOrderWithEmptyCart() {
//        homePage.clickCartLink();
//        Assert.assertFalse(orderPage.isTotalPriceDisplayed(), "Total price should not be displayed for empty cart");
//        orderPage.clickPlaceOrderButton();
//        Assert.assertTrue(informationOrderModal.isErrorMessageDisplayed(), "Error message must be displayed when placing order with empty cart");
//    }
//
//    @Test(priority = 5)
//    public void testAddMultipleProductsAndCheckTotal() {
//        homePage.clickProductDetail();
//        productDetailPage.clickAddToCartButton();
//        productDetailPage.handleAlertAndAccept();
//        homePage.clickProductDetail(); // giả sử click lại sẽ chọn sản phẩm khác
//        productDetailPage.clickAddToCartButton();
//        productDetailPage.handleAlertAndAccept();
//        homePage.clickCartLink();
//        Assert.assertTrue(orderPage.isTotalPriceDisplayed(), "Total price must be displayed after adding multiple products");
//        Assert.assertTrue(orderPage.getTotalPrice() > 0, "Total price must be greater than 0");
//    }

//    @Test(priority = 6)
//    public void testLogout() {
//        homePage.clickLogoutLink();
//        Assert.assertFalse(homePage.isUserLoggedIn(), "User should not be logged in after logout");
//    }
}
