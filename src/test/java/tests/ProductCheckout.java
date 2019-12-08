package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ProductCheckout {

    @Test(groups = {"smoke"})
    public void verifyAddToCart(){
        // variables
        String baseUrl = "https://spree-vapasi-prod.herokuapp.com/";
        String chromeDriverPath = System.getProperty("user.dir")+"/"+"drivers/chromedriver";
        String searchBarSelector = "//*[@id=\"keywords\"]";
        String productName = "Tote";
        String searchButtonSelector = "#search-bar > form > input.btn.btn-success";
        String productSearchResult = "//*[@id=\"product_1\"]/div/div[1]/a";
        String productTitle = "//*[@id=\"product-description\"]/h1";
        String productQuantitySelector = "quantity";
        String addToCartButtonSelector = "add-to-cart-button";
        String checkoutButtonSelector = "checkout-link";

        String shoppingCartPageTitle = "//*[@id=\"content\"]/div/h1";
        // WebDriver initialization
        WebDriver driver;

        // Setup chrome driver
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();

        //implicit timeout
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        // Launch URL
        driver.get(baseUrl);

        // enter product name in search bar
        WebElement searchBar = driver.findElement(By.xpath(searchBarSelector));
        searchBar.clear();
        searchBar.sendKeys(productName);

        // Click on Search button
        driver.findElement(By.cssSelector(searchButtonSelector)).click();

        // Get search results
        WebElement searchResult = driver.findElement(By.xpath(productSearchResult));
        searchResult.click();

        // Assert by Product Description title
        Assert.assertTrue(driver.findElement(By.xpath(productTitle)).getText().contains(productName), "Product Description Not Found");

        // enter product quantity
        WebElement productQuantity = driver.findElement(By.id(productQuantitySelector));
        productQuantity.clear();
        productQuantity.sendKeys("1");

        // Click on Add to Cart button
        driver.findElement(By.id(addToCartButtonSelector)).click();

        // Assert Page title
        Assert.assertTrue(driver.findElement(By.xpath(shoppingCartPageTitle)).getText().contains("Shopping Cart"), "Shopping Cart Page Title not found");

        // Assert Checkout Button
        Assert.assertTrue(driver.findElement(By.id(checkoutButtonSelector)).isEnabled(), "Checkout Button NOT found");

        // Quit page
        driver.quit();
    }
}
