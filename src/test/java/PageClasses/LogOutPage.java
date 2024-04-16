package PageClasses;

import BaseClasses.PageBaseClass;
import BaseClasses.TopMeniClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogOutPage extends PageBaseClass {

    public TopMeniClass topMeniClass;
    public LogOutPage(WebDriver driver, ExtentTest logger){
        super(driver,logger);
        topMeniClass = new TopMeniClass(driver,logger);
        PageFactory.initElements(driver, topMeniClass);
    }


    @FindBy(xpath = "//*[@id=\"wrapper\"]/div[4]/a")
    public WebElement loginAgain;

    public PortfolioLoginPage clickLoginAgain(){
        logger.log(Status.INFO, "Clicking the Login-Again link");
        loginAgain.click();
        logger.log(Status.PASS, "Clicked the login-Again link");
        PortfolioLoginPage portfolioLoginPage = new PortfolioLoginPage(driver,logger);
        PageFactory.initElements(driver, portfolioLoginPage);
        return portfolioLoginPage;
    }
}
