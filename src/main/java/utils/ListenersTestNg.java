package utils;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class ListenersTestNg extends BaseTest implements ITestListener {

    ExtentReports extent = HtmlReporter.getReportObject();
    ExtentTest test;
    ThreadLocal<ExtentTest> threadLocal = new ThreadLocal();

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        test = extent.createTest(result.getMethod().getMethodName());
        threadLocal.set(test); // resolver problema ao rodar paralelo

    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        threadLocal.get().fail(result.getThrowable());

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String filePath;

        try {
            filePath = getScreenshot(result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // take screenshot
        threadLocal.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        extent.flush();
    }
}
