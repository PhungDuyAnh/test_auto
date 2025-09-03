package learn.basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestSelenium {
    public static void main(String[] args) {

        // Declare driver
        WebDriver driver = new ChromeDriver();


        // Link web base
        String baseUrl = "https://demo.guru99.com/test/newtours/";
        String expectedTitle = "Welcome: Mercury Tours";

        // launch Chrome and direct it to the Base URL
        driver.get(baseUrl);

        // get the actual value of the title
        String actualTitle = driver.getTitle();

        /*
         * compare the actual title of the page with the expected one and print
         * the result as "Passed" or "Failed"
         */
        assert actualTitle != null;
        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }

        //close Chrome
        driver.quit();
    }
}
