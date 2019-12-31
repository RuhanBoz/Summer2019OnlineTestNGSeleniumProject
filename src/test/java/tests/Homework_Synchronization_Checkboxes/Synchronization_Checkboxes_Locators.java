package tests.Homework_Synchronization_Checkboxes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Driver;
import java.util.List;

public class Synchronization_Checkboxes_Locators extends Before_After_Abstract {


        WebDriverWait wait = new WebDriverWait(Driver.get(),10);

        @Test(description = "Verify that page subtitle 'Options' is displayed")
        public void testCase1(){
            WebElement optionsButton=Driver.get().findElement(By.cssSelector("[class='btn btn-link dropdown-toggle']"));
            Assert.assertTrue(optionsButton.isDisplayed());
        }

        @Test(description = "Verify that page number is equals to '1'")
        public void testCase2(){
            WebElement numberOfPages = Driver.get().findElement(By.cssSelector("[type='number']"));
            String Value = numberOfPages.getAttribute("value");
            Assert.assertTrue(Value.equals("1"));
        }

        @Test(description = "Verify that view per page number is equals to '25'")
        public void testCase3(){
            WebElement ViewPerPage = Driver.get().findElement(By.cssSelector("[class='btn dropdown-toggle ']"));
            String ViewPerPageText = ViewPerPage.getText();
            Assert.assertTrue(ViewPerPageText.equals("25"));
        }

        @Test(description = "Verify that number of calendar events (rows in the table) is equals to number of records")
        public void testCase4(){
            List<WebElement> Rows = Driver.get().findElements(By.xpath("//tbody//tr"));
            int numberOfRows = Rows.size();
            WebElement Records = Driver.get().findElement(By.cssSelector("[class='dib']:nth-of-type(3)"));
            String RecordsText = Records.getText();
            Assert.assertTrue(RecordsText.contains(""+numberOfRows));
        }

        @Test(description = "Verify that all calendar events were selected")
        public void testCase5(){
            WebElement SelectAll= Driver.get().findElement
                    (By.cssSelector("[type='checkbox'][data-select]"));
            wait.until(ExpectedConditions.elementToBeClickable(SelectAll));
            SelectAll.click();
            for(int i=1;i<=19;i++) {
                WebElement selection = Driver.get().findElement(By.xpath("//tbody//tr[" + i + "]//td//input"));
                Assert.assertTrue(selection.isSelected());
            }
        }

        @Test(description = "Verify that meeting data is displayed when clicked on meeting")
        public void testCase6(){
            WebElement meeting = Driver.get().findElement(By.xpath("//tbody//tr[13]//td[2]"));
            meeting.click();
            WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
            wait.until(ExpectedConditions.invisibilityOf(loaderMask));
            String [] info = {"Title","Testers Meeting","Description", "Start","Nov 27, 2019, 9:30 PM","End","Nov 27, 2019, 10:30 PM",
                    "All-day Event","No","Organizer","Stephan Haley","Guests","Tom Smith - Required","Recurrence",
                    "Weekly every 1 week on Wednesday","Call Via Hangout","No","This is a a weekly testers meeting"};
            List<WebElement> data = Driver.get().findElements(By.cssSelector("[class='control-label']"));
            int count=0;
            for(WebElement each: data){
                Assert.assertEquals(each.getText(),info[count],"Info does not match");
                Assert.assertTrue(each.isDisplayed());
                count++;
            }
            WebElement description =Driver.get().findElement(By.cssSelector("[class='control-label html-property']"));
            String actualDescription = description.getText();
            String expectedDescription = info[17];
            Assert.assertEquals(actualDescription,expectedDescription,"Description is wrong");
        }

    }

