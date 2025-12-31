package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class HtmlReporter {

    public static ExtentReports getReportObject(){

        String reportPath = System.getProperty("user.dir") + "/target/reports/index.html";

        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath);
        extentSparkReporter.config().setDocumentTitle("Test Results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(extentSparkReporter);

        return extent;
    }

}
