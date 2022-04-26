package com.nagarro.pages;

import com.nagarro.utils.web.Web_Lib;
import com.nagarro.utils.reporting.AssertionLog;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class ContactUs {
    WebDriver driver;
    Web_Lib webLib;
    AssertionLog assertionLog;
    // locators
    String[] loc_navigation = {"class","navigation_page"};
    String[] loc_contactForm = {"class","contact-form-box"};
    String[] loc_msgsuccess = {"classcontains","alert-success"};
    String[] loc_msg = {"id","message"};
    String[] loc_submit = {"id","submitMessage"};
    String[] loc_service = {"id","id_contact"};
    String[] loc_email = {"id","email"};
    String[] loc_ref = {"id","id_order"};
    String[] loc_contact = {"id","contact-link"};

    /****
     * Description : constructor for this page
     * Usage :
     * parameter: webdriver, AssertionLog for loggin purpose
     */
    public ContactUs(WebDriver driver, AssertionLog assertionLog){
        this.driver = driver;
        webLib = new Web_Lib(this.driver);
        this.assertionLog = assertionLog;
    }


    /****
     * Description : this function will verify if user is on contact page or not
     * Usage :
     * parameter:
     */
    public void verifyOnContactPage(){
        try {
            String actual = webLib.getText(loc_navigation);
            String expected = "Contact";
            assertionLog.assertEquals(actual, expected, "Check text on navigation bar, expected "+ expected);
        }
        catch (Exception e) {
            assertionLog.assertEquals(false,true, "User is not on contact page");
        }
        boolean boolForm = webLib.checkElementPresent(loc_contactForm);
        assertionLog.assertEquals(boolForm,true, "Checking for presence of contact form, if present or not");
    }

    /****
     * Description : this function will fill contact detials and submit request
     * Usage :
     * parameter: input : hashmap with input detials to be filled
     */
    public void sendContactRequest(HashMap<String,String> input){
        webLib.click(loc_contact);
        webLib.selectItemDropDown(loc_service,input.get("subject"));
        webLib.setText(loc_email,input.get("email"));
        webLib.setText(loc_ref,input.get("refNo"));
        webLib.setText(loc_msg,input.get("message"));
        // attachment is yet to be done
        webLib.click(loc_submit);

    }



    public void validateContactSuccess(){
        // we can take exp message as input as well
        try{
            String actM = webLib.getText(loc_msgsuccess);
            String expM = "Your message has been successfully sent to our team.";
            String strM = "Expected :" + expM + "|Actual :"+actM;
            assertionLog.assertEquals(actM.equals(expM), true, "Message is not as expected, " + strM);
        }
        catch(Exception e){
            assertionLog.assertEquals(false,true, "Success message is not shown");
        }
    }

}
