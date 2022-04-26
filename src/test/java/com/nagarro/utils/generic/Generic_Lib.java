package com.nagarro.utils.generic;

import java.util.HashMap;
import java.util.Map;

public class Generic_Lib {

    /****
     * Description : this function will stop script for some time as mentioned i n second
     * Usage :
     * parameter : secs : tie in seconds to wait
     */
    public static void wait(int secs){
        try {
            Thread.sleep(secs*1000);
        }
        catch(Exception e){
        }
    }

    public  static void printHashMap(Map<Object, Object> map){
        for (Map.Entry<Object,Object> entry : map.entrySet())
            System.out.println( entry.getKey().toString() +
                    " = " + entry.getValue().toString());
    }
    /****
     * Description : this function will print hashmap
     * Usage :
     * parameter : hashmap
     */
    public  static void printHashMap(HashMap<String, String> map){
        for (Map.Entry<String,String> entry : map.entrySet())
            System.out.println( entry.getKey() +
                    " = " + entry.getValue());
    }

    /****
     * Description : this function will convert hashmap to string
     * Usage :
     * parameter : hashmap
     */
    public  static String hashMaptoString(HashMap<String, String> map){
        String retStr = "";
        for (Map.Entry<String,String> entry : map.entrySet())
            retStr += entry.getKey() +
                    " = " + entry.getValue() + ", ";
        return retStr;
    }
}
