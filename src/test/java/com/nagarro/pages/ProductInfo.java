package com.nagarro.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nagarro.config.Configs;
import com.nagarro.utils.reporting.AssertionLog;
import com.nagarro.utils.web.Web_Lib;

public class ProductInfo {

    /****
     * Description : Yet to be implemented
     */
    WebDriver driver;
    Web_Lib webLib;
    WebDriverWait wait;
    AssertionLog assertionLog;
    // locators
    String[] loc_Product = {"xpath","//div[@class='inventory_details_name large_size']"};
    String[] loc_AddCart = {"xpath","//*[text()='Add to cart']"};



    public ProductInfo(WebDriver driver, AssertionLog assertionLog){
        this.driver = driver;
        webLib = new Web_Lib(this.driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(Configs.explicitTimeout));
        this.assertionLog = assertionLog;
    }
    
    public void validateIfRightProductInfo(String strProductName)
    {
    	//webLib.getText(loc_Product).equals(strProductName);
    	assertionLog.assertEquals(webLib.getText(loc_Product), strProductName,"Product info is actual is"+webLib.getText(loc_Product));
    }
    
    public void addItemToCart()
    {
    	webLib.click(loc_AddCart);
    	
    }


}
