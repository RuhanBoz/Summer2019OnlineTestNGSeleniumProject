package tests.day7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BrowserFactory;

public class TestNGPractice {
   @Test
    public void test() {

       Assert.assertEquals("appl", "apple",  "Word is not correct! should be apple");

    }
    @Test(description = "Verifying title of the practice website")
    public void verifyTitle(){

        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice.cybertekschool.com/");
        String expectedTitle = "Practice";
        String  actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Title is wrong");
        driver.quit();
    }
    @Test(description = "Verify that heading is displayed")
    public void verifyHeadingIsDisplayed(){
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice.cybertekschool.com/");
        //if there is no element with this locator, we will get NoSuchElementException
        //and our program will stop on the findElement line
        WebElement heading = driver.findElement(By.xpath("//span[text()='Test Automation Practice']"));
        //to make sure that element is visible to user
        //because element can be present, but not visible
        //we need to make sure element is visible
        Assert.assertTrue(heading.isDisplayed(), "Element is not visible");
        driver.quit();
    }
}
