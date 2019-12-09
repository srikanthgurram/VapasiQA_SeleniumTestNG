package util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;


public class ScreenshotCapturer {

    public static void captureScreenshot(WebDriver driver, String fileName){
        try{
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String filePath = System.getProperty("user.dir")+"/screenshots";
            File destination = new File(filePath+"/"+fileName+".png");
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to copy the screenshot. Exception: "+e.getMessage());
        }

    }
}
