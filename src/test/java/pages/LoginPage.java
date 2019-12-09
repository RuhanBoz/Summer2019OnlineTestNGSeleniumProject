package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LoginPage  extends BasePage {

    @FindBy(id= "prependedInput")
    public WebElement userNameInput;

     @FindBy(id= "prependedInput2")
     public WebElement passwordInput;

     @FindBy(id = "_submit")
     public WebElement loginButton;

     @FindBy(css = "[class='alert alert-error']")
     public WebElement warningMessage;


    public LoginPage(){

        PageFactory.initElements(Driver.get(), this);

    }
    public void login(String userName , String password) {
        userNameInput.sendKeys(userName);
        passwordInput.sendKeys(password, Keys.ENTER);
    }
}
