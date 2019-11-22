package VyTrack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.BrowserFactory;
import utilities.BrowserUtils;

public class NegativeTestCase {
    public static void main(String[] args) {

        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("https://qa2.vytrack.com/user/login");
        WebElement username = driver.findElement(By.name("_username"));
        username.sendKeys("ruhanboz44");
        WebElement password = driver.findElement(By.name("_password"));
        password.sendKeys("UserUser44");
        WebElement clickLogin = driver.findElement(By.id("_submit"));
        clickLogin.click();
        String expectedTitle = "Dashboard";
        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Test Failed");
        } else {
            System.out.println("Test Passed, You are in the Home Page");

        }
        System.out.println("Expected Result --> " + expectedTitle);
        System.out.println("Actual Result --> " + actualTitle);
        BrowserUtils.wait(2);


        driver.close();

    }
}
