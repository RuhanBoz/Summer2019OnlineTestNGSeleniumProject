package tests.day5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BrowserFactory;

public class TestsForLinkText {
    public static void main(String[] args) {

        WebDriver driver = BrowserFactory.getDriver("chroma");

        driver.get("http://practice.cybertekschool.com/");

        driver.findElement(By.linkText("Autocomplete"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
