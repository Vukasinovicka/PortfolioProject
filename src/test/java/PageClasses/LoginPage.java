package PageClasses;

import baseClasses.PageBaseClass;
import baseClasses.TopMeniClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends PageBaseClass {

    public TopMeniClass topMeniClass;

    public LoginPage(WebDriver driver, ExtentTest logger){
        super(driver,logger);
        topMeniClass = new TopMeniClass(driver,logger);
        PageFactory.initElements(driver, topMeniClass);
    }
  //  public RediffMailPage doLogin(){

    //return PageFactory.initElements(driver, RediffMailPage.class);
    // return new LoginPage();

    public TopMeniClass getTopMeniClass(){
        return topMeniClass;
    }
    public void getTitle(){

    }



}
