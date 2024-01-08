package com.nagarro.pages;

import com.nagarro.config.Configs;
import com.nagarro.utils.web.Web_Lib;
import com.nagarro.utils.reporting.AssertionLog;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    WebDriver driver;
    Web_Lib webLib;
    AssertionLog assertionLog;
    WebDriverWait wait;
    // locators
    String[] loc_homeproof = {"id","shopping_cart_container"};
    String[] loc_Inventory_Items = {"class","inventory_item_name"};
    String[] loc_AddTocart = {"id","add-to-cart-sauce-labs-backpack"};



    public HomePage(WebDriver driver, AssertionLog assertionLog){
        this.driver = driver;
        webLib = new Web_Lib(this.driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(Configs.explicitTimeout));
        this.assertionLog = assertionLog;
    }

    /****
     * Description : this function will validate if user is on homepage of website
     * Usage :
     */
    public void validateUserOnHomePage(){
        // if object found then on home page
        try{
            webLib.getElement(loc_homeproof);
            assertionLog.assertEquals(true, true, "user is expected to be on home page and user is on home page");
        }
        catch (Exception e){
            assertionLog.assertEquals(false, true, "user is expected to be on home page but not there");
        }
    }
    
    
    /****
     * Description : this function will validate if user is on homepage of website
     * Usage :
     */
    public void clickOnProduct(String strProductName){
       
    	List<WebElement> listInventoryItems = webLib.getElements(loc_Inventory_Items);
    	
    	for(int i = 0; i < listInventoryItems.size()-1 ; i++)
    	{
    		if(listInventoryItems.get(i).getText().equals(strProductName))
    		{
    			webLib.click(listInventoryItems.get(i));
    			break;
    		}
    	}
    	

    }
    
    /****
     * Description : this function will validate if user is on homepage of website
     * Usage :
     */
    public void clickOnAddCartForBag(){
       
    	webLib.click(loc_AddTocart);
    	

    }
}
