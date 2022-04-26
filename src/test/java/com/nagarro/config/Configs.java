package com.nagarro.config;

import com.nagarro.utils.generic.Date_Lib;
import org.openqa.selenium.remote.SessionId;

import java.io.FileReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

public class Configs {

    public static String dateFormat = "dd-MMM-yyyy hh:mm"; // for extent report
    public static String strProjectPath = System.getProperty("user.dir");
    public static String strConfigPath = System.getProperty("user.dir") + "\\src\\test\\resources\\configs\\";
    public static Properties propsConfig = readPropertiesFile("AutomationPractice.properties");
    public static Properties propsInput = readPropertiesFile("InputData.properties");
    public static String strWebDriverPath = System.getProperty("user.dir") + "\\src\\test\\resources\\webdrivers\\";
    public static String strResultPath = System.getProperty("user.dir") + "\\" + getPropertyConfig("resultFolder") + "\\";
    public static String currentResultPath = strResultPath + Date_Lib.getCurrentDate(dateFormat);
    public static String strReportPath =  currentResultPath + "\\result.html";
    public static String strArchReportPath = System.getProperty("user.dir") + "\\" + getPropertyConfig("archiveFolder") + "\\";
    public static String browser = getPropertyConfig("browser");
    public static boolean screenCaptureonSuccess = getBoolConfigValue(getPropertyConfig("screenCaptureSuccess"));
    public static boolean screenCaptureonFail = getBoolConfigValue(getPropertyConfig("screenCaptureFail"));
    public static boolean screenCaptureonWarning = false;
    public static boolean stopTestONFail = getBoolConfigValue(getPropertyConfig("stopTestOnValidatonFail"));
    public static boolean retryFailed = getBoolConfigValue(getPropertyConfig("retryFailed"));
    public static int retryCount = 0;
    public static int implicitTimeout = Integer.valueOf(getPropertyConfig("implicitTimeout"));
    public static int explicitTimeout = Integer.valueOf(getPropertyConfig("explicitTime"));


    static {
        if(retryFailed)
            retryCount=1;
    }

    /****
     * Description : this function will get value of config data as true or false in nboolean form
     * Usage :
     * parameter : string vlaue to be coverted to true/false
     */
    public static boolean getBoolConfigValue(String value){
        if(value.equalsIgnoreCase("true"))
            return true;
        return false;
    }

    /****
     * Description : this function will read the properties file
     * Usage :
     * parameter : strConfig : name of file to be read, path is taken fom configpath variable
     */
    public static Properties readPropertiesFile(String strConfig){
        Properties p=null;
        try {
            FileReader reader = new FileReader( strConfigPath + strConfig);
            p = new Properties();
            p.load(reader);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        printProperties(p);
        return p;
    }

    /****
     * Description : this function will property value form config file
     * Usage :
     * parameter : strProperty : property whose value is reqired
     */
    public static String getPropertyConfig(String strProperty){
        if(propsConfig == null) {
            System.out.println("**** Cannot read properties file. Something went wrong with properties file like file not found or something else.");
            return "";
        }
        else
            return propsConfig.getProperty(strProperty);
    }
    /****
     * Description : this function will property value form input file
     * Usage :
     * parameter : strProperty : property whose value is required
     */
    public static String getPropertyInputData(String strProperty){
        if(propsInput == null) {
            System.out.println("**** Cannot read properties file. Something went wrong with properties file like file not found or something else.");
            return "";
        }
        else
            return propsInput.getProperty(strProperty);
    }

    /****
     * Description : this function will print properties file
     * Usage :
     * parameter : Properties object/variable
     */
    public static void printProperties(Properties props){
        Enumeration<Object> keys = props.keys();
        while(keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            System.out.println(key + "->" + props.getProperty(key));
        }
    }
}
