package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager {
    public static WebDriver driver;

    public DriverManager(){
        ConfigFileReader config = new ConfigFileReader();
        String browser = config.getPropertyValue("browser");
        String chromeDriverPath = config.getPropertyValue("chrome_driver_path");
        String geckoDriverPath = config.getPropertyValue("geko_driver_path");

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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
