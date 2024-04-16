package PageClasses;

import BaseClasses.PageBaseClass;
import BaseClasses.TopMeniClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MoneyPage extends PageBaseClass {

    public TopMeniClass topMeniClass;

    public MoneyPage(WebDriver driver, ExtentTest logger){
        super(driver,logger);
        topMeniClass = new TopMeniClass(driver,logger);
        PageFactory.initElements(driver, topMeniClass);
    }

    @FindBy(xpath = "//*[@id=\"signin_info\"]/a[1]")
    public WebElement signinLink;


    public PortfolioLoginPage clickSignInLink(){
        logger.log(Status.INFO, "Clicking the Sigh-In link");
        signinLink.click();
        logger.log(Status.PASS, "Clicked the Sigh-In link");
        PortfolioLoginPage portfolioLoginPage = new PortfolioLoginPage(driver,logger);
        PageFactory.initElements(driver, portfolioLoginPage);
        return portfolioLoginPage;
    }
}
