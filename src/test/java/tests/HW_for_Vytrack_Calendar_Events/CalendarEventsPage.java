package tests.HW_for_Vytrack_Calendar_Events;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BrowserUtils;
import utilities.Driver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CalendarEventsPage extends Activities_Calendar_Events {
    private WebDriverWait wait =new WebDriverWait(Driver.get(), 15);
    @Test(description = "Verify that 'view', 'edit' and 'delete' options are available for each calendar event")
    public void testCase1() {
        int row = 13;
        int column = 9;
        WebElement HoverPoint = Driver.get().findElement(By.xpath("//tbody//tr[" + row + "]//td[" + column + "]"));
        Actions action = new Actions(Driver.get());
        action.moveToElement(HoverPoint).perform();
        String[] options = { "a[href='#']", "a[href='/calendar/event/update/184']","a[href='/calendar/event/view/184']"};
        WebElement option;
        for (String each : options) {
            option = Driver.get().findElement(By.cssSelector(each));
            Assert.assertTrue(option.isDisplayed());
        }
    }

    @Test(description = "Verify that title column is displayed")
    public void testCase2() {
        Driver.get().findElement(By.cssSelector("[class='fa-cog hide-text']")).click();

        WebElement name;
        for (int i = 2; i <= 7; i++) {
            name = Driver.get().findElement(By.xpath("//tr[" + i + "]//td[3]//input"));
            name.click();
            Assert.assertFalse(name.isSelected(), "Option is still selected");
        }

        WebElement titleColumn = Driver.get().findElement(By.xpath("//span[@class='grid-header-cell__label'][contains(text(),'Title')][1]"));
        Assert.assertTrue(titleColumn.isDisplayed(), "Title column is not displayed");

    }

    @Test(description = "Verify that 'Save and Close','Save and New', and 'Save' options are available")
    public void testCase3(){
        Driver.get().findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        Driver.get().findElement(By.cssSelector("[class='btn-success btn dropdown-toggle']")).click();
        WebElement option;
        for(int i = 1;i<=3;i++) {
            option = Driver.get().findElement(By.xpath("//li[1]//button[contains(text(),'Save')]"));
            Assert.assertTrue(option.isDisplayed(), "Option "+i+" is not Display");
            Assert.assertTrue(option.isEnabled(), "Option "+i+" is not Enabled");
        }
    }

    @Test(description = "Verify that 'All Calendar Events' page subtitle is displayed")
    public void testCase4(){
        Driver.get().findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        Driver.get().findElement(By.linkText("Cancel")).click();
        WebElement subTitle = Driver.get().findElement(By.cssSelector("[class='oro-subtitle']"));
        Assert.assertTrue(subTitle.isDisplayed(),"Subtitle is not displayed");
    }
    @Test(description = "Verify that difference between end and start time is exactly 1 hour")
    public void testCase5(){
        Driver.get().findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        WebElement StartTime = Driver.get().findElement(By.cssSelector("[class='input-small timepicker-input start ui-timepicker-input']"));
        StartTime.click();
        Driver.get().findElement(By.xpath("//li[contains(text(),'12:00 AM')]")).click();
        Driver.get().findElement(By.cssSelector("[class='input-small timepicker-input end ui-timepicker-input']")).click();
        WebElement EndTime = Driver.get().findElement(By.cssSelector("[class='ui-timepicker-am ui-timepicker-selected']:nth-of-type(3)"));
        String actuslEndTime = EndTime.getText();
        String expectedEndTime = "1:00 AM";
        Assert.assertTrue(EndTime.isDisplayed());
        Assert.assertEquals(actuslEndTime,expectedEndTime, "Start time and end time are not 1 hour apart");
    }

    @Test(description = "Verify that end time is equals to 10:00pm when 9:00 PM start time is selected")
    public void testCase6(){
        Driver.get().findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        WebElement StartTime = Driver.get().findElement(By.cssSelector("[class='input-small timepicker-input start ui-timepicker-input']"));
        StartTime.click();
        Driver.get().findElement(By.xpath("//li[contains(text(),'9:00 PM')]")).click();
        Driver.get().findElement(By.cssSelector("[class='input-small timepicker-input end ui-timepicker-input']")).click();
        WebElement EndTime = Driver.get().findElement(By.cssSelector("[class='ui-timepicker-pm ui-timepicker-selected']:nth-of-type(3)"));
        String actuslEndTime = EndTime.getText();
        String expectedEndTime = "10:00 PM";
        Assert.assertTrue(EndTime.isDisplayed());
        Assert.assertEquals(actuslEndTime,expectedEndTime, "Start time and end time are not 1 hour apart");

    }
    @Test(description = "Verify taht 'All-Day Event' is selected and start/end time slots are not displayed")
    public void testCase7(){
        Driver.get().findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        WebElement allDayButton = Driver.get().findElement(By.cssSelector("[data-name='field__all-day']"));
        allDayButton.click();
        BrowserUtils.wait(2);
        Assert.assertTrue(allDayButton.isSelected(), "All day event button is not selected");
        WebElement StartTime = Driver.get().findElement(By.cssSelector("[class='input-small timepicker-input start ui-timepicker-input']"));
        WebElement Endtime = Driver.get().findElement(By.cssSelector("[class='input-small timepicker-input end ui-timepicker-input']"));
        Assert.assertFalse(StartTime.isDisplayed(), " Start time is displayed");
        Assert.assertFalse(Endtime.isDisplayed(), "End time is displayed");
    }

    @Test(description = "Verify that Repeat checkbox is selected and that Daily checkbox selected by default")
    public void testCase8(){
        Driver.get().findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        WebElement RepeatCheckBox = Driver.get().findElement(By.cssSelector("[data-name='recurrence-repeat']"));
        RepeatCheckBox.click();
        Assert.assertTrue(RepeatCheckBox.isSelected()," Repeat Checkbox is not selected");
        WebElement Options = Driver.get().findElement(By.cssSelector("[data-name='recurrence-repeats']"));
        Select RecurringEvent = new Select(Options);
        String actualDefaultValue = RecurringEvent.getFirstSelectedOption().getText();
        String expectedDefaultValue ="Daily";
        Assert.assertEquals(actualDefaultValue,expectedDefaultValue,"Daily is not default");
        List<WebElement> allOptions = RecurringEvent.getOptions();
        ArrayList<String> actualOptions = new ArrayList<>();
        ArrayList<String> expectedOptions = new ArrayList<>();
        expectedOptions.addAll(Arrays.asList("Daily","Weekly","Monthly","Yearly"));
        for(WebElement each: allOptions){
            actualOptions.add(each.getText());
        }
        Assert.assertEquals(actualOptions,expectedOptions,"Options are incorrect");
    }

    @Test(description = "After selecting Repeat,Repeat Every and Never button verify that summanry message is displayed")
    public void testCase9(){
        Driver.get().findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        WebElement RepeatCheckBox = Driver.get().findElement(By.cssSelector("[data-name='recurrence-repeat']"));
        RepeatCheckBox.click();
        Assert.assertTrue(RepeatCheckBox.isSelected()," Repeat Checkbox is not selected");
        WebElement RepeatEveryCheckBox = Driver.get().findElement(By.cssSelector("[checked='checked']"));
        Assert.assertTrue(RepeatEveryCheckBox.isSelected(),"Repeat Every checkbox is not selected");
        WebElement NeverButton= Driver.get().findElement(By.cssSelector("[type='radio'][checked='']"));
        Assert.assertTrue(NeverButton.isSelected());
        WebElement message = Driver.get().findElement(By.cssSelector("[data-name='recurrence-summary']"));
        String actualMessage = message.getText();
        String expectedMessage= "Daily every 1 day";
        Assert.assertEquals(actualMessage,expectedMessage,"Summary message is not displayed correctly");
    }

    @Test(description = "After selected Repeat, After 10 occurences Verify that the correct message is displayed")
    public void testCase10(){
        Driver.get().findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        WebElement RepeatCheckBox = Driver.get().findElement(By.cssSelector("[data-name='recurrence-repeat']"));
        RepeatCheckBox.click();
        Driver.get().findElement(By.xpath("//*[contains(text(),'After')]")).click();
        WebElement occurence = Driver.get().findElement(By.cssSelector("[data-related-field='occurrences']"));
        occurence.click();
        occurence.sendKeys("10", Keys.ENTER);
        WebElement message = Driver.get().findElement(By.cssSelector("[data-name='recurrence-summary']"));
        String actualMessage = message.getText();
        String expectedMessage= "Daily every 1 day, end after 10 occurrences";
        Assert.assertEquals(actualMessage,expectedMessage,"Summary message is not displayed correctly");
    }

    @Test(description = "After Selecting 'By Nov 18 2021' Verify that summary message is properly displayed")
    public void testCase11(){
        Driver.get().findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        WebElement RepeatCheckBox = Driver.get().findElement(By.cssSelector("[data-name='recurrence-repeat']"));
        RepeatCheckBox.click();
        Driver.get().findElement(By.xpath("//*[@class='recurrence-subview-control__text'][contains(text(),'By')]")).click();
        WebElement date = Driver.get().findElement(By.cssSelector("[class='datepicker-input hasDatepicker']"));
        date.sendKeys("Nov 18, 2021", Keys.ESCAPE);
        WebElement message = Driver.get().findElement(By.cssSelector("[data-name='recurrence-summary']"));
        String actualMessage = message.getText();
        String expectedMessage= "Daily every 1 day, end by Nov 18, 2021";
        Assert.assertEquals(actualMessage,expectedMessage,"Summary message is not displayed correctly");
    }

    @Test(description = "After selecting Repeat, Weekly, Monday and Friday. Verify that summary message is correct")
    public void testCase12(){
        Driver.get().findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        WebElement RepeatCheckBox = Driver.get().findElement(By.cssSelector("[data-name='recurrence-repeat']"));
        RepeatCheckBox.click();
        WebElement Options = Driver.get().findElement(By.cssSelector("[data-name='recurrence-repeats']"));
        Select RecurringEvent = new Select(Options);
        RecurringEvent.selectByVisibleText("Weekly");
        WebElement mondayCheckButton = Driver.get().findElement(By.cssSelector("[type='checkbox'][value='monday']"));
        WebElement fridayCheckButton = Driver.get().findElement(By.cssSelector("[type='checkbox'][value='friday']"));
        mondayCheckButton.click();
        fridayCheckButton.click();
        Assert.assertTrue(mondayCheckButton.isSelected(),"Monday checkbox is not selected");
        Assert.assertTrue(fridayCheckButton.isSelected(),"Friday checkbox is not selected");
        WebElement message = Driver.get().findElement(By.cssSelector("[data-name='recurrence-summary']"));
        String actualMessage = message.getText();
        String expectedMessage= "Weekly every 1 week on Monday, Friday";
        Assert.assertEquals(actualMessage,expectedMessage,"Summary message is not displayed correctly");

    }
}

