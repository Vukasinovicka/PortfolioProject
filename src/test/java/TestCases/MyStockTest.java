package TestCases;

import BaseClasses.BaseTestClass;
import BaseClasses.PageBaseClass;
import PageClasses.LandingPage;
import PageClasses.MoneyPage;
import PageClasses.MyPorfolioPage;
import PageClasses.PortfolioLoginPage;
import Utilities.ConstantValue;
import Utilities.TestDataProvider;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class MyStockTest extends BaseTestClass {


    LandingPage landingPage;
    MoneyPage moneyPage;
    PortfolioLoginPage portfolioLoginPage;
    MyPorfolioPage myporfolioPage;


    @Test(dataProvider = "AddStockTestData")
    public void AddStockTest(Hashtable<String, String> testData) {
        logger = report.createTest("Add Stock in: " + testData.get("PortfolioName") + " - Stock Name" + testData.get("StockName"));

        invokeBrowser("chrome");
        PageBaseClass pageBase = new PageBaseClass(driver, logger);
        PageFactory.initElements(driver, pageBase);
        landingPage = pageBase.OpenApplication();
        moneyPage = landingPage.clickMoneyLink();
        portfolioLoginPage = moneyPage.clickSignInLink();
        myporfolioPage = portfolioLoginPage.login(ConstantValue.userName, ConstantValue.password);
        myporfolioPage.verifyMoneyBiz();
        myporfolioPage.clickCreatePortfolio();
        waitForPageLoad();
        myporfolioPage.enterPortfolioName(testData.get("PortfolioName"));
        myporfolioPage = myporfolioPage.submitPortfolio();
        waitForPageLoad();
        myporfolioPage.isPorfolioExists(testData.get("PortfolioName"));
        myporfolioPage.clickAddStock();
        myporfolioPage.enterStockName(testData.get("StockName"));
        myporfolioPage.clickStockPurchaseCalendar();
        selectDateIncalendar("11/02/2017");
        myporfolioPage.enterStockQuantity(testData.get("Quantity"));
        myporfolioPage.enterStockPrice(testData.get("StockPrice"));
        myporfolioPage = myporfolioPage.submitStock();
        waitForPageLoad();
        myporfolioPage.verifyStock(testData.get("StockName"));


    }

    @DataProvider
    public Object[][] AddStockTestData() {
        return TestDataProvider.getTestData("RediffPortFolioLaunch.xlsx", "StockTestData", "AddStockTest");
    }
}
