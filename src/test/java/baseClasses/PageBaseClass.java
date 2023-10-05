package baseClasses;

import PageClasses.LandingPage;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class PageBaseClass extends BaseTestClass {


    public ExtentTest logger;

    public PageBaseClass(WebDriver driver, ExtentTest logger){
        this.driver = driver;
        this.logger = logger;
    }

    public LandingPage OpenApplication() {
        logger.log(Status.INFO, "Opening the WebSite");
        driver.get("https://www.rediff.com/");
        logger.log(Status.PASS, "Successfully Opened the https://www.rediff.com");
        LandingPage landingPage = new LandingPage(driver,logger);
        PageFactory.initElements(driver, landingPage);
        return landingPage;
    }



    //Get Page Title
    public void getTitle(String expectedTitle){
        try {
            Assert.assertEquals(driver.getTitle(), expectedTitle);
            reportPass("Actual Title : " + driver.getTitle() + " - equals to Expected Title : " + expectedTitle);
        }catch (Exception e){
            reportFail(e.getMessage());
        }
    }

    //Accept Java Script Alert
    public void acceptAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            logger.log(Status.PASS, "Page Alert Accepted");
        }catch (Exception e){
            reportFail(e.getMessage());
        }
    }

    //Cancel Java Script Alert
    public void cancelAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            logger.log(Status.PASS, "Page Alert Rejected");
        } catch (Exception e) {
            reportFail(e.getMessage());
        }
    }

        //Select value from DropDown
       public void selectDropDownValue(WebElement webElement, String value){
        try {
            Select select = new Select(webElement);
            select.selectByValue(value);
            logger.log(Status.PASS, "Selected the Value : " + value);
        }catch (Exception e){
            reportFail(e.getMessage());
        }
       }
    public void verifyElementIsDisplayed(WebElement webElement){
        try {
            if (webElement.isDisplayed()){
                reportPass("Message is on the display");
            }else {
                reportFail("Message is not on the display");
            }
        }catch (Exception e){
            reportFail(e.getMessage());
        }
    }

    public List getAllElementsOfDropDown(WebElement webElement){
        try {
            Select select = new Select(webElement);
            List<WebElement>allElements = select.getOptions();
            return allElements;
        }catch (Exception e){
            reportFail(e.getMessage());
        }
        return null;
    }

    //Reporting Functions
    public void reportFail(String reportString){
        logger.log(Status.FAIL, reportString);
        // takeScreenShotOnFailure();
        Assert.fail(reportString);
    }
    public void reportPass(String reportString){
        logger.log(Status.PASS, reportString);
    }


}
