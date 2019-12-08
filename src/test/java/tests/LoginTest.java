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

import java.util.concurrent.TimeUnit;

public class LoginTest {
    //class variables
    public static WebDriver driver;
    public static String baseUrl = "https://spree-vapasi-prod.herokuapp.com/";
    public static String chromeDriverPath = System.getProperty("user.dir")+"/"+"src/test/resources/drivers/chromedriver";
    public static String geckoDriverPath = System.getProperty("user.dir")+"/"+"src/test/resources/drivers/geckodriver";

    @BeforeClass
    @Parameters({"browser"})
    public void initializeDriver(@Optional("firefox") String browser){
        System.out.println("Initializing driver");
        // Setup chrome driver
        if(browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            driver = new ChromeDriver();
        }else if(browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", geckoDriverPath);
            driver = new FirefoxDriver();
        }


        //implicit timeout
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @BeforeTest
    public void launchWebPage(){
        System.out.println("Starting test");
    }

    @AfterClass
    public void quitDriver(){
        System.out.println("Quit driver");
        driver.quit();
    }

    @Test(groups = {"smoke", "regression"})
    public void login(){
        driver.get(baseUrl);
        // test data
        String loginId="vapasi@thoughtworks.com";
        String password = "test123";
        // Locators
        String loginLinkSelector = "#link-to-login > a";
        String emailInput = "#spree_user_email";
        String passwordInput = "#spree_user_password";
        String loginButton = "#new_spree_user > p:nth-child(4) > input";
        // Click on Login Link
        WebElement loginLink = driver.findElement(By.cssSelector(loginLinkSelector));
        loginLink.click();

        // provide Email
        WebElement emailField = driver.findElement(By.cssSelector(emailInput));
        emailField.clear();
        emailField.sendKeys(loginId);

        // provide Password
        WebElement passwordField = driver.findElement(By.cssSelector(passwordInput));
        passwordField.clear();
        passwordField.sendKeys(password);

        // Click on Login Button
        driver.findElement(By.cssSelector(loginButton)).click();
    }

    @Test(dependsOnMethods = "login", groups = {"regression", "smoke"})
    public void verifyLogin(){
        String loginMessageSelector = "#content > div.alert.alert-success";

        // text to compare
        String loginMessageText = "Logged in successfully";

        // verify if Login is successful. Wait for the Login alert message to be displayed
        WebElement loginMessage = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(loginMessageSelector)));

        Assert.assertTrue(loginMessage.getText().contains(loginMessageText), "Login Alert message NOT Found");
    }

    @Test(dependsOnMethods = {"login"}, groups = {"regression", "smoke"})
    public void verifyLogout(){
        String signoutLinkSelector = "//*[@id=\"nav-bar\"]/li[2]/a";
        String signoutMessageLocator = "#content > div.alert.alert-notice";
        WebElement signoutLink = driver.findElement(By.xpath(signoutLinkSelector));
        String signoutMessageText = "Signed out successfully.";

        // Logout and wait for the logout message to be displayed
        signoutLink.click();
        WebElement signoutMessage = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(signoutMessageLocator)));

        Assert.assertTrue(signoutMessage.getText().contains(signoutMessageText), "Singout Alert message NOT found");
    }

}
