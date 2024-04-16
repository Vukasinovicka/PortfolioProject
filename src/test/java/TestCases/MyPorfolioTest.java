package TestCases;

import BaseClasses.BaseTestClass;
import BaseClasses.PageBaseClass;
import BaseClasses.TopMeniClass;
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

public class MyPorfolioTest extends BaseTestClass {

    LandingPage landingPage;
    MoneyPage moneyPage;
    PortfolioLoginPage portfolioLoginPage;
    MyPorfolioPage myporfolioPage;
    TopMeniClass topMenu;

    @Test(dataProvider="getOpenPortfolioTestData", groups={"Regression", "LoginTest" })
    public void openPorfolio(Hashtable<String, String> testData){
        logger = report.createTest("Login Rediff Portfolio : " + testData.get("Comment"));

        invokeBrowser("chrome");
        PageBaseClass pageBase = new PageBaseClass(driver, logger);
        PageFactory.initElements(driver, pageBase);
        landingPage = pageBase.OpenApplication();
        moneyPage =landingPage.clickMoneyLink();
        portfolioLoginPage = moneyPage.clickSignInLink();
        myporfolioPage = portfolioLoginPage.login(testData.get("userName"), testData.get("Password"));
        myporfolioPage.verifyMoneyBiz();
        myporfolioPage.getTitle(testData.get("PageTitle"));
        topMenu = myporfolioPage.gettopMenu();
        topMenu.signOutApplication();
    }
    @Test(dataProvider="verifyLoginTestData", groups={"Regression", "LoginTest" })
    public void verifyLogin(Hashtable<String, String> testData){
        logger = report.createTest("Login Rediff Portfolio : " + testData.get("Comment"));

        invokeBrowser("chrome");
        PageBaseClass pageBase = new PageBaseClass(driver, logger);
        PageFactory.initElements(driver, pageBase);
        landingPage = pageBase.OpenApplication();
        moneyPage =landingPage.clickMoneyLink();
        portfolioLoginPage = moneyPage.clickSignInLink();
        portfolioLoginPage.enterUser(testData.get("UserName"));
        portfolioLoginPage.subimLogin();
        portfolioLoginPage.verifyMessage();

    }

    @Test(dataProvider="addPortfolioTestData", groups={"Regression", "AddPortfolio" })
    public void addPortfolioTest(Hashtable<String, String> testData){
        logger = report.createTest("Create Porfolio Test : " + testData.get("Comment"));
        invokeBrowser("chrome");
        PageBaseClass pageBase = new PageBaseClass(driver, logger);
        PageFactory.initElements(driver, pageBase);
        landingPage = pageBase.OpenApplication();
        moneyPage =landingPage.clickMoneyLink();
        portfolioLoginPage = moneyPage.clickSignInLink();
        myporfolioPage = portfolioLoginPage.login(ConstantValue.userName, ConstantValue.password);
        myporfolioPage.verifyMoneyBiz();
        myporfolioPage.clickCreatePortfolio();
        waitForPageLoad();
        myporfolioPage.enterPortfolioName(testData.get("PortfolioName"));
        myporfolioPage = myporfolioPage.submitPortfolio();
        waitForPageLoad();
        myporfolioPage.isPorfolioExists(testData.get("PortfolioName"));
    }


    @Test(dataProvider="addPortfolioTestData", groups={"Regression", "DeletePortfolio" })
    public void deletePortfolio(Hashtable<String, String> testData){
        logger = report.createTest("Delete Porfolio Test : " + testData.get("Comment"));
        invokeBrowser("chrome");
        PageBaseClass pageBase = new PageBaseClass(driver, logger);
        PageFactory.initElements(driver, pageBase);
        landingPage = pageBase.OpenApplication();
        moneyPage =landingPage.clickMoneyLink();
        portfolioLoginPage = moneyPage.clickSignInLink();
        myporfolioPage = portfolioLoginPage.login(ConstantValue.userName, ConstantValue.password);
        waitForPageLoad();
        myporfolioPage.verifyMoneyBiz();
        myporfolioPage = myporfolioPage.selectPortfolio(testData.get("PortfolioName"));
        myporfolioPage = myporfolioPage.deletePortFolio();
        waitForPageLoad();
        myporfolioPage.isPorfolioDeleted(testData.get("PortfolioName"));

    }

    @DataProvider
    public Object[][] getOpenPortfolioTestData(){
        return TestDataProvider.getTestData("RediffPortFolioLaunch.xlsx", "RediffLoginTest", "openPorfolio");
    }

    @DataProvider
    public Object[][] verifyLoginTestData(){
        return TestDataProvider.getTestData("RediffPortFolioLaunch.xlsx", "RediffLoginTest", "verifyLogin");
    }

    @DataProvider
    public Object[][] addPortfolioTestData(){
        return TestDataProvider.getTestData("RediffPortFolioLaunch.xlsx", "RediffLoginTest", "addPortfolioTest");
    }





}
