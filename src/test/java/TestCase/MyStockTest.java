package TestCase;

import PageClasses.LandingPage;
import PageClasses.MoneyPage;
import PageClasses.MyPorfolioPage;
import PageClasses.PortfolioLoginPage;
import baseClasses.BaseTestClass;
import baseClasses.PageBaseClass;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import utlilities.ConstantValue;

public class MyStockTest extends BaseTestClass {

    LandingPage landingPage;
    MoneyPage moneyPage;
    PortfolioLoginPage portfolioLoginPage;
    MyPorfolioPage myPorfolioPage;

    @Test
    public void addStock(){
     logger = reports.createTest("Add Stock in :");

        invokeBrowser("Chrome");
        PageBaseClass pageBaseClass = new PageBaseClass(driver, logger);
        PageFactory.initElements(driver, pageBaseClass);
        landingPage = pageBaseClass.OpenApplication();
        moneyPage = landingPage.clickMoneyLink();
        portfolioLoginPage = moneyPage.clickSignInLink();
        myPorfolioPage = portfolioLoginPage.login(ConstantValue.userName, ConstantValue.password);
        myPorfolioPage.verifyMoneyBiz();
        myPorfolioPage.clickCreatePortfolio();
        myPorfolioPage.enterPotrfolioName("Marijana6");
        myPorfolioPage = myPorfolioPage.submitPortfolio();
        waitForPageLoad();
        myPorfolioPage.isPortfolioExisted("Marijana6");
        myPorfolioPage.clickAddStockButton();
        myPorfolioPage.enterStockName("Bosch Ltd");
        myPorfolioPage.clickPurchaseDate();
        selectDateIncalendar("10/09/2021");
        myPorfolioPage.enterStockQuantity("200");
        myPorfolioPage.enterStockPurchasePrice("500");
        myPorfolioPage = myPorfolioPage.clickSubmitAddStockButton();

    }
}
