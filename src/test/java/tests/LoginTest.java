package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import suite.SuiteManager;
import testdata.LoginCredentials;
import util.ConfigFileReader;
import util.DriverManager;

import java.util.concurrent.TimeUnit;

public class LoginTest extends SuiteManager {

    @Test(dataProvider = "loginCredentials", dataProviderClass = LoginCredentials.class)
//    @Parameters({"username", "password"})
    public void login(String loginId, String password){
        // Locators
        String loginLinkSelector = "#link-to-login > a";
        String emailInput = "#spree_user_email";
        String passwordInput = "#spree_user_password";
        String loginButton = "#new_spree_user > p:nth-child(4) > input";
        // Click on Login Link
        WebElement loginLink = DriverManager.driver.findElement(By.cssSelector(loginLinkSelector));
        loginLink.click();

        // provide Email
        WebElement emailField = DriverManager.driver.findElement(By.cssSelector(emailInput));
        emailField.clear();
        emailField.sendKeys(loginId);

        // provide Password
        WebElement passwordField = DriverManager.driver.findElement(By.cssSelector(passwordInput));
        passwordField.clear();
        passwordField.sendKeys(password);

        // Click on Login Button
        DriverManager.driver.findElement(By.cssSelector(loginButton)).click();
    }

    @Test(priority = 1)
    public void verifyLogin(){
        String loginMessageSelector = "#content > div.alert.alert-success";

        // text to compare
        String loginMessageText = "Logged in successfully";

        // verify if Login is successful. Wait for the Login alert message to be displayed
        WebElement loginMessage = new WebDriverWait(DriverManager.driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(loginMessageSelector)));

        Assert.assertTrue(loginMessage.getText().contains(loginMessageText), "Login Alert message NOT Found");
    }

    @Test(priority = 2)
    public void verifyLogout(){
        String signoutLinkSelector = "//*[@id=\"nav-bar\"]/li[2]/a";
        String signoutMessageLocator = "#content > div.alert.alert-notice";
        WebElement signoutLink = DriverManager.driver.findElement(By.xpath(signoutLinkSelector));
        String signoutMessageText = "Signed out successfully.";

        // Logout and wait for the logout message to be displayed
        signoutLink.click();
        WebElement signoutMessage = new WebDriverWait(DriverManager.driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(signoutMessageLocator)));

        Assert.assertTrue(signoutMessage.getText().contains(signoutMessageText), "Singout Alert message NOT found");
    }

}
