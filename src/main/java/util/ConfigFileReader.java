package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private Properties prop;

    public ConfigFileReader(){
        String configFileName = "testSetup.properties";
        FileInputStream inputFileStream = null;

        try {
            this.prop = new Properties();
            String configFilePath = System.getProperty("user.dir") + "/" + "src/test/resources/testdata/config" + "/" + configFileName;
            inputFileStream = new FileInputStream(configFilePath);
            prop.load(inputFileStream);
        } catch (FileNotFoundException e){
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPropertyValue(String propertyName){
        return this.prop.getProperty(propertyName);
    }
}
