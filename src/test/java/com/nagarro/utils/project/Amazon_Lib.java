package com.nagarro.utils.project;

import com.nagarro.config.Configs;
import com.nagarro.utils.web.Web_Lib;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Amazon_Lib {

    /****
     * Description : this function will validate if items in list are soreted or not
     * Usage :
     * parameter : list of items
     */
    public static boolean validate_List_Sorted(List<String> list){
        List tmp = new ArrayList(list);
        Collections.sort(tmp);
        boolean sorted = tmp.equals(list);
        return sorted;
    }

    /****
     * Description : this function will move results from current folder to archived folder
     * Usage :
     */
    public static void moveResults(){
        String strsrc = Configs.strResultPath;
        String strDest = Configs.strArchReportPath;
        File f1 = new File(strsrc);
        File f2 = new File(strDest);
        moveDir(f1.toPath(), f2.toPath());
    }
    /****
     * Description : this function will move dir from src to desination
     * Usage :
     * parameter : src and destination paths
     */
    private static boolean moveDir(Path src, Path dest)
    {
        if (src.toFile().isDirectory())
        {
            for (File file: src.toFile().listFiles()) {
                moveDir(file.toPath(), dest.resolve(src.relativize(file.toPath())));
            }
        }

        try {
            Files.move(src, dest);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
