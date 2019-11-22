package VyTrack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.BrowserFactory;
import utilities.BrowserUtils;

public class TestLogin {

    public static void main(String[] args) {
        //launch chrome browser
        WebDriver driver = BrowserFactory.getDriver("chrome");
        //go to vyTrack login page
        driver.get("https://qa2.vytrack.com/user/login");
        //entering the username
        WebElement username = driver.findElement(By.name("_username"));
        username.sendKeys("storemanager79");
        // entering the password
        WebElement password = driver.findElement(By.name("_password"));
        password.sendKeys("UserUser123");
        WebElement clickLogin = driver.findElement(By.id("_submit"));
        clickLogin.click();
        String expectedTitle = "Dashboard";
        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Test Passed, You are in the Home Page");
        } else {
            System.out.println("Test Failed");
        }
        System.out.println("Expected Result --> " + expectedTitle);
        System.out.println("Actual Result --> " + actualTitle);
        BrowserUtils.wait(2);


        driver.close();

    }
}