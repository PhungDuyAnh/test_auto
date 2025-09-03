package demoblaze.test.order;

import demoblaze.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class OrderTest extends BaseTest {

    // DataProvider cho login
    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"admin", "admin"},
                // nếu muốn test thêm user khác:
                 {"user2","pass2"}
        };
    }

    // DataProvider cho order info
    @DataProvider(name = "orderData")
    public Object[][] orderData() {
        return new Object[][]{
                // name, country, city, creditCard, month, year
                {"DuyAnh", "VN", "HN", "01230120321", "09", "2025"},
                {"NguyenVanA", "VN", "HCM", "09876543210", "10", "2026"}
        };
    }

    @Test(dataProvider = "loginData")
    public void testLoginSuccess(String username, String password) {
        homePage.clickLoginLink();
        loginModal.login(username,password);
        Assert.assertTrue(homePage.getNameOfUser().contains(username), "UserName must be displayed after login");
    }

    @Test(priority = 1, dataProvider = "orderData")
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
}
