package com.joshua.webpage.java;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by bmk on 18-3-13.
 */
public class DeveloperSqlGenerator {
    public static void main(String[] args) {
        File folder = new File("/home/bmk/workspace/bmk-projects/biocloud-developer-platform-another/mock");
        File[] files = folder.listFiles();
        StringBuilder sb = new StringBuilder();
        if (files.length > 0) {
            Arrays.asList(files).forEach(file -> {
                File[] files1 = file.listFiles();
                if (file.isDirectory() && files1.length>0) {
                    String appName = file.getName();
                    sb.append("update t_project_type set personalized_static_resource = '");
                    sb.append(readFileToString(files1[0]));
                    sb.append("' where project_type_en = '");
                    sb.append(getProjectTypeByAppName(appName));
                    sb.append("';\n");
                }
            });
        }
        try {
            FileUtils.writeStringToFile(new File("/home/bmk/桌面/developer.sql"), sb.toString());
        }catch (IOException e) {
            System.out.println("写文件出现异常");
            System.out.println(e.getMessage());
        }
    }

    static String readFileToString (File file) {
        try {
            return FileUtils.readFileToString(file);
        } catch (IOException e) {
            System.out.println("读取文件出现异常");
            System.out.println(e.getMessage());
            return "";
        }
    }

    static String getProjectTypeByAppName (String appName) {
        String projectType;
        switch (appName) {
            case "RNARef":
                projectType = "ref";
                break;
            case "RNANoRefTrans":
                projectType = "noref";
                break;
            case "SmallRNA":
                projectType = "srna";
                break;
            case "LncRNA":
                projectType = "lncrna";
                break;
            case "Microbial":
                projectType = "mbd";
                break;
            default:
                projectType =  "";
                break;
        }
        return projectType;
    }
}
