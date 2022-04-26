package com.nagarro.utils.reporting;

import com.nagarro.config.Configs;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

public class TNGListener implements ITestListener, IRetryAnalyzer, IAnnotationTransformer {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("****Starting Test****");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("****Test Successful****");
        Map<Object, Object> resultTest = ExtentReportManager.getScenarioResult().getResult();
        if(Integer.valueOf(resultTest.get("Failed").toString()) > 0) // needed when we decide not to stop test on validation failures
            result.setStatus(2);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("****Test Failed****");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("****Test Skipped****");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("****Test Failed withing success percentage****");
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("****Test Started****");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("****Test Finished****");
    }

    int currentCount = 0;
    int retryMaxCount = Configs.retryCount;
    @Override
    public boolean retry(ITestResult result) {

        if(currentCount < retryMaxCount)
        {
            currentCount++;
            return true;
        }
        return false;
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(TNGListener.class);
    }
}
