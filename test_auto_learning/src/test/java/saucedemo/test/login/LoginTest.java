package saucedemo.test.login;

import org.testng.Assert;
import org.testng.annotations.Test;
import saucedemo.base.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void TestLogin() {
        loginPage.setUserName("standard_user");
        loginPage.setPassword("xxx");
        loginPage.clickLoginButton();
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertTrue(actualMessage.contains("Epic"));
    }
}
