package testdata;


import org.testng.annotations.DataProvider;

public class LoginCredentials {

    @DataProvider(name="loginCredentials")
    public static Object[][] loginData(){
        return new Object[][]{
                {"test1@testuser.com", "test123"},
                {"test2@testuser.com", "test123"},
                {"vapasi@thoughtworks.com", "test123"}
        };
    }
}
