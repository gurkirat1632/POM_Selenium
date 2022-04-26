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
    public void addItemViaQuickView() {
        // launching and moving to page is part of before method
        test = startTest("Add Item via Quick view", "Adding item via quick view after hovering and validating its presence in cart");
        test.assignCategory("AddToCart");
        // input dta
        final String strSearch = "printed";
        final int qty = 1;
        signIn.login(Configs.getPropertyInputData("user"), Configs.getPropertyInputData("password"));
        logger.logInfo("Search product via keyword " + "printed");
        header.searchItem(strSearch);
        logger.logInfo("Add item via quick view after hovering over image");
        final String[] prodData = results.addFirstItemToCart_QuickView(qty);
        logger.logInfo("Validate if added item is present with name " + prodData[0]);
        cart.validateItemPresent(prodData[0]);
        logger.logInfo("Validate item added with correct detials in cart");
        cart.validateItemDetails(prodData[0], prodData[1], prodData[2]);
        logger.logInfo("Validate cart summary calculations");
        cart.validateCartTotals();
    }

    @Test(groups = { "GuestUser", "Smoke", "Regression" })
    public void addItemDirectlyWithQuantity() {
        test = startTest("Add Item from search results", "Adding item from search results and validating its presence in cart");
        test.assignCategory("AddToCart");
        // input data
        final String strSearch = "printed";
        final int qty = 1; // setting quantity to 1 for now as the option to edit quantity is no more on site

        logger.logInfo("Search product via keyword " + "printed");
        header.searchItem(strSearch);
        logger.logInfo("Add first item from list via add to cart button");
        final String[] prodData = results.addFirstItemToCart(qty);
        logger.logInfo("Validate if added item is present with name " + prodData[0]);
        cart.validateItemPresent(prodData[0]);
        logger.logInfo("Validate item added with correct detials in cart");
        cart.validateItemDetails(prodData[0], prodData[1], prodData[2]);
        logger.logInfo("Validate cart summary calculations");
        cart.validateCartTotals();
    }

}
