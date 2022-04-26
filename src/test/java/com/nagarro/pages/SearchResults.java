package com.nagarro.pages;

import com.nagarro.config.Configs;
import com.nagarro.utils.generic.Generic_Lib;
import com.nagarro.utils.web.Web_Lib;
import com.nagarro.utils.reporting.AssertionLog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SearchResults {
    WebDriver driver;
    Web_Lib webLib;
    WebDriverWait wait;
    AssertionLog assertionLog;
    // locators
    String[] loc_qtyInput = {"id","quantity_wanted"};
    String[] loc_Item_obj = {"class","color-list-container"};
    String[] loc_AddToCart = {"xpath","//*[@title='Add to cart']"};
    String[] loc_ProceedChecout = {"xpath","//*[@title='Proceed to checkout']"};
    String[] loc_QuickView = {"class","quick-view"};
    String[] loc_framet = {"class","fancybox-iframe"};
    String[] loc_QVAddtoCart = {"id","add_to_cart"};
    String[] loc_PrdName = {"id","layer_cart_product_title"};
    String[] loc_PrdPrice = {"id","layer_cart_product_price"};
    String[] loc_PrdQty = {"id","layer_cart_product_quantity"};
    String[] loc_FilteredCat = {"class","cat-name"};
    String[] loc_FilteredCount = {"class","heading-counter"};
    String[] loc_Sort = {"id","selectProductSort"};
    String[] loc_Prices = {"xpath","//*[@itemprop='price']"};
    String[] loc_PrdContainer = {"class","product-container"};


    public SearchResults(WebDriver driver, AssertionLog assertionLog){
        this.driver = driver;
        webLib = new Web_Lib(this.driver);
        wait = new WebDriverWait(driver, Configs.explicitTimeout);
        this.assertionLog = assertionLog;
    }

    /****
     * Description : this function will set the quantity of product to be added to cart
     * Usage :
     * paramenter : quantity to be set
     */
    public void setQtyPopUp(int qty){
        webLib.setText(loc_qtyInput,Integer.toString(qty));
    }
    /****
     * Description : this function will add first item from search results to cart
     * Usage :
     */
    public String[] addFirstItemToCart(int qty){
        webLib.hoverOverElement(loc_Item_obj);
        Generic_Lib.wait(1);
        if(qty > 1) // quantity object has been removed from site hence settng 1 in test case
            setQtyPopUp(qty);
        webLib.click(loc_AddToCart);
        WebElement proceed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc_ProceedChecout[1])));
        String[] details = getProductDetails();
        proceed.click();
        return details;
    }

    /****
     * Description : this function will add first item to cart from results , via quick view optins
     * Usage :
     * paramenter : qty : quantity to product
     */
    public String[] addFirstItemToCart_QuickView(int qty){
        webLib.hoverOverElement(loc_Item_obj);
        webLib.click(loc_QuickView);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className(loc_framet[1])));
        if(qty > 1)
            setQtyPopUp(qty);
        webLib.click(loc_QVAddtoCart);
        Generic_Lib.wait(2);
        WebElement proceed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc_ProceedChecout[1])));
        String[] details = getProductDetails();
        proceed.click();
        return details;
    }

    /****
     * Description : this function will get product detilas like name,qty,price
     * Usage :
     */
  public String[] getProductDetails(){
        String productName = webLib.getText(loc_PrdName);
        String prodQty = webLib.getText(loc_PrdQty);
        String price = webLib.getText(loc_PrdPrice);
        return new String[]{productName,prodQty,price};
    }

    /****
     * Description : this function will get string from page (the filtered stirng)
     * Usage :
     */
    public String getFilteredString(){
        return webLib.getText(loc_FilteredCat);
    }
    /****
     * Description : this function will get the result count from screen--based on string shown
     */
    public int getResultCountMessage(){
        String strMsg = webLib.getText(loc_FilteredCount);
        String finalMsg = strMsg.split("There are ")[1].split(" products.")[0];
        return Integer.valueOf(finalMsg);
    }

    /****
     * Description : this function will sort results, based on input ot dropdown
     * Usage :
     * paramenter : strSortBy : value to be seleted  like price low to high
     */
    public void sortResults(String strSortBy){
        webLib.selectItemDropDown(loc_Sort,strSortBy);
    }

    /****
     * Description : this function will get list of prices from filtered/searched results
     * Usage :
     */
    public List<String> getFilteredProductPrices(){
        List<String> prices = new ArrayList<>();
        List<WebElement> listElements = webLib.getElements(loc_Prices);
        for(WebElement element : listElements){
            if(! element.getText().equals(""))
                prices.add(element.getText());
        }
        webLib.scrollPage();
        return prices;
    }

    /****
     * Description : this function will  get count of search resulted items
     * Usage :
     */
    public int getResultCount(){
        return webLib.getElements(loc_PrdContainer).size();
    }
}
