package com.nagarro.utils.reporting;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;

import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.nagarro.config.Configs;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromPath;

public class ExtentReportLogger {

    WebDriver driver = null;
    String testName;
    static boolean screenCaptureonSuccess = Configs.screenCaptureonSuccess;
    static boolean screenCaptureonFail = Configs.screenCaptureonFail;
    static boolean screenCaptureonWarning = Configs.screenCaptureonWarning;

    static String pathSS = Configs.currentResultPath;
    public ExtentReportLogger(){

    }
    public ExtentReportLogger(WebDriver driver, String testName){
        this.driver = driver;
        this.testName = testName;
    }

    public void markTestFail(){
        ExtentTest test = ExtentReportManager.getTest();
        test.fatal("Check earlier errors/warnings");
    }
    public void logPass(String desc){
        String strpath = "";
        ExtentTest test = ExtentReportManager.getTest();
        ExtentReportManager.getScenarioResult().passCount += 1;
        if(screenCaptureonSuccess) {
            String strImageName = this.testName + getTimestamp() + ".png";
            strpath = takeScreenShot(pathSS + "\\Screenshots\\" + strImageName);
            try {
                 test.pass(desc + "\nSee Image  :" ,MediaEntityBuilder.createScreenCaptureFromPath("Screenshots\\" + strImageName).build());
            }
            catch(Exception e){}
        }
        else
            test.pass(desc + "\nSee Image below :" );
    }

    public void logPass(String desc, boolean imageCapture){
        String strpath = "";
        ExtentTest test = ExtentReportManager.getTest();
        ExtentReportManager.getScenarioResult().passCount += 1;
        if(imageCapture) {
            String strImageName = this.testName + getTimestamp() + ".png";
            strpath = takeScreenShot(pathSS + "\\Screenshots\\" + strImageName);
            try {
                test.pass(desc + "\nSee Image  :" ,MediaEntityBuilder.createScreenCaptureFromPath("Screenshots\\" + strImageName).build());
            }
            catch(Exception e){}
        }
        else
            test.pass(desc + "\nSee Image below :" );
    }
    public void logFail(String desc){
        String strpath = "";
        ExtentTest test = ExtentReportManager.getTest();
        ExtentReportManager.getScenarioResult().failCount += 1;
        if(screenCaptureonFail) {
            String strImageName = this.testName + getTimestamp() + ".png";
            strpath = takeScreenShot(pathSS + "\\Screenshots\\" + strImageName);
            try {
                test.fail(desc + "\nSee Image  :", MediaEntityBuilder.createScreenCaptureFromPath("Screenshots\\" + strImageName).build());
                test.addScreenCaptureFromPath("Screenshots\\" + strImageName);            }

            catch(Exception e){}
        }
        else
            test.fail(desc);
    }


    public void logFail(String desc, boolean imageCapture){
        String strpath = "";
        ExtentTest test = ExtentReportManager.getTest();
        ExtentReportManager.getScenarioResult().failCount += 1;
        if(imageCapture) {
            String strImageName = this.testName + getTimestamp() + ".png";
            strpath = takeScreenShot(pathSS + "\\Screenshots\\" + strImageName);
            try {
                test.fail(desc + "\nSee Image  :", MediaEntityBuilder.createScreenCaptureFromPath("Screenshots\\" + strImageName).build());
                test.addScreenCaptureFromPath("Screenshots\\" + strImageName);            }

            catch(Exception e){}
        }
        else
            test.fail(desc);
    }

    public void logSkipped(String desc){
        String strpath = "";
        ExtentTest test = ExtentReportManager.getTest();
        test.skip("Test skipped");

    }
    public void logSkipped(Throwable t, String strDesc){
        String strpath = "";
        ExtentTest test = ExtentReportManager.getTest();
        logSkipped(strDesc + " Test skipped");
    }

    public void logFail(Throwable t, String strDesc){
        String strpath = "";
        ExtentTest test = ExtentReportManager.getTest();
        if(t != null) // will not be null when assertions failed and we are continuing with test
        {
            test.fail(t);
            ExtentReportManager.getScenarioResult().failCount += 1;
        }
        logFail(strDesc + " Some Steps failed or exception occured, check other failures");

    }
    public void logWarning(String desc){
        String strpath = "";
        ExtentTest test = ExtentReportManager.getTest();
        ExtentReportManager.getScenarioResult().warningCount += 1;
        if(screenCaptureonWarning) {
            String strImageName = this.testName + getTimestamp() + ".png";
            strpath = takeScreenShot(pathSS + "\\Screenshots\\" + strImageName);
            try {
                test.warning(desc + "\nSee Image  :", MediaEntityBuilder.createScreenCaptureFromPath("Screenshots\\" + strImageName).build());
                 }
            catch(Exception e){}
        }
        else
            test.log(Status.WARNING, desc);
    }
    public void logInfo(String desc){
        ExtentReportManager.getTest().log(Status.INFO,desc);
    }

    public String takeScreenShot(String strPath){
        try {
            TakesScreenshot scrShot = ((TakesScreenshot) this.driver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File(strPath);
            FileUtils.copyFile(SrcFile, DestFile);
            return strPath;
        }
        catch (Exception e){
            System.out.println("Due to some error not able to take screenshot" );
            e.printStackTrace();
            throw new RuntimeException("Exception during capturing screenshot");

        }
    }
    // get hours and mins and secs only, not reusing datelib as it makes this file depend on others
    public static String getTimestamp(){
        String currentTime = new SimpleDateFormat("hh:mm:ss").format(new Date());
        currentTime = currentTime.replace(" ", "_t_").replace("-", "_").replace(":","_");
        return currentTime;
    }

    // stauts is not used now , we can use it later if we want to mark this step as paased or failed or warning
    public void logTable(String[][] data, String status){
        Markup m = MarkupHelper.createTable(data);
        ExtentReportManager.getTest().log(Status.INFO, m);
    }

}
