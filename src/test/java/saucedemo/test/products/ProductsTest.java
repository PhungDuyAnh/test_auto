package saucedemo.test.products;

import org.testng.Assert;
import org.testng.annotations.Test;
import saucedemo.pages.ProductsPage;
import saucedemo.base.BaseTest;

public class ProductsTest extends BaseTest {
    @Test
    public void testProductsHeaderIsDisplayed()
    {
        ProductsPage productsPage = loginPage.logIntoApplication("standard_user","secret_sauce");
        Assert.assertTrue(productsPage.isProductsHeaderDisplayed(), "Products Header is displayed");
    }
}
