package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTestNg implements IRetryAnalyzer {

    int count = 0;
    int maxTries = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(count < maxTries){
            count++;
            return true;
        }
        return false;
    }
}
