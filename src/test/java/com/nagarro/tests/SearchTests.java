package com.nagarro.tests;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nagarro.base.BaseClass;
import com.nagarro.utils.project.Amazon_Lib;

public class SearchTests extends BaseClass {

    // before and after class can be used in case we have specific req for class settings
    @BeforeClass
    public void beforeClass() {

    }

    @AfterClass
    public void afterClass() {

    }

    @BeforeMethod
    public void beforeFromClass() {

    }

    @Test(description = "This test will check sorting of filtered items based on price, low to highs", groups = { "GuestUser", "Smoke", "Regression" })
    public void validateSearch_SortPrice() {
        // launching and moving to page is part of before method
        test = startTest("Sorting of Filtered Items", "This test will check sorting of filtered items based on price, low to highs");
        test.assignCategory("SortingFilteredItems");
        // input data
        final String strSearch = "printed", strSorting = "Price: Lowest first";
        logger.logInfo("Validating sorting of items based on price");
        logger.logInfo("Searching for Printed dresses");
        header.searchItem(strSearch);
        // sort by price
        // results.sortResults("Price: Lowest first");
        logger.logInfo("Sorting results based on " + "Price: Highest first");
        results.sortResults(strSorting);
        final List<String> prices = results.getFilteredProductPrices();
        final boolean actual = Amazon_Lib.validate_List_Sorted(prices);
        logger.logInfo("Validating if results are sorted as per price or not");
        assertionLog.assertEquals(actual, true, "Validating items sorted on price or not, actual list of prices " + prices);
    }

}
