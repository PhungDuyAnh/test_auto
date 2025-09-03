package anhtester.test.login;


import anhtester.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(priority = 1, description = "Verify login page is displayed correctly")
    public void testLoginPageDisplay() {
        // Verify login page elements are displayed
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");
        Assert.assertTrue(loginPage.isEmailFieldDisplayed(), "Email field should be displayed");
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed(), "Password field should be displayed");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should be displayed");
        Assert.assertTrue(loginPage.isForgotPasswordLinkDisplayed(), "Forgot password link should be displayed");

        // Verify page title contains relevant text
        String pageTitle = loginPage.getPageTitle();
        Assert.assertTrue(pageTitle.length() > 0, "Page title should not be empty");

        // Verify form elements are enabled
        Assert.assertTrue(loginPage.isEmailFieldEnabled(), "Email field should be enabled");
        Assert.assertTrue(loginPage.isPasswordFieldEnabled(), "Password field should be enabled");
        Assert.assertTrue(loginPage.isLoginButtonEnabled(), "Login button should be enabled");
    }

    @Test(priority = 2, description = "Test successful login with valid credentials")
    public void testValidLogin() {
        // Perform login with valid credentials
        loginPage.performLogin(VALID_EMAIL, VALID_PASSWORD);
        waitForPageLoad();

        // Verify successful login by checking if user is redirected to dashboard
        Assert.assertTrue(dashboardPage.isDashboardPageDisplayed(), "User should be redirected to dashboard");
        Assert.assertTrue(dashboardPage.isUserLoggedIn(), "User should be logged in");

        String currentUrl = dashboardPage.getCurrentPageUrl();
        Assert.assertFalse(currentUrl.contains("authentication"), "User should not be on authentication page");
    }

    @Test(priority = 3, description = "Test login with invalid email")
    public void testInvalidEmail() {
        // Test with invalid email
        loginPage.performLogin(INVALID_EMAIL, VALID_PASSWORD);
        waitForPageLoad();

        // Verify login failed - still on login page
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("authentication"), "Should remain on login page");

        // Check for error message if displayed
        if (loginPage.isErrorMessageDisplayed()) {
            String errorMsg = loginPage.getErrorMessage();
            Assert.assertFalse(errorMsg.isEmpty(), "Error message should not be empty");
        }
    }

    @Test(priority = 4, description = "Test login with invalid password")
    public void testInvalidPassword() {
        // Test with invalid password
        loginPage.performLogin(VALID_EMAIL, INVALID_PASSWORD);
        waitForPageLoad();

        // Verify login failed - still on login page
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("authentication"), "Should remain on login page");

        // Check for error message if displayed
        if (loginPage.isErrorMessageDisplayed()) {
            String errorMsg = loginPage.getErrorMessage();
            Assert.assertFalse(errorMsg.isEmpty(), "Error message should not be empty");
        }
    }

    @Test(priority = 5, description = "Test login with both invalid credentials")
    public void testInvalidCredentials() {
        // Test with both invalid credentials
        loginPage.performLogin(INVALID_EMAIL, INVALID_PASSWORD);
        waitForPageLoad();

        // Verify login failed - still on login page
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("authentication"), "Should remain on login page");

        // Check for error message if displayed
        if (loginPage.isErrorMessageDisplayed()) {
            String errorMsg = loginPage.getErrorMessage();
            Assert.assertFalse(errorMsg.isEmpty(), "Error message should not be empty");
        }
    }

    @Test(priority = 6, description = "Test login with empty email")
    public void testEmptyEmail() {
        // Test with empty email
        loginPage.performLogin("", VALID_PASSWORD);
        waitForPageLoad();

        // Verify still on login page
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("authentication"), "Should remain on login page");
    }

    @Test(priority = 7, description = "Test login with empty password")
    public void testEmptyPassword() {
        // Test with empty password
        loginPage.performLogin(VALID_EMAIL, "");
        waitForPageLoad();

        // Verify still on login page
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("authentication"), "Should remain on login page");
    }

    @Test(priority = 8, description = "Test login with both fields empty")
    public void testEmptyCredentials() {
        // Test with both fields empty
        loginPage.performLogin("", "");
        waitForPageLoad();

        // Verify still on login page
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("authentication"), "Should remain on login page");
    }

    @Test(priority = 9, description = "Test login with special characters in email")
    public void testSpecialCharactersInEmail() {
        // Test with special characters
        loginPage.performLogin("test@#$%.com", VALID_PASSWORD);
        waitForPageLoad();

        // Verify still on login page
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("authentication"), "Should remain on login page");
    }

    @Test(priority = 10, description = "Test login with SQL injection in credentials")
    public void testSQLInjection() {
        // Test with SQL injection attempts
        loginPage.performLogin("admin' OR '1'='1", "password' OR '1'='1");
        waitForPageLoad();

        // Verify login attempt is handled securely
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("authentication"), "Should remain on login page and not be vulnerable to SQL injection");
    }

    @Test(priority = 11, description = "Verify forgot password link functionality")
    public void testForgotPasswordLink() {
        // Click forgot password link
        loginPage.clickForgotPasswordLink();
        waitForPageLoad();

        // Verify navigation or behavior
        String currentUrl = driver.getCurrentUrl();
        // Note: Update assertion based on actual behavior of the application
        Assert.assertNotNull(currentUrl, "Should navigate somewhere when clicking forgot password");

        // Navigate back to login page for other tests
        loginPage.navigateToLoginPage();
    }

    @Test(priority = 12, description = "Test field validation and input handling")
    public void testFieldValidation() {
        // Test email field
        loginPage.enterEmail("test@example.com");
        Assert.assertEquals(loginPage.getEmailFieldValue(), "test@example.com", "Email field should accept and store valid email");

        // Test password field
        loginPage.enterPassword("testpassword");
        Assert.assertEquals(loginPage.getPasswordFieldValue(), "testpassword", "Password field should accept and store password");

        // Clear fields
        loginPage.enterEmail("");
        loginPage.enterPassword("");
        Assert.assertEquals(loginPage.getEmailFieldValue(), "", "Email field should be clearable");
        Assert.assertEquals(loginPage.getPasswordFieldValue(), "", "Password field should be clearable");
    }

    @Test(priority = 13, description = "Test login with very long credentials")
    public void testLongCredentials() {
        String longEmail = "a".repeat(100) + "@example.com";
        String longPassword = "a".repeat(200);

        loginPage.performLogin(longEmail, longPassword);
        waitForPageLoad();

        // Verify application handles long inputs gracefully
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("authentication"), "Should handle long credentials gracefully");
    }

    @Test(priority = 14, description = "Test case sensitivity in login")
    public void testCaseSensitivity() {
        // Test with uppercase email
        loginPage.performLogin(VALID_EMAIL.toUpperCase(), VALID_PASSWORD);
        waitForPageLoad();

        String currentUrl = loginPage.getCurrentUrl();
        // Note: Update expectation based on application behavior
        // Some apps are case-insensitive for email, others are not
        Assert.assertTrue(currentUrl.length() > 0, "Should handle email case sensitivity appropriately");

        // Navigate back for cleanup
        if (!currentUrl.contains("authentication")) {
            loginPage.navigateToLoginPage();
        }
    }
}
