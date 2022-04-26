package com.nagarro.utils.reporting;

import com.nagarro.config.Configs;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AssertionLog {

    WebDriver driver;
    ExtentReportLogger logger;
    String methodName;
    static boolean stopTestonFail = Configs.stopTestONFail;

    public AssertionLog(WebDriver driver, String methodName){
        this.driver = driver;
        this.methodName = methodName;
        logger = new ExtentReportLogger(driver,methodName);
    }

    /****
     * Description : this function will log the assertion output to extent file and do assertions
     * Usage :
     * parameter : expected value, actual value, description to print in report, and if image to be taken
     */
    public void assertEquals(Object actual, Object expected, String desc, boolean imageCapture){
        String strM = "Expected :" + expected.toString() + "|Actual :"+actual.toString();
        try {
            Assert.assertEquals(actual.toString(), expected.toString(), desc + "|Details:"+strM);
            Assert.assertTrue(true);
            logger.logPass(desc,imageCapture);
        }
        catch(AssertionError e){
            logger.logFail(desc + "|Details:"+strM,imageCapture);
            System.out.println("*********ASSERTION FAILED : EXPECTED "+ expected.toString() + " Actual :" + actual.toString());
            if(stopTestonFail) // if not continue with test
                Assert.assertEquals(actual, expected, desc);
        }

    }

    /****
     * Description : this function will log the assertion output to extent file and do assertions
     * Usage :
     * parameter : expected value, actual value, description to print in report
     */
    public void assertEquals(Object actual, Object expected, String desc){
        String strM = "Expected :" + expected.toString() + "|Actual :"+actual.toString();
        try {
            Assert.assertEquals(actual.toString(), expected.toString(), desc + "|Details:"+strM);
            Assert.assertTrue(true);
            logger.logPass(desc);
        }
        catch(AssertionError e){
            logger.logFail(desc + "|Details:"+strM);
            System.out.println("*********ASSERTION FAILED : EXPECTED "+ expected.toString() + " Actual :" + actual.toString());
            if(stopTestonFail) // if not continue with test
                Assert.assertEquals(actual, expected, desc);
        }

    }
}
