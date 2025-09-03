package anhtester.utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() {
        return new Object[][] {
                {"admin@example.com", "123456"},
                {"user@example.com", "password123"},
                {"test@anhtester.com", "testpass"}
        };
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][] {
                {"invalid@email.com", "wrongpassword"},
                {"wrong@user.com", "123456"},
                {"admin@example.com", "wrongpass"},
                {"", "password"},
                {"email@test.com", ""},
                {"", ""},
                {"notanemail", "password"},
                {"admin@", "123456"},
                {"@example.com", "password"},
                {"admin@example", "123456"}
        };
    }

    @DataProvider(name = "specialCharacterData")
    public Object[][] specialCharacterData() {
        return new Object[][] {
                {"admin@#$%.com", "password"},
                {"admin@example.com", "pass@#$%"},
                {"admin'OR'1'='1", "password"},
                {"admin@example.com", "pass'OR'1'='1"},
                {"<script>alert('xss')</script>", "password"},
                {"admin@example.com", "<script>alert('xss')</script>"}
        };
    }

    @DataProvider(name = "boundaryTestData")
    public Object[][] boundaryTestData() {
        // Generate very long strings for boundary testing
        String longEmail = "a".repeat(100) + "@example.com";
        String longPassword = "a".repeat(200);
        String maxLengthEmail = "a".repeat(50) + "@example.com";
        String maxLengthPassword = "a".repeat(100);

        return new Object[][] {
                {longEmail, "password"},
                {"admin@example.com", longPassword},
                {longEmail, longPassword},
                {maxLengthEmail, maxLengthPassword},
                {"a@b.co", "123"}, // Minimum valid format
                {"admin@example.com", "p"} // Single character password
        };
    }
}
