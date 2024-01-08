package com.nagarro.pages;

import com.nagarro.utils.generic.Generic_Lib;
import com.nagarro.utils.web.Web_Lib;
import org.openqa.selenium.WebDriver;

public class Header {

    WebDriver driver;
    Web_Lib webLib;

    // locators
    String[] loc_search = {"id","search_query_top"};
    String[] loc_submit = {"name","submit_search"};
    String[] loc_signout = {"class","logout"};
    String[] loc_contactus = {"id","contact-link"};
    String[] loc_CatWoman = {"xpath","//*[@title='Women']"};
    String[] loc_subCatTops = {"xpath","//*[@title='Tops']"};
    String[] loc_subCatDresses = {"xpath","//*[@title='Dresses']"};
    String[] loc_catDresses = {"xpath","//*[@title='Dresses']"};
    String[] loc_subCatCasual = {"xpath","//*[@title='Casual Dresses']"};
    String[] loc_subCatEvening = {"xpath","//*[@title='Evening Dresses']"};
    String[] loc_cart = {"xpath","//a[@title='View my shopping cart']"};
    String[] loc_logo = {"id","header_logo"};
    String[] loc_LHP = {"id","react-burger-menu-btn"};
    String[] loc_LogOut = {"id","logout_sidebar_link"};


    public Header(WebDriver driver){
        this.driver = driver;
        webLib = new Web_Lib(this.driver);
    }

    /****
     * Description : this function will click on signout
     * Usage :
     */
    public void signOut(){
        webLib.click(loc_signout);
    }
    /****
     * Description : this function will search item by filling value in field
     * Usage :
     * Paramenter : strSearch value to be searched
     */
    public void clickLeftHandPanel(){
        webLib.click(loc_LHP);
    }

    /****
     * Description : this function will click on logo
     * Usage :
     */
    public void clickHomeLogo(){
        webLib.click(loc_logo);
    }
    /****
     * Description : this function will click on contact us
     * Usage :
     */
    public void clickContactUs(){
        webLib.click(loc_contactus);
    }
    /****
     * Description : this function will click on cart
     * Usage :
     */
    public void clickCart(){ webLib.click(loc_cart); }

    /****
     * Description : this function will hover over category then clck on subcategory
     * Usage :
     * paramenter : category and subcategory values
     */
    public void clickCategorySubCategory(String category, String subCategory){
        if(category.equalsIgnoreCase("women")){
            webLib.hoverOverElement(loc_CatWoman);
            Generic_Lib.wait(3);
            if(subCategory.equalsIgnoreCase("tops"))
                webLib.click(loc_subCatTops);
            else if(subCategory.equalsIgnoreCase("dresses"))
                    webLib.click(loc_subCatDresses);
            else
                System.out.println("Not found valid subcategory"); // this case is to be handled
            }
        else if(category.equalsIgnoreCase("dresses")){
            webLib.hoverOverElement(loc_catDresses);
            if(subCategory.equalsIgnoreCase("casual"))
                webLib.click(loc_subCatCasual);
            else if(subCategory.equalsIgnoreCase("evening"))
                webLib.click(loc_subCatEvening);
            else
                System.out.println("Not found valid subcategory"); // this case is to be handled
        }
        else
            System.out.println("Invalid category");
    }
    
    /****
     * Description : this function will search item by filling value in field
     * Usage :
     * Paramenter : strSearch value to be searched
     */
    public void logOut(){
        webLib.click(loc_LogOut);
    }


}
