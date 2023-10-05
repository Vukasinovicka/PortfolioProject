package PageClasses;

import baseClasses.PageBaseClass;
import baseClasses.TopMeniClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class MyPorfolioPage extends PageBaseClass {

    public TopMeniClass topMeniClass;

    public MyPorfolioPage(WebDriver driver, ExtentTest logger) {
        super(driver,logger);
        topMeniClass = new TopMeniClass(driver,logger);
        PageFactory.initElements(driver,topMeniClass);
    }

    @FindBy(xpath = "//*[@id=\"headcontent\"]/div[1]/div[1]/a/span")
    public WebElement moneyBiz_text;

    @FindBy(id = "createPortfolio")
    public WebElement createPortfolio;

    @FindBy(id = "create")
    public WebElement createportfolio_textBox;

    @FindBy(id = "createPortfolioButton")
    public WebElement createPortfolioButton;

    @FindBy(id = "portfolioid")
    public WebElement myPortfolioList;

    @FindBy(id = "deletePortfolio")
    public WebElement deletePortfolioButton;

    @FindBy(id = "addStock")
    public WebElement addStockButton;

    @FindBy(id = "addstockname")
    public WebElement stockNameTextBox;

    @FindBy(xpath = "//*[@id=\"ajax_listOfOptions\"]/div[1]")
    public WebElement stockValue;

    @FindBy(id = "addstockqty")
    public WebElement stockQualitityTextBox;

    @FindBy(id = "addstockprice")
    public WebElement stockPurchasePrice;

    @FindBy(id = "addStockButton")
    public WebElement submitAddStock;

    @FindBy(id= "stock")
    public WebElement stockTable;

    @FindBy(id = "stockPurchaseDate")
    public WebElement stockPurchaseDate;

    public void clickPurchaseDate(){
        try {
            stockPurchaseDate.click();
            logger.log(Status.PASS, "Clicked Purchase Date");
        }catch (Exception e){
            reportFail(e.getMessage());
        }
    }


    public MyPorfolioPage clickSubmitAddStockButton(){
        try {
            submitAddStock.click();
            logger.log(Status.PASS, "Clicked Add Stock Button");
        }catch (Exception e){
            reportFail(e.getMessage());
        }
        MyPorfolioPage myPorfolioPage = new MyPorfolioPage(driver,logger);
        PageFactory.initElements(driver,myPorfolioPage);
        return myPorfolioPage;
    }

    public void verifyStock(String stockName){
        boolean flag = false;
        try {
            List<WebElement> tableRows = stockTable.findElements(By.xpath("/tbody/tr"));

            for (WebElement rows : tableRows){
                List<WebElement> tableColumns = rows.findElements(By.tagName("td"));

                for (WebElement column : tableColumns) {
                    if (column.getText().equalsIgnoreCase(stockName)){
                        flag = true;

                    }
                }
            }
           Assert.assertTrue(flag, "GivenStock :" + stockName + "is not present in this Portfolio");
            logger.log(Status.PASS, "GivenStock :" + stockName + "is not present in this Portfolio");
        }catch (Exception e){
            reportFail(e.getMessage());
        }
    }

    public void enterStockPurchasePrice(String purchasePrice) {
        try {
            stockPurchasePrice.sendKeys(purchasePrice);
            logger.log(Status.PASS, "Add the Purchase Price :" + purchasePrice);
        } catch (Exception e) {
            reportFail(e.getMessage());
        }
    }

    public void enterStockQuantity(String quantity) {
        try {
            stockQualitityTextBox.sendKeys(quantity);
            logger.log(Status.PASS, "Add the Quantity :" + quantity);
        } catch (Exception e) {
            reportFail(e.getMessage());
        }
    }
    public void enterStockName(String stockName){
        try {
            stockNameTextBox.sendKeys(stockName);
            waitForPageLoad();
            stockValue.click();
            logger.log(Status.PASS,"Typed Stock Name :" + stockName);
        }catch (Exception e){
            reportFail(e.getMessage());
        }
    }


    public void clickAddStockButton(){
        try {
            addStockButton.click();
            logger.log(Status.PASS,"Clicked on Add Stock Button");
        }catch (Exception e){
            reportFail(e.getMessage());
        }
    }

    public MyPorfolioPage deletePortfolio(){
        try {
            deletePortfolioButton.click();
            acceptAlert();
            logger.log(Status.PASS, "Deleted the Portfolio");
        }catch (Exception e){
            reportFail(e.getMessage());
        }
        MyPorfolioPage myPorfolioPage = new MyPorfolioPage(driver,logger);
        PageFactory.initElements(driver,myPorfolioPage);
        return myPorfolioPage;

    }

    public MyPorfolioPage selectPortfolio(String Value){
        selectDropDownValue(myPortfolioList, Value);
        MyPorfolioPage myPorfolioPage = new MyPorfolioPage(driver,logger);
        PageFactory.initElements(driver,myPorfolioPage);
        return myPorfolioPage;
    }



    public void isPortfolioExisted(String portfolioName){
        boolean flag = false;
        try {
            List<WebElement> allOptions = getAllElementsOfDropDown(myPortfolioList);
            for (WebElement option : allOptions) {
                if (option.getText().equalsIgnoreCase(portfolioName)){
                    flag= true;
                }else {
                    flag= false;
                }
            }
            Assert.assertTrue(flag);
            logger.log(Status.PASS, "Given Portfolio : " + portfolioName + "Present in Portfolio List");
        }catch (Exception e){
            reportFail(e.getMessage());
        }
    }

    public void isPortfolioDeleted(String portfolioName){
        boolean flag = false;
        try {
            List<WebElement> allOptions = getAllElementsOfDropDown(myPortfolioList);
            for (WebElement option : allOptions) {
                if (!option.getText().equalsIgnoreCase(portfolioName)){
                    flag= true;
                }else {
                    flag= false;
                }
            }
            Assert.assertTrue(flag);
            logger.log(Status.PASS, "Given Portfolio : " + portfolioName + "is not Present in Portfolio List");
        }catch (Exception e){
            reportFail(e.getMessage());
        }
    }

    public MyPorfolioPage submitPortfolio(){
        try {
            createPortfolioButton.click();
            logger.log(Status.PASS,"Clicked the create portfolo button");
        }catch (Exception e){
            reportFail(e.getMessage());
        }
        MyPorfolioPage myPorfolioPage = new MyPorfolioPage(driver,logger);
        PageFactory.initElements(driver,myPorfolioPage);
        return myPorfolioPage;
    }

    public void enterPotrfolioName(String portfolioName){
        try {
            createportfolio_textBox.clear();
            createportfolio_textBox.sendKeys(portfolioName);
            logger.log(Status.PASS,"Entered Portfolio Name : " + portfolioName);
        }catch (Exception e) {
            reportFail(e.getMessage());

        }
    }

    public void clickCreatePortfolio(){
        try {
            createPortfolio.click();
            logger.log(Status.PASS, "Clicked the Create Portfolio Button");
        }catch (Exception e){
            reportFail(e.getMessage());
        }
    }

    public void verifyMoneyBiz(){
        moneyBiz_text.isDisplayed();
    }
    public TopMeniClass getTopMeniClass(){

        return topMeniClass;
    }

}
