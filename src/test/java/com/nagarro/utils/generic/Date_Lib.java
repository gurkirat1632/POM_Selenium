package com.nagarro.utils.generic;

import com.nagarro.config.Configs;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Date_Lib {

    /****
     * Description : this function will return current date time in required format
     * Usage :
     * parameter : format required in string form
     */
    public static String getCurrentDate(String dateFormat){
        String currentTime = new SimpleDateFormat(dateFormat).format(new Date());
        currentTime = currentTime.replace(" ", "_t_").replace("-", "_").replace(":","_");
        return currentTime;
    }
}
