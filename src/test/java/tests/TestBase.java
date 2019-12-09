package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import utilities.BrowserUtils;
import utilities.ConfigurationReader;
import utilities.Driver;
import java.io.IOException;
//this class will be a test foundation for all test classes
//We will put here only before and after methods
//this class will be a test foundation for all test classes
//we will put here only before and after parts
//In this way before and after methods will be the same
//Every test class will extend testbase class


public abstract class TestBase {
// * ExtentReports itself does not build any reports, but allows reporters to access information, which in
// * turn build the said reports. An example of building an HTML report and adding information to ExtentX:
// * ExtentHtmlReporter html = new ExtentHtmlReporter("Extent.html");
// * ExtentXReporter extentx = new ExtentXReporter("localhost");

    protected ExtentReports extentReports;//storage report
    protected ExtentHtmlReporter extentHtmlReporter;//generate report
    //    The ExtentHtmlReporter creates a rich standalone HTML file. It allows several
    protected ExtentTest extentTest;//writes  our reoort shows us our test reports
    //    Defines a test. You can add logs, snapshots, assign author and categories to a test and its children.
    @BeforeTest
    public void beforeTest(){
        String filePath=System.getProperty("user.dir") + "/test-output/report.html";
        extentReports=new ExtentReports();
        extentHtmlReporter=new ExtentHtmlReporter(filePath);
        extentReports.attachReporter(extentHtmlReporter);
        extentHtmlReporter.config().setReportName("Vytrack Test Results");
        extentReports.setSystemInfo("Environment","QA1");
        extentReports.setSystemInfo("Browser",ConfigurationReader.getProperty("browser"));
        extentReports.setSystemInfo("OS",System.getProperty("os.name"));
    }

    @AfterTest
    public void afterTest(){
        //writes test information from the stardet reported to their output
        extentReports.flush();
    }

    @BeforeMethod
    public void setUp(){
        String url = ConfigurationReader.getProperty("url");
        Driver.get().get(url);
    }
    @AfterMethod
    public void teardown(ITestResult result){

        if(result.getStatus()==ITestResult.FAILURE) {
            extentTest.fail(result.getName());
            extentTest.fail(result.getThrowable());
            try {
                extentTest.addScreenCaptureFromPath(BrowserUtils.getScreenshot(result.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(result.getStatus()==ITestResult.SKIP){
            extentTest.skip("Test case was skiped:"+result.getName());
        }
        Driver.close();

    }

}

