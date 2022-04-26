package com.nagarro.pages;

import com.nagarro.utils.web.Web_Lib;
import com.nagarro.utils.reporting.AssertionLog;
import org.openqa.selenium.WebDriver;

public class SignIn {

    WebDriver driver;
    Web_Lib webLib;
    AssertionLog assertionLog;

    // locators
    String[] loc_signInDb = {"class","login"};
    String[] loc_signIn = {"id","SubmitLogin"};
    String[] loc_user = {"id","email"};
    String[] loc_password = {"id","passwd"};
    String[] loc_error = {"classcontains","alert-danger"};
    String[] loc_signOut = {"class","logout"};

    public SignIn(WebDriver driver, AssertionLog assertionLog){
        this.driver = driver;
        webLib = new Web_Lib(this.driver);
        this.assertionLog = assertionLog;
    }

    public void setUserName(String userName) {
        webLib.setText(loc_user,userName);
    }

    public void setPassword(String password) {
        webLib.setText(loc_password,password);
    }

    public String getErrorMsg() {
        return webLib.getText(loc_error);
    }

    public void clickSignIn(){
        webLib.click(loc_signIn);
    }

    public void movetoSignInPage(){
        // click sign in on homepage as usually lands on home page but same ispresent on signin page as well
        webLib.click(loc_signInDb);
    }

    public void login(String userName, String password){
        movetoSignInPage();
        setUserName(userName);
        setPassword(password);
        clickSignIn();
    }

    /****
     * Description : this function will validate error message shown on page
     * Usage :
     * paramenter : strExp : expected stirng message
     */
    public void validateErrorMessage(String strExp){
        String strAct = getErrorMsg();
        strAct = strAct.replace("\n","");
        assertionLog.assertEquals(strAct, strExp,"Error Message on incorrect Login details, expected :"+ strExp);
    }

    /****
     * Description : this function will validatei f user on logged in user dashboard
     * Usage :
     */
    public void validateOnDashboard(){
        boolean ifUserOnDashboard = checkUserOnDashboard();
        assertionLog.assertEquals(ifUserOnDashboard, true,"User should be on dashboard after login");
     }

    /****
     * Description : this function will return if user on logged in user dashboard
     * Usage :
     */
    public boolean checkUserOnDashboard(){
        try {
            return webLib.getElement(loc_signOut).isDisplayed();
        }
        catch(Exception e) { return false;}
    }

    public boolean checkUserOnLogin(){
        try {
            return webLib.getElement(loc_user).isDisplayed();
        }
        catch(Exception e) { return false;}
    }

    /****
     * Description : this function will validate if user on log in screen
     * Usage :
     */
    public void validateOnLoginScreen(){
        boolean ifUserOnLogin = checkUserOnLogin();
        assertionLog.assertEquals(ifUserOnLogin, true,"User should be on login screen after logout");

    }
}
