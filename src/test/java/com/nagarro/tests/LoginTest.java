package com.nagarro.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nagarro.base.BaseClass;
import com.nagarro.config.Configs;

public class LoginTest extends BaseClass {

    // before and after class can be used in case we have specific req for class settings
    @BeforeClass
    public void beforeClass() {

    }

    @AfterClass
    public void afterClass() {

    }

    @Test(groups = { "Smoke", "Regression" })
    public void validLoginLogout() {
        // launching and moving to page is part of before method
        test = startTest("Valid Login Logout", "Checking valid login with correct credentials and then logout");
        test.assignCategory("Login");
        // input data
        final String struser = Configs.getPropertyInputData("user"), password = Configs.getPropertyInputData("password");
        logger.logInfo("Logging with valid credentials");
        signIn.login(struser, password);
        logger.logInfo("Validating if user is on logged in user dashboard or not");
        signIn.validateOnDashboard();
        logger.logInfo("Logging out of application");
        header.signOut();
        logger.logInfo("Validate user on login screen again");
        signIn.validateOnLoginScreen();

    }

    @Test(dataProvider = "invalidLogin", groups = { "Regression" })
    public void invalidLogin(final String[] inputData, final String expMsg) {
        // launching and moving to page is part of before method
        test = startTest("Invalid credentials", "checking with invalid credentials user:" + inputData[0] + " password:" + inputData[1]);
        test.assignCategory("Login");
        // input data
        final String struser = inputData[0], password = inputData[1];
        logger.logInfo("Logging in with missing or invalid credentials");
        logger.logInfo("Test Data, username:" + struser + ", password:" + password);
        System.out.println("User details email->" + struser + " password->" + password + " expected error message->" + expMsg);
        signIn.login(struser, password);
        logger.logInfo("Validating if correct error message is shown or not, expected :" + expMsg);
        signIn.validateErrorMessage(expMsg);
    }

    @DataProvider(name = "invalidLogin")
    public static Object[][] invalidLoginData() {
        return new Object[][] { { new String[] { "abc@yopmail.com", "1234566" }, "There is 1 errorAuthentication failed." },
                { new String[] { "abc@yopmail.com", "" }, "There is 1 errorPassword is required." },
                { new String[] { "", "sdfwr3535" }, "There is 1 errorAn email address required." },
                // {new String[]{"sdfsfs","232sdw3sdf"}, "There is 1 errorInvalid email address."},
        };
    }
}
