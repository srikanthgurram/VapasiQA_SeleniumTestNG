package suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import util.ConfigFileReader;
import util.DriverManager;

import java.util.concurrent.TimeUnit;

public class SuiteManager {
    DriverManager driverManager;
    private static ConfigFileReader config = new ConfigFileReader();

    @BeforeSuite(alwaysRun = true)
    public void startDriver(){
        driverManager = new DriverManager();
        //implicit timeout
        DriverManager.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
