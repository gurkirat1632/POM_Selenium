package com.nagarro.base;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;
import com.nagarro.config.Configs;
import com.nagarro.pages.Cart;
import com.nagarro.pages.ContactUs;
import com.nagarro.pages.Header;
import com.nagarro.pages.HomePage;
import com.nagarro.pages.SearchResults;
import com.nagarro.pages.SignIn;
import com.nagarro.utils.project.Amazon_Lib;
import com.nagarro.utils.reporting.AssertionLog;
import com.nagarro.utils.reporting.ExtentReportLogger;
import com.nagarro.utils.reporting.ExtentReportManager;
import com.nagarro.utils.web.Web_Lib;

public class BaseClass {

    public WebDriver           driver;
    public SignIn              signIn;
    public Header              header;
    public ContactUs           contactus;
    public SearchResults       results;
    public ExtentTest          test;
    public AssertionLog        assertionLog;
    public ExtentReportLogger  logger;
    public ExtentReportManager reportManger;
    public Cart                cart;
    public HomePage            homepage;

    /****
     * Description : this function will be run before every test method, this will launch browser and then initialize all the page objects Usage : none
     * parameter: method : the method paramenter to get information regarding the test method run
     */
    @BeforeMethod(alwaysRun = true)
    public void beforeEveryMethod(final Method method) {
        System.out.println("Before method from base class");
        driver = launchBrowser();
        driver.manage().timeouts().implicitlyWait(Configs.implicitTimeout, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(Configs.getPropertyConfig("url"));

        assertionLog = new AssertionLog(driver, method.getName());
        logger = new ExtentReportLogger(driver, method.getName());

        // below objects can b movmoved to respective test classes
        signIn = new SignIn(driver, assertionLog);
        header = new Header(driver);
        contactus = new ContactUs(driver, assertionLog);
        cart = new Cart(driver, assertionLog);
        homepage = new HomePage(driver, assertionLog);
        results = new SearchResults(driver, assertionLog);

    }

    /****
     * Description : this function will be run after every test method, this will do required jobs like flushing report after every scenario and checking if
     * scenarios have passed or failed Usage : none parameter: ITestResult result : to get result of testmethod
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod(final ITestResult result) {
        System.out.println("After method from base class");
        final Map<Object, Object> resultTest = ExtentReportManager.getScenarioResult().getResult();
        if (Integer.valueOf(resultTest.get("Failed").toString()) > 0) {
            result.setStatus(2);
        }
        System.out.println(result.getStatus());
        markTestBasedOnStatus(result);
        // Generic_Lib.printHashMap(resultTest);
        logResultCounts(resultTest); // in extent report
        driver.quit();
        ExtentReportManager.flushReport();
    }

    /****
     * Description : this function will mark test case as failed or passed in extent report Usage : none parameter: ITestResult result : to get result of
     * testmethod
     */
    public void markTestBasedOnStatus(final ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.logFail(result.getThrowable(), "Test case has been failed.");
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.logSkipped(result.getThrowable(), "Test case has been skipped.");
        } else {
            System.out.println("Test case already passed"); // no need to log again
        }
    }

    /****
     * Description : this function will log the steps results in extent report Usage : none parameter: result : result map with information
     */
    public void logResultCounts(final Map<Object, Object> result) {
        final String[][] data = { { "Passed Steps", result.get("Passed").toString() }, { "Failed Steps", result.get("Failed").toString() },
                { "Warning Steps", result.get("Warning").toString() } };
        logger.logTable(data, "");

    }

    /****
     * Description : this function will run before suite, this will start the extent reports Usage : none
     */
    @BeforeSuite(alwaysRun = true)
    public void setExtentReport() {
        Amazon_Lib.moveResults();
        ExtentReportManager.initializeReporter();
    }

    /****
     * Description : this function will start the test Usage : parameter: strName : name of test case to be printed in extent report strDesc: description for
     * test
     */
    public ExtentTest startTest(final String strName, final String strDesc) {
        return ExtentReportManager.startTest(strName, strDesc);
    }

    /****
     * Description : this function willlaunch the browser, based on input from config file Usage :
     */
    public WebDriver launchBrowser() {
        final String browser = Configs.browser;
        return launchBrowser(browser);
    }

    /****
     * Description : this function will launch the browser, based on input Usage : parameter : browser browser name
     */
    public WebDriver launchBrowser(final String browser) {
        driver = Web_Lib.launchBrowser(browser);
        driver.manage().window().maximize();
        return driver;
    }

}
