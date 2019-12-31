package tests.HW_for_Vytrack_Calendar_Events;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigurationReader;
import utilities.Driver;

import java.util.concurrent.TimeUnit;

public abstract class Activities_Calendar_Events {
    private WebDriverWait wait;
    @BeforeMethod
    public void setUp(){
        String url = ConfigurationReader.getProperty("url");
        Driver.get().get(url);
        wait = new WebDriverWait(Driver.get(), 15);
        Driver.get().manage().window().maximize();
        Driver.get().findElement(By.id("prependedInput")).sendKeys(ConfigurationReader.getProperty("user_name"));
        Driver.get().findElement(By.id("prependedInput2")).sendKeys(ConfigurationReader.getProperty("password"), Keys.ENTER);
        Driver.get().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.visibilityOf(loaderMask));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        //BrowserUtils.wait(2);
        WebElement activitiesElement = Driver.get().findElement(By.linkText("Activities"));
        wait.until(ExpectedConditions.visibilityOf(activitiesElement));
        wait.until(ExpectedConditions.elementToBeClickable(activitiesElement));
        activitiesElement.click();
        WebElement calendarEventsElement = Driver.get().findElement(By.linkText("Calendar Events"));
        wait.until(ExpectedConditions.visibilityOf(calendarEventsElement));
        wait.until(ExpectedConditions.elementToBeClickable(calendarEventsElement));
        calendarEventsElement.click();

        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
    }

    @AfterMethod
    public void teardown(){
        Driver.close();
    }

}



