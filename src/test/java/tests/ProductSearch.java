package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import suite.SuiteManager;
import util.DriverManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductSearch extends SuiteManager {

    @Test(groups = {"regression"})
    public void searchForProduct(){
        // variables
        String searchBarSelector = "//*[@id=\"keywords\"]";
        String productName = "Baseball Jersey";
        String searchButtonSelector = "#search-bar > form > input.btn.btn-success";
        String productSearchResults = "#products > div .product-body > a > span";

        // enter product name in search bar
        WebElement searchBar = DriverManager.driver.findElement(By.xpath(searchBarSelector));
        searchBar.clear();
        searchBar.sendKeys(productName);

        // Click on Search button
        DriverManager.driver.findElement(By.cssSelector(searchButtonSelector)).click();

        // Get search results and print the product names
        List<WebElement> searchResults = DriverManager.driver.findElements(By.cssSelector(productSearchResults));

        for(WebElement searchResult : searchResults){
            Assert.assertTrue(searchResult.getText().contains(productName), "Product NOT found in the search results");
        }
    }
}
