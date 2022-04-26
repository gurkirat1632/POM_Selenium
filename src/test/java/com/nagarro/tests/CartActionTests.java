package com.nagarro.tests;

import com.nagarro.base.BaseClass;
import com.nagarro.config.Configs;
import com.nagarro.utils.generic.Generic_Lib;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartActionTests extends BaseClass {

    // before and after class can be used in case we have specific req for class settings
    @BeforeClass
    public void beforeClass(){

    }
    @AfterClass
    public void afterClass(){

    }
    @BeforeMethod
    public void beforeFromClass(){

    }


    @Test(description = "This test will delete items from cart",groups = {"LoggedInUser", "Smoke", "Regression"})
    public void validateSearch_SortPrice(){
        // launching and moving to page is part of before method
        test = startTest("Cart Action delte Item","Delte product from cart");
        test.assignCategory("CartActions");
        //input data
        String search1="short",search2="printed";
        logger.logInfo("Validating delete from cart");
        signIn.login(Configs.getPropertyInputData("user"),Configs.getPropertyInputData("password"));
        String item1 = search_addFirstItem(search1)[0];
        String item2 = search_addFirstItem(search2)[0];
        header.clickCart();
        logger.logInfo("Delete one product :" + item1);
        logger.logInfo("Validate item (before deletion) should  be present with name " + item1);
        cart.validateItemPresent(item1);
        cart.deleteItem(item1);
        Generic_Lib.wait(3); // no loading just table refreshess
        logger.logInfo("Validate item (which is deleted) should not be present with name " + item1);
        cart.validateItemNotPresent(item1);
        logger.logInfo("Validate item (which is not deleted) should  be present with name " + item2);
        cart.validateItemPresent(item2);
    }

    public String[] search_addFirstItem(String search){
        logger.logInfo("Searching "+ search + " then adding first item" );
        header.searchItem(search);
        String[] prodData = results.addFirstItemToCart(1);
        return prodData;

    }


}
