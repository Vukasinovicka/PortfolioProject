package BaseClasses;

import PageClasses.LogOutPage;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TopMeniClass extends PageBaseClass{

    public TopMeniClass(WebDriver driver, ExtentTest logger){

        super(driver,logger);
    }

    @FindBy(xpath = "//*[@id=\"wrapper\"]/div[1]/ul/li[2]/a")
    public WebElement myPorfolioLink;

    @FindBy(xpath = "//*[@id=\"signin_info\"]/a")
    public WebElement signOutButton;

    public LogOutPage signOutApplication(){
        logger.log(Status.INFO, "Cliking the Sigh-Out link");
        signOutButton.click();
        logger.log(Status.PASS, "Cliked the Sigh-Out link");
        LogOutPage logOutPage = new LogOutPage(driver,logger);
        PageFactory.initElements(driver, logOutPage);
        return logOutPage;
    }
}
