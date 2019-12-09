package tests.day14;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


/*
#BROWSER TYPE | it's a comment
#key=value, it's like a Map in java
browser=firefox
#URL OF OUR WEB APPLICATION
url=https://qa1.vytrack.com/
#CREDENTIALS
user_name=storemanager85
password=UserUser123
 */


public class ConfigReaderTest {

    @Test
    public void test1(){
        String expectedBrowser = "chrome";
        String actualBrowser = ConfigurationReader.getProperty("browser");
        Assert.assertEquals(actualBrowser, expectedBrowser);
        System.out.println(ConfigurationReader.getProperty("url"));
        System.out.println("Username: "+ConfigurationReader.getProperty("user_name"));
        String password = ConfigurationReader.getProperty("password");
        System.out.println("Password: "+password);


    }

}
