package saucedemo.test.login;

import org.testng.Assert;
import org.testng.annotations.Test;
import saucedemo.base.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void shouldShowErrorWhenPasswordIncorrect() {
        loginPage.login("standard_user", "xxx");
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertTrue(actualMessage.toLowerCase().contains("epic"),
                "Expected error message to contain 'Epic' but was: " + actualMessage);
    }
}
