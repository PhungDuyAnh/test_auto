package demoblaze.bdd.stepdefs;

import io.cucumber.java.en.*;
import org.testng.Assert;
import java.util.Map;

public class OrderSteps {
    @Given("I am logged in as {string} with password {string}")
    public void i_am_logged_in(String username, String password) {
        TestHooks.driver.get(TestHooks.BASE_URL);
        TestHooks.homePage.clickLoginLink();
        TestHooks.loginModal.loginSuccess(username, password);
        Assert.assertTrue(TestHooks.homePage.isUserLoggedIn());
    }

    @When("I add a product to cart and place an order with:")
    public void i_add_product_and_place_order(io.cucumber.datatable.DataTable dataTable) {
        java.util.List<java.util.Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        java.util.Map<String, String> data = rows.get(0); // lấy dòng đầu tiên
        TestHooks.homePage.clickProductDetail();
        TestHooks.productDetailPage.clickAddToCartButton();
        TestHooks.productDetailPage.handleAlertAndAccept();
        TestHooks.homePage.clickCartLink();
        TestHooks.orderPage.clickPlaceOrderButton();
        TestHooks.informationOrderModal.inputOrderInformation(
            data.get("name"), data.get("country"), data.get("city"),
            data.get("card"), data.get("month"), data.get("year")
        );
    }

    @Then("The order should be successful")
    public void the_order_should_be_successful() {
        TestHooks.informationOrderModal.clickButtonPurchaseOrderSuccess();
        TestHooks.informationOrderModal.clickOKButton();
    }

    @Then("I should see an alert {string}")
    public void i_should_see_alert(String expectedAlert) {
        TestHooks.informationOrderModal.clickButtonPurchaseOrderFail();
        String alertText = TestHooks.informationOrderModal.getOrderAlterText();
        Assert.assertEquals(alertText, expectedAlert);
    }
}
