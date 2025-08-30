package saucedemo.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage{
    private By userNameFiled = By.id("user-name");
    private By passwordFiled = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessageFiled =  By.xpath("//h3[@data-test='error']");

    public void setUserName(String userName){
        set(userNameFiled, userName);
    }

    public void setPassword(String password){
        set(passwordFiled, password);
    }

    public ProductsPage clickLoginButton(){
        click(loginButton);
        return new ProductsPage();
    }

    public ProductsPage logIntoApplication(String username, String password){
        setUserName(username);
        setPassword(password);
        return clickLoginButton();
    }

    public String getErrorMessage(){
        return findElement(errorMessageFiled).getText();
    }
}
