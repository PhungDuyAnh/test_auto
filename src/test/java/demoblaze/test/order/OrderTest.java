package demoblaze.test.order;

import demoblaze.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderTest extends BaseTest {

    @Test
    public void testOrderSuccess() {
        homePage.clickLoginLink();
        loginModal.login("admin","admin");
        Assert.assertTrue(homePage.getNameOfUser().contains("admin"), "UserName must be displayed after login");

        homePage.clickProductDetail();
        productDetailPage.clickAddToCartButton();
        productDetailPage.handleAlertAndAccept();
        homePage.clickCartLink();
        Assert.assertTrue(orderPage.isTotalPriceDisplayed(), "Total price must be displayed after add to cart");

        orderPage.clickPlaceOrderButton();
        informationOrderModal.inputOrderInformation("DuyAnh", "VN", "HN", "01230120321",
                "09", "2025");
    }
}
