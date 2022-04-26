package com.nagarro.tests;

import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nagarro.base.BaseClass;

public class ContactUsTest extends BaseClass {

    // before and after class can be used in case we have specific req for class settings
    @BeforeClass
    public void beforeClass() {

    }

    @AfterClass
    public void afterClass() {

    }

    @Test(groups = { "GuestUser", "Regression" })
    public void validateContactUs() {
        // launching and moving to page is part of before method
        test = startTest("Validate Contact us", "Validate contact us link and contact process");
        test.assignCategory("HeaderActions");
        // input data
        final HashMap<String, String> map = new HashMap<String, String>() {

            {
                put("subject", "Customer service");
                put("email", "testcontact@yopmail.com");
                put("refNo", "Ref 2343");
                put("message", "This is testing message");
            }
        };
        logger.logInfo("click on contact us on top bar");
        header.clickContactUs();
        logger.logInfo("Validating user on contacts page, checking for presence of contact form");
        contactus.verifyOnContactPage();
        logger.logInfo("Filling and submitting contact form");
        contactus.sendContactRequest(map);
        logger.logInfo("Validating success message on contact form submission");
        contactus.validateContactSuccess();
    }

}
