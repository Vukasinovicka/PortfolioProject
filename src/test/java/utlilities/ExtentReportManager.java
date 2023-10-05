package utlilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

public class ExtentReportManager {

    public static ExtentReports reports;

    public static ExtentReports getReportInstance(){

        if(reports == null){
            String reportName = DataUtils.getTimeStamp() + ".html";
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("C:\\Users\\marijana\\IdeaProjects\\SeleniumProject" + reportName));
            reports =  new ExtentReports();
            reports.attachReporter(htmlReporter);

            reports.setSystemInfo("OS", "Windows 10");
            reports.setSystemInfo("Environment", "UAT");
            reports.setSystemInfo("Build Number", "10.8.1");
            reports.setSystemInfo("Browser", "Chrome");

            htmlReporter.config().setDocumentTitle("UAT UI Automation Results");
            htmlReporter.config().setReportName("All Headlines UI Test Report");
            htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
            htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
        }

        return reports;
    }
}
