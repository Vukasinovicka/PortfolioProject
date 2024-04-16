package PageClasses;

import BaseClasses.PageBaseClass;
import BaseClasses.TopMeniClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class PortfolioLoginPage extends PageBaseClass {

    public TopMeniClass topmenu;

    public PortfolioLoginPage(WebDriver driver, ExtentTest logger) {
        super(driver, logger);
        topmenu = new TopMeniClass(driver, logger);
        PageFactory.initElements(driver, topmenu);
    }

    @FindBy(id = "useremail")
    public WebElement emailAddress;

    @FindBy(id = "userpass")
    public WebElement passwordBoxs;

    @FindBy(id = "loginsubmit")
    public WebElement submitButton;

    @FindBy(id = "message_shows")
    public WebElement errorMessage;

    public MyPorfolioPage login(String userName, String password) {

        emailAddress.sendKeys(userName);
        logger.log(Status.PASS, "Entered the UserName : " + userName);
        logger.log(Status.PASS, "Entered the password");
        passwordBoxs.sendKeys(password);
        logger.log(Status.PASS, "Entered the password : " + password );
        submitButton.click();

        logger.log(Status.PASS, "Clicking the Submit Login Button ");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String currentTitle = driver.getTitle();
        if (currentTitle.equals("Indian stock markets: Login to Portfolio")) {
            reportFail("Login is failed on Portfolio Login Page ");
            Assert.fail("Login Failed");
        }
        MyPorfolioPage myPorfolioPage = new MyPorfolioPage(driver, logger);
        PageFactory.initElements(driver, myPorfolioPage);
        return myPorfolioPage;

    }
    public void enterUser(String userName) {
        emailAddress.sendKeys(userName);
        logger.log(Status.PASS, "Entered the UserName : " + userName);
    }
    public void enterPassword(String password) {
        passwordBoxs.sendKeys(password);
        logger.log(Status.PASS,"Entered the Password : " + password);
    }
    public MyPorfolioPage subimLogin(){
        submitButton.click();
        logger.log(Status.PASS,"Clicked the Submit Login Button");
        MyPorfolioPage myPorfolioPage = new MyPorfolioPage(driver, logger);
        PageFactory.initElements(driver, myPorfolioPage);
        return myPorfolioPage;
    }

    public void setErrorMessage(){
        errorMessage.isDisplayed();
        logger.log(Status.PASS,"Error Message is on teh display");
    }
    public void verifyMessage(){
        verifyElementIsDisplayed(errorMessage);
    }


}
