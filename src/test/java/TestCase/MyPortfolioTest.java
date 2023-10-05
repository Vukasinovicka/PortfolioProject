package TestCase;

import PageClasses.LandingPage;
import PageClasses.MoneyPage;
import PageClasses.MyPorfolioPage;
import PageClasses.PortfolioLoginPage;
import baseClasses.BaseTestClass;
import baseClasses.PageBaseClass;
import baseClasses.TopMeniClass;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utlilities.ConstantValue;

public class MyPortfolioTest extends BaseTestClass {


    LandingPage landingPage;
    MoneyPage moneyPage;
    PortfolioLoginPage portfolioLoginPage;
    MyPorfolioPage myPorfolioPage;
    TopMeniClass topMeniClass;


    @Test
    public void openPortfolio() {
        logger = reports.createTest("Login in Reddif");

        invokeBrowser("Chrome");
        PageBaseClass pageBaseClass = new PageBaseClass(driver, logger);
        PageFactory.initElements(driver, pageBaseClass);
        landingPage = pageBaseClass.OpenApplication();
        moneyPage = landingPage.clickMoneyLink();
        portfolioLoginPage = moneyPage.clickSignInLink();
        myPorfolioPage = portfolioLoginPage.login(ConstantValue.userName, ConstantValue.password);
        myPorfolioPage.verifyMoneyBiz();
        topMeniClass = myPorfolioPage.getTopMeniClass();
        topMeniClass.signOutApplication();
    }

      @Test
      public void openPortfolioWithWrongUserName(){

        logger = reports.createTest("Login with wrong userName");

        invokeBrowser("Chrome");
        PageBaseClass pageBaseClass = new PageBaseClass(driver,logger);
        PageFactory.initElements(driver, pageBaseClass);
        landingPage = pageBaseClass.OpenApplication();
        moneyPage = landingPage.clickMoneyLink();
        portfolioLoginPage = moneyPage.clickSignInLink();
        myPorfolioPage = portfolioLoginPage.login("v.ana@hotmail.com", ConstantValue.password);
        portfolioLoginPage.verifyMessage();
      }

      @Test
      public void openPortfolioWithWrongPassword(){
          logger = reports.createTest("Login with wrong password");
          invokeBrowser("Chrome");
          PageBaseClass pageBaseClass = new PageBaseClass(driver,logger);
          PageFactory.initElements(driver, pageBaseClass);
          landingPage = pageBaseClass.OpenApplication();
          moneyPage = landingPage.clickMoneyLink();
          portfolioLoginPage = moneyPage.clickSignInLink();
          myPorfolioPage = portfolioLoginPage.login(ConstantValue.userName, "Vaki");
          myPorfolioPage.verifyMoneyBiz();
      }

      @Test
      public void openPortfolioWithWrongEmailandPassword(){
        logger = reports.createTest("Login with wrong email and password");
          invokeBrowser("Chrome");
          PageBaseClass pageBaseClass = new PageBaseClass(driver,logger);
          PageFactory.initElements(driver, pageBaseClass);
          landingPage = pageBaseClass.OpenApplication();
          moneyPage = landingPage.clickMoneyLink();
          portfolioLoginPage = moneyPage.clickSignInLink();
          myPorfolioPage = portfolioLoginPage.login("v.ana.@hotmail.com", "Vaki");
          myPorfolioPage.verifyMoneyBiz();
      }

      @Test
      public void addPortfolio(){
        logger = reports.createTest("Create Portfolio Test");

          invokeBrowser("Chrome");
          PageBaseClass pageBaseClass = new PageBaseClass(driver, logger);
          PageFactory.initElements(driver, pageBaseClass);
          landingPage = pageBaseClass.OpenApplication();
          moneyPage = landingPage.clickMoneyLink();
          portfolioLoginPage = moneyPage.clickSignInLink();
          myPorfolioPage = portfolioLoginPage.login(ConstantValue.userName, ConstantValue.password);
          myPorfolioPage.verifyMoneyBiz();
          myPorfolioPage.clickCreatePortfolio();
          myPorfolioPage.enterPotrfolioName("Marijana");
          myPorfolioPage = myPorfolioPage.submitPortfolio();
          waitForPageLoad();
          myPorfolioPage.isPortfolioExisted("Marijana");
      }
      @Test
      public void deletePortfolio() {
          logger = reports.createTest("Delete Portfolio Test");
          invokeBrowser("Chrome");
          PageBaseClass pageBaseClass = new PageBaseClass(driver, logger);
          PageFactory.initElements(driver, pageBaseClass);
          landingPage = pageBaseClass.OpenApplication();
          moneyPage = landingPage.clickMoneyLink();
          portfolioLoginPage = moneyPage.clickSignInLink();
          myPorfolioPage = portfolioLoginPage.login(ConstantValue.userName, ConstantValue.password);
          myPorfolioPage.verifyMoneyBiz();
          myPorfolioPage = myPorfolioPage.selectPortfolio("2075197");
          myPorfolioPage = myPorfolioPage.deletePortfolio();
          waitForPageLoad();
          myPorfolioPage.isPortfolioDeleted("Marijana");
      }





}
