package demoblaze.bdd.stepdefs;

import io.cucumber.java.en.*;
import org.testng.Assert;

public class LoginSteps {

    @Given("I am on the home page")
    public void i_am_on_home_page() {
        TestHooks.driver.get(TestHooks.BASE_URL);
    }

    @When("I click the login link")
    public void i_click_login_link() {
        TestHooks.homePage.clickLoginLink();
    }

    @And("I login with username {string} and password {string}")
    public void i_login_with_credentials(String username, String password) {
        TestHooks.loginModal.loginFail(username, password);
    }

    @Then("The login result should be {string}")
    public void the_login_result_should_be(String result) {
        if ("success".equals(result)) {
            Assert.assertTrue(TestHooks.homePage.isUserLoggedIn());
        } else {
            String alertText = TestHooks.loginModal.getLoginAlertText();
            Assert.assertEquals(alertText, "Wrong password.");
        }
    }
}
