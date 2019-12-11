package util;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.net.URL;

public class DriverManager {
    public static WebDriver driver;

    public DriverManager() throws MalformedURLException {
        ConfigFileReader config = new ConfigFileReader();
        String browser = config.getPropertyValue("browser");
        String browserStackUsername = config.getPropertyValue("browser_stack_username");
        String browserStackAutomateKey = config.getPropertyValue("browser_stack_automate_key");
        String browserStackUrl = "https://" + browserStackUsername + ":" + browserStackAutomateKey + "@hub-cloud.browserstack.com/wd/hub";
        String gridUrl = "http://localhost:4444/wd/hub";

        // Setup chrome driver
        if(browser.equalsIgnoreCase("chrome")){
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserName", "chrome");
            caps.setCapability("platform", "LINUX");
            driver = new RemoteWebDriver(new URL(gridUrl), caps);

        }else if(browser.equalsIgnoreCase("firefox")){
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browser", "firefox");
            caps.setCapability("platform", "LINUX");
            driver = new RemoteWebDriver(new URL(gridUrl), caps);

        }else if(browser.equalsIgnoreCase("cloud")){
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browser", "Chrome");
            caps.setCapability("browser_version", "62.0");
            caps.setCapability("os", "Windows");
            caps.setCapability("os_version", "10");
            caps.setCapability("resolution", "1024x768");
            caps.setCapability("name", "Bstack-[Java] Sample Test");

            driver = new RemoteWebDriver(new URL(browserStackUrl), caps);

        }

        //implicit timeout
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
