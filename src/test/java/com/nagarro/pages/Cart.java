package com.nagarro.pages;

import com.nagarro.config.Configs;
import com.nagarro.utils.generic.Generic_Lib;
import com.nagarro.utils.web.Web_Lib;
import com.nagarro.utils.reporting.AssertionLog;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cart {

    WebDriver driver;
    Web_Lib webLib;
    WebDriverWait wait;
    AssertionLog assertionLog;
    // locators
    String[] loc_cartIcon = {"class","shopping_cart"};
    String[] loc_deleteIcon = {"class","icon-trash"};
    String[] loc_tableCart = {"id", "cart_summary"};
    String[] loc_row = {"xpath", ".//tbody/tr"};
    String[] loc_PrdName = {"class", "product-name"};
    String[] loc_Qty = {"xpath", ".//*[@type='text']"};
    String[] loc_Totprice = {"xpath", ".//*[contains(@id,'total_product_price')]"};
    String[] loc_price = {"xpath", ".//*[contains(@class,'special-price')]"};
    String[] loc_price2 = {"xpath", ".//*[contains(@id,'product_price_')]"};
    String[] loc_delete = {"class", "cart_quantity_delete"};

    /****
     * Description : constructor for this file, to set driver, and libraries and waits
     * Usage :
     * parameter : driver, and assertionlog which will be used to log results for validations and extent reports
     */
    public Cart(WebDriver driver, AssertionLog assertionLog){
        this.driver = driver;
        webLib = new Web_Lib(this.driver);
        wait = new WebDriverWait(driver, Configs.explicitTimeout);
        this.assertionLog = assertionLog;
    }

    /****
     * Description : this function will clear the cart , delete all items
     * Usage :
     * parameter :
     */
    public void clearCart(){
        webLib.click(loc_cartIcon);
        while(true) {
            try {
                webLib.click(loc_deleteIcon);
                Generic_Lib.wait(1);
            }
            catch(Exception e){
                break;
            }
        }
    }

    /****
     * Description : this function will return row data for any cart item
     * Usage :
     * parameter : row element,tr for whom data is required
     */
    public HashMap<String, String> getRowData(WebElement rowElement){
        HashMap<String,String> cartItem = new HashMap<String,String>();
        WebElement element = webLib.getElement(rowElement,loc_PrdName[0],loc_PrdName[1]);
        cartItem.put("name",element.getText());
        element = webLib.getElement(rowElement, loc_Qty[0],loc_Qty[1]);
        cartItem.put("qty",element.getAttribute("value"));
        element = webLib.getElement(rowElement,loc_Totprice[0],loc_Totprice[1]);
        cartItem.put("totalPrice",element.getText());
        try {
            element = webLib.getElement(rowElement, loc_price[0],loc_price[1]);
        }
        catch(Exception e){
            element = webLib.getElement(rowElement, loc_price2[0],loc_price2[1]);

        }
        cartItem.put("unitPrice",element.getText());

        return cartItem;
    }

    public HashMap<String, String> getCartItem(){
        WebElement table = webLib.getElement(loc_tableCart[0],loc_tableCart[1]);
        WebElement tablerow = webLib.getElement(table, loc_row[0],loc_row[1]);
        return getRowData( tablerow);

    }

    /****
     * Description : this function will return list of all items from cart
     * Usage :
     * parameter:
     */
    public List<HashMap<String, String>> getCartItemList(){
        List<HashMap<String,String>> cartItemList = new ArrayList<HashMap<String,String>>();
        WebElement table = webLib.getElement(loc_tableCart[0],loc_tableCart[1]);

        List<WebElement> elements = webLib.getElements(table, loc_row[0],loc_row[1]);
        for(int i=0;i<elements.size(); i++)
            cartItemList.add(getRowData( elements.get(i)));

        printListHashmap(cartItemList);
        return cartItemList;
    }

    /****
     * Description : this function will print the list of hashmap
     * Usage :
     * parameter: list of h ash map
     */
    public void printListHashmap(List<HashMap<String, String>> listData){
        for(int i=0;i<listData.size(); i++) {
            Generic_Lib.printHashMap(listData.get(i));
        }
    }

    /****
     * Description : this function will validate if an item is there on cart or not
     * Usage :
     * parameter: prdName product name of item to be checked
     */
    public void validateItemPresent(String prdName){
        List<HashMap<String, String>> listItems = getCartItemList();
        boolean found = false;
        for(int i=0;i<listItems.size(); i++)
            if(listItems.get(i).get("name").equalsIgnoreCase(prdName)) { found = true; break; }
        assertionLog.assertEquals(found,true, "Item shoule be found in cart with product name " + prdName);
    }
    /****
     * Description : this function will validate if an item should not be present
     * Usage :
     * parameter: prdName product name of item to be checked
     */
    public void validateItemNotPresent(String prdName){
        List<HashMap<String, String>> listItems = getCartItemList();
        boolean found = false;
        for(int i=0;i<listItems.size(); i++)
            if(listItems.get(i).get("name").equalsIgnoreCase(prdName)) { found = true; break; }
        assertionLog.assertEquals(found,false, "Item should not be found with name " + prdName);
    }

    /****
     * Description : this function will return detials of an product item from cart table, all details in hashmap form
     * Usage :
     * parameter: prdName product name of item to be checked
     */
    public HashMap<String, String> getItemDetils(String prdName){
        List<HashMap<String, String>> listItems = getCartItemList();
        for(int i=0;i<listItems.size(); i++)
            if(listItems.get(i).get("name").equalsIgnoreCase(prdName)) { return listItems.get(i); }
        return null;
    }
    /****
     * Description : this function will validate if item details are as expected from cart item
     * Usage :
     * parameter: prdName product name of item to be checked
     * qty : quantity
     * price : unit price of item
     */
    public void validateItemDetails(String prdName, String qty, String price){
        HashMap<String, String> itemDetails = getItemDetils(prdName);
        String strM = "Expected -> name:"+prdName + ", price:"+ price + ", quantity:"+ qty;
        if(itemDetails == null) {
            assertionLog.assertEquals(false, true, "Product not found in cart, " + strM);
            return;
        }
        boolean detMatch = false;
        if(itemDetails.get("name").equalsIgnoreCase(prdName)
            && itemDetails.get("qty").equalsIgnoreCase(qty)
            && itemDetails.get("totalPrice").equalsIgnoreCase(price)
            )
        { detMatch = true;  }
        strM += ", Actual ->" + Generic_Lib.hashMaptoString(itemDetails);
        assertionLog.assertEquals(detMatch,true, "Details are mismatching, " + strM);
    }

    /****
     * Description : this function will validate cart details, prices in the cart, total, taxes and other calculaitons
     * Usage :
     * parameter:
     */
    public void validateCartTotals(){
        Double totalItems = 0.0;
        WebElement table = webLib.getElement("id", "cart_summary");
        List<WebElement> elements = webLib.getElements(table, "xpath", ".//*[contains(@id,'total_product_price')]");
        for(WebElement element : elements)
            totalItems += getPrice(element.getText());
        Double actualSum = getPrice(webLib.getElement(table, "id","total_product").getText());
        String strM = "Total sum of items " + totalItems.toString() + "Expected "+ actualSum.toString();
        assertionLog.assertEquals(actualSum.equals(totalItems),true,"Sum of items should be equal to Total Products "+ strM, false);

        Double shipping = getPrice(webLib.getElement(table, "id","total_shipping").getText());
        Double sumWithoutTax = getPrice(webLib.getElement(table, "id","total_price_without_tax").getText());
        Double tax = getPrice(webLib.getElement(table, "id","total_tax").getText());
        Double finalAmount = getPrice(webLib.getElement(table, "id","total_price_container").getText());

        boolean validation = (sumWithoutTax.equals(shipping+actualSum)) && (finalAmount.equals(tax+sumWithoutTax));
        assertionLog.assertEquals(validation,true,"Check Total sum calculations on cart ", true);

    }


    /****
     * Description : this function will delte item from cart
     * Usage :
     * parameter: prdName product name of item to be checked
     */
    public void deleteItem(String productName){
        WebElement table = webLib.getElement(loc_tableCart[0],loc_tableCart[1]);
        List<WebElement> elements = webLib.getElements(table, loc_row[0],loc_row[1]);

        for(int i=0;i<elements.size(); i++)
            if(webLib.getElement(elements.get(i),loc_PrdName[0],loc_PrdName[1]).getText().equalsIgnoreCase(productName))
            {
                webLib.click(webLib.getElement(elements.get(i),loc_delete[0],loc_delete[1]));
                break;
            }

    }

    /****
     * Description : this function will convert string to double price
     * Usage :
     * parameter: price with currency
     */
    public Double getPrice(String price){
        return Double.valueOf(price.replace("$",""));
    }
}
