package com.nagarro.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nagarro.base.BaseClass;
import com.nagarro.config.Configs;

public class AddItemTest extends BaseClass {

    // before and after class can be used in case we have specific req for class settings
    @BeforeClass
    public void beforeClass() {

    }

    @AfterClass
    public void afterClass() {

    }

    @BeforeMethod
    public void beforeMethodClass() {

    }

    @Test(groups = { "LoggedInUser", "Regression" }, alwaysRun = true)
    public void addItemToCart() {
        // launching and moving to page is part of before method
        test = startTest("Add Item to Cart", "Adding item to Cart from Home Page");
        test.assignCategory("AddToCart");
        // input dta
        final String strSearch = "Sauce Labs Backpack";
        signIn.login(Configs.getPropertyInputData("user"), Configs.getPropertyInputData("password"));
        logger.logInfo("Clicking on Left hand panel");
        header.clickLeftHandPanel();
        homepage.clickOnProduct(strSearch);
        productPage.validateIfRightProductInfo(strSearch);
        productPage.addItemToCart();
        header.clickLeftHandPanel();
        header.logOut();
        

    }

    @Test(groups = { "GuestUser", "Smoke", "Regression" })
    public void addItemDirectlyWithoutProductPage() {
        test = startTest("Add Item from Home Page", "Adding item from home page directly.");
        test.assignCategory("AddToCart");
        // input dta
        final String strSearch = "Sauce Labs Backpack";
        signIn.login(Configs.getPropertyInputData("user"), Configs.getPropertyInputData("password"));
        logger.logInfo("Clicking on Left hand panel");
        header.clickLeftHandPanel();
        homepage.clickOnAddCartForBag();
        //header.clickLeftHandPanel();
        header.logOut();
    }

}
