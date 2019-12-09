package tests.day13;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserFactory;

import java.time.Instant;
import java.util.List;

public class WebTablesPractice {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice.cybertekschool.com/tables");
       wait = new WebDriverWait(driver, 15);
       wait.until(ExpectedConditions.presenceOfElementLocated(By.id("table1")));
    }

    @Test(description = "Print table 1 data")
    public void test1() {


       wait.until(ExpectedConditions.presenceOfElementLocated(By.id("table1")));
        WebElement table = driver.findElement(By.id("table1"));
        System.out.println(table.getText());
    }

     @Test(description = "Verify that number of columns in the first table is equals to 6")
     public void  test2(){

         int actualColumnNumber = driver.findElements(By.xpath("//table[@id='table1']//th")).size();
         int expectedColumnNumber = 6;
         Assert.assertEquals(actualColumnNumber, expectedColumnNumber);

     }
     @Test(description = "Verify that number of rows is equals to 5")
     public void test3(){
        int expectedRowCount = 5;
        int actualRowCount = driver.findElements(By.xpath("//table[@id='table1']//tr")).size();
        Assert.assertEquals(actualRowCount, expectedRowCount);
     }
    /**
     * Use findElements() to find all values from 2nd row
     * Then iterate through the collection of elements with for each loop
     * Print text of every element from new line
     */
    @Test(description = "Print all values from the 2nd row (excluding table header)")
    public void test4() {
        List<WebElement> row = driver.findElements(By.xpath("//table[@id='table1']//tbody//tr[2]//td"));
        for (WebElement cell : row) {
            System.out.println(cell.getText());
        }
    }

    @Test(description = "Print all values from the n-th row (excluding table header)")
    public void test5() {
        //if index = 1, then it's a first row
        //if index = 2, then it's a second row
        int index = 1;
        List<WebElement> row = driver.findElements(By.xpath("//table[@id='table1']//tbody//tr[" + index + "]//td"));
        for (WebElement cell : row) {
            System.out.println(cell.getText());
        }
    }
    @Test(description = "Verify that email in the third row is equals to jdoe@hotmail.com")
    public void test6(){
        int row = 3;
        int column = 3;
        WebElement cell = driver.findElement(By.xpath("//table[@id='table1']//tbody//tr["+row+"]//td["+column+"]"));
        String expectedEmail = "jdoe@hotmail.com";
        String actualEmail = cell.getText();
        Assert.assertEquals(actualEmail, expectedEmail);
    }
    /**
     * Get all values from email column and verify that every value contains "@"
     * If no - fail test.
     *
     */
    @Test(description = "Verify that every email contains '@'")
    public void test7(){
        //get all emails
        List<WebElement> emails = driver.findElements(By.xpath("//table[@id='table1']//tbody//td[3]"));
        //loop through collection of emails
        for(WebElement email: emails){
            System.out.println(email.getText());
            Assert.assertTrue(email.getText().contains("@"));
        }
    }
    /**
     * Step 1. Click on "Last Name" column name
     * Step 2. Get all values from "Last Name" column
     * Step 3. Verify that column is sorted in alphabetic order
     */
    @Test(description = "Verify that after click on last name, values will be sorted in alphabetic order")
    public void test8(){

        WebElement lastNameElement = driver.findElement(By.xpath("//table[@id='table1']//*[text()='Last Name']"));
        lastNameElement.click();
        List<WebElement> lastNames = driver.findElements(By.xpath("//table[@id='table1']//tbody//td[1]"));
        for (int index = 0; index < lastNames.size() - 1; index++) {

            String lastName = lastNames.get(index).getText();
            String followingLastName = lastNames.get(index + 1).getText();
            System.out.println("##### Iteration :: "+index);
            System.out.println("Current Last Name: "+lastName);
            System.out.println("Following Last Name: "+followingLastName);
            System.out.println("##############################");
            Assert.assertTrue(lastName.compareTo(followingLastName) < 0);
        }

    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
