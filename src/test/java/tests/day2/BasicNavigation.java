package tests.day2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicNavigation {
    public static void main(String[] args) {

        //if you have exception: cannot load a class
        //that means that package name, doesn't match with location of the class itself
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        //to maximize browser
        driver.manage().window().maximize();
        driver.get("http://google.com");
        //we want to navigate, to the different page
        //within same browser
        //we not gonna open new browser or new tab
        driver.navigate().to( "http://amazon.com");
        driver.navigate().back();
        String url = driver.getCurrentUrl();
        System.out.println(url);
        driver.quit();



    }
}
