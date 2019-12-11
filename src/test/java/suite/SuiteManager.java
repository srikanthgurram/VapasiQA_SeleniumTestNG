package suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import util.ConfigFileReader;
import util.DriverManager;

import java.net.MalformedURLException;


public class SuiteManager {
    DriverManager driverManager;
    private static ConfigFileReader config = new ConfigFileReader();

    @BeforeSuite(alwaysRun = true)
    public void startDriver() throws MalformedURLException {
        driverManager = new DriverManager();
    }

    @AfterSuite(alwaysRun = true)
    public void quitDriver(){
        DriverManager.driver.quit();
    }

    @BeforeClass
    public void launchUrl(){
        DriverManager.driver.manage().window().maximize();
        String baseUrl = config.getPropertyValue("base_url");
        DriverManager.driver.get(baseUrl);
    }
}
