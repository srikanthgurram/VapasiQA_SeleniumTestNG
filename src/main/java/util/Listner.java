package util;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Created by srikanth on 30/11/16.
 */
public class Listner implements ITestListener {
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Starting Test ->"+iTestResult.getName());
        System.out.println("Status: "+iTestResult.getStatus());
    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(iTestResult.getName()+" <- Test case passed");
        System.out.println("Status: "+iTestResult.getStatus());
    }

    public void onTestFailure(ITestResult iTestResult) {
        System.out.println(iTestResult.getName()+" <- Test case Failed");
        System.out.println("Status: "+iTestResult.getStatus());
    }

    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println(iTestResult.getName()+" <- Test case Skipped");
        System.out.println("Status: "+iTestResult.getStatus());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println(iTestResult.getTestName());
    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }
}