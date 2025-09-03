package demoblaze.utils;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "loginData")
    public static Object[][] loginData() {
        return new Object[][]{
                {"admin", "admin"},
        };
    }

    @DataProvider(name = "orderData")
    public static Object[][] orderData() {
        return new Object[][]{
                {"DuyAnh", "VN", "HN", "01230120321", "09", "2025"},
        };
    }
}
