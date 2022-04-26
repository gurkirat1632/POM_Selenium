package com.nagarro.utils.generic;

import java.io.File;

public class File_Lib {

    /****
     * Description : this function will check if file exists or not
     * Usage :
     * parameter : path of file
     */
    public static Boolean checkFileExists(String strPath){
        File f = new File(strPath);
        return f.exists();
    }

}
