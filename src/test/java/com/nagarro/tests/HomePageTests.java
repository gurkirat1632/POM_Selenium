package com.nagarro.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nagarro.base.BaseClass;

public class HomePageTests extends BaseClass {

    // before and after class can be used in case we have specific req for class settings
    @BeforeClass
    public void beforeClass() {

    }

    @AfterClass
    public void afterClass() {

    }

    @Test(dataProvider = "cat_subcat", groups = { "GuestUser", "Smoke" })
    public void validateSearchCategory_subCategory(final String strCategory, final String strSubCategory) {
        // launching and moving to page is part of before method
        test = startTest("Search by category subcategory", "Validate search by acategory and sub category, also validate number of results");
        test.assignCategory("SearchByCategory");
        logger.logInfo("Test Data, category:" + strCategory + ", subcategory:" + strCategory);
        logger.logInfo("Hover over category and click on subcategory");
        header.clickCategorySubCategory(strCategory, strSubCategory);
        final String actualFilter = results.getFilteredString().trim();
        logger.logInfo("Validate filtered text on page");
        assertionLog.assertEquals(actualFilter.toLowerCase(), strSubCategory.toLowerCase(), "Validating Filtering/selection of subcategory");
        final int expectedCount = results.getResultCountMessage();
        final int actualCount = results.getResultCount();
        logger.logInfo("Validate filtered items count with count shown on page");
        assertionLog.assertEquals(actualCount, expectedCount, "Validating actual count of filtered items with count of items in message");

    }

    @DataProvider(name = "cat_subcat")
    public static Object[][] invalidLoginData() {
        return new Object[][] { new String[] { "Women", "tops" },
                // currently running over only one flow, if need multiple then need to updatelocators accordingly on header page file
        };
    }

    @Test(groups = { "Regression", "Smoke" })
    public void validateHomeLogo() {
        test = startTest("Logo action", "Validate user on home page on click of logo");
        test.assignCategory("Header");
        // input data
        final String search1 = "printed";
        logger.logInfo("Searching item");
        header.searchItem(search1);
        header.clickHomeLogo();
        logger.logInfo("Validate user on home page on click of logo from searched results");
        homepage.validateUserOnHomePage();
        logger.logInfo("Moving to cart");
        header.clickCart();
        header.clickHomeLogo();
        logger.logInfo("Validate user on home page on click of logo from cart");
        homepage.validateUserOnHomePage();

    }

}
