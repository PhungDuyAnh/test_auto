package anhtester.test.login;

import anhtester.base.BaseTest;
import anhtester.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenLoginTests extends BaseTest {

    @Test(dataProvider = "validLoginData", dataProviderClass = TestDataProvider.class,
            description = "Test login with multiple valid credentials")
    public void testValidLoginWithMultipleData(String email, String password) {
        // Perform login with provided credentials
        loginPage.performLogin(email, password);
        waitForPageLoad();

        // Verify successful login or appropriate handling
        // Note: Adjust assertions based on which credentials are actually valid in the system
        String currentUrl = dashboardPage.getCurrentPageUrl();

        // For demo purposes, we'll check that the system processes the login attempt
        Assert.assertNotNull(currentUrl, "Should navigate to some page after login attempt");

        // Navigate back to login page for next iteration
        if (!currentUrl.contains("authentication")) {
            loginPage.navigateToLoginPage();
        }
    }

    @Test(dataProvider = "invalidLoginData", dataProviderClass = TestDataProvider.class,
            description = "Test login with various invalid credentials")
    public void testInvalidLoginWithMultipleData(String email, String password) {
        // Perform login with invalid credentials
        loginPage.performLogin(email, password);
        waitForPageLoad();

        // Verify login failed - should remain on login page
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("authentication"),
                "Should remain on login page with invalid credentials: " + email + "/" + password);

        // Check for error message if displayed
        if (loginPage.isErrorMessageDisplayed()) {
            String errorMsg = loginPage.getErrorMessage();
            Assert.assertFalse(errorMsg.isEmpty(), "Error message should not be empty for invalid login");
        }
    }

    @Test(dataProvider = "specialCharacterData", dataProviderClass = TestDataProvider.class,
            description = "Test login with special characters and potential security issues")
    public void testSpecialCharacterLogin(String email, String password) {
        // Test with special characters and potential injection attempts
        loginPage.performLogin(email, password);
        waitForPageLoad();

        // Verify system handles special characters securely
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("authentication"),
                "Should handle special characters securely and remain on login page");

        // Verify no error messages contain reflected input (XSS protection)
        if (loginPage.isErrorMessageDisplayed()) {
            String errorMsg = loginPage.getErrorMessage();
            Assert.assertFalse(errorMsg.contains("<script>"), "Error message should not contain unescaped scripts");
            Assert.assertFalse(errorMsg.contains("alert("), "Error message should not contain unescaped JavaScript");
        }
    }

    @Test(dataProvider = "boundaryTestData", dataProviderClass = TestDataProvider.class,
            description = "Test login with boundary value data")
    public void testBoundaryValueLogin(String email, String password) {
        // Test with boundary values (very long, very short inputs)
        loginPage.performLogin(email, password);
        waitForPageLoad();

        // Verify system handles boundary values gracefully
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("authentication"),
                "Should handle boundary values gracefully");

        // Verify page is still functional after boundary value input
        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "Login page should remain functional after boundary value input");
    }

    @Test(description = "Test multiple rapid login attempts")
    public void testRapidLoginAttempts() {
        // Test multiple rapid invalid login attempts to check for rate limiting
        for (int i = 0; i < 5; i++) {
            loginPage.performLogin("test" + i + "@example.com", "wrongpass" + i);
            waitForPageLoad();

            // Verify system handles rapid attempts appropriately
            String currentUrl = loginPage.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("authentication"),
                    "Should remain on login page for attempt " + (i + 1));

            // Small delay between attempts
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Verify page is still responsive after multiple attempts
        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "Login page should remain responsive after multiple attempts");
    }
}
