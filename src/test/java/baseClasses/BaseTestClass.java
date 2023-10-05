package baseClasses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import utlilities.ExtentReportManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.lang.System.setProperty;

public class BaseTestClass {

    public WebDriver driver;


    public ExtentReports reports = ExtentReportManager.getReportInstance();
    public ExtentTest logger;


    public void invokeBrowser(String browserName) {

        try {

            if (browserName.equalsIgnoreCase("Chrome")) {
                setProperty("webdriver.chrome.driver", "c:\\Selenium\\Chrome\\chromedriver.exe");
                driver = new ChromeDriver();
            } else if (browserName.equalsIgnoreCase("Mozila")) {

                driver = new FirefoxDriver();
            } else if (browserName.equalsIgnoreCase("Opera")) {
                driver = new OperaDriver();
            } else if (browserName.equalsIgnoreCase("IE")) {
                driver = new InternetExplorerDriver();
            } else {
                driver = new SafariDriver();
            }
        } catch (Exception e) {
            // reportFail(e.getMessage());
            System.out.println(e.getMessage());
        }

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    //Select Date from Calendar
    public void selectDateIncalendar(String date) {

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date expectedDate = dateFormat.parse(date);

            String day = new SimpleDateFormat("dd").format(expectedDate);
            String month = new SimpleDateFormat("MMMM").format(expectedDate);
            String year = new SimpleDateFormat("yyyy").format(expectedDate);

            String expectedMonthYear = month + " " + year;

            while (true) {
                String displayDate = driver.findElement(By.className("dpTitleText")).getText();

                if (expectedMonthYear.equals(displayDate)) {

                    driver.findElement(By.xpath("//td[text()= '" + day + "']")).click();

                    break;
                } else if (expectedDate.compareTo(currentDate) > 0) {
                    driver.findElement(By.xpath("//*[@id='datepicker']/table/tbody/tr[1]/td[4]/button")).click();
                } else {
                    driver.findElement(By.xpath("//*[@id='datepicker']/table/tbody/tr[1]/td[2]/button")).click();
                }

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


        public void waitForPageLoad() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int i = 0;
        while (i != 180) {
            String pageState = (String) js.executeScript("return document.readyState;");
            if (pageState.equals("complete")) {
                break;
            } else {
                waitLoad(1);
            }
        }

        waitLoad(2);

        i = 0;
        while (i != 180) {
            Boolean jsState = (Boolean) js.executeScript("return window.jQuery != undefined && jQuery.active == 0;");
            if (jsState) {
                break;
            } else {
                waitLoad(1);
            }
        }
    }

    public void waitLoad(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    @AfterMethod
    public void flushReports() {
        reports.flush();
    }
}

