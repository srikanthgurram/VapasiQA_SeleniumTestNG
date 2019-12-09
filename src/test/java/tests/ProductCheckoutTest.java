package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import suite.SuiteManager;
import util.DriverManager;

public class ProductCheckoutTest extends SuiteManager {

    @Test(groups = {"smoke"})
    public void verifyAddToCart(){
        // variables
        String searchBarSelector = "//*[@id=\"keywords\"]";
        String productName = "Tote";
        String searchButtonSelector = "#search-bar > form > input.btn.btn-success";
        String productSearchResult = "//*[@id=\"product_1\"]/div/div[1]/a";
        String productTitle = "//*[@id=\"product-description\"]/h1";
        String productQuantitySelector = "quantity";
        String addToCartButtonSelector = "add-to-cart-button";
        String checkoutButtonSelector = "checkout-link";

        String shoppingCartPageTitle = "//*[@id=\"content\"]/div/h1";

        // enter product name in search bar
        WebElement searchBar = DriverManager.driver.findElement(By.xpath(searchBarSelector));
        searchBar.clear();
        searchBar.sendKeys(productName);

        // Click on Search button
        DriverManager.driver.findElement(By.cssSelector(searchButtonSelector)).click();

        // Get search results
        WebElement searchResult = DriverManager.driver.findElement(By.xpath(productSearchResult));
        searchResult.click();

        // Assert by Product Description title
        Assert.assertTrue(DriverManager.driver.findElement(By.xpath(productTitle)).getText().contains(productName), "Product Description Not Found");

        // enter product quantity
        WebElement productQuantity = DriverManager.driver.findElement(By.id(productQuantitySelector));
        productQuantity.clear();
        productQuantity.sendKeys("1");

        // Click on Add to Cart button
        DriverManager.driver.findElement(By.id(addToCartButtonSelector)).click();

        // Assert Page title
        Assert.assertTrue(DriverManager.driver.findElement(By.xpath(shoppingCartPageTitle)).getText().contains("Shopping Cart"), "Shopping Cart Page Title not found");

        // Assert Checkout Button
        Assert.assertTrue(DriverManager.driver.findElement(By.id(checkoutButtonSelector)).isEnabled(), "Checkout Button NOT found");
    }
}
