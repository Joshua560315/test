package com.bmk;

import com.joshua.dataBase.DbUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bmk on 18-4-18.
 */
public class WrongEmailFilter {
    public static void main(String[] args) {
        filterWrongEmails();
//        addWrongEmails();
    }

    private static void filterWrongEmails(){
        try {
            List<String> emails = Files.lines(Paths.get("/home/bmk/桌面/sortedEmails.txt")).collect(Collectors.toList());

            Connection con =  DbUtil.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select email from wrongEmailUniqe");
            String email;
            int count = 0;
            while (resultSet.next()){
                email = resultSet.getString(1);
                if (emails.contains(email)){
                    System.out.println("无效邮件:"+email);
                    emails.remove(email);
                    count++;
                }
            }
            System.out.println("过滤掉无效邮件地址"+count+"个");
            FileUtils.writeLines(new File("/home/bmk/桌面/validEmails.txt"), emails);
            DbUtil.release(con,statement,resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addWrongEmails(){
        try {
            List<String> emails = Files.lines(Paths.get("/home/bmk/桌面/wrongEmails.txt")).collect(Collectors.toList());

            Connection con =  DbUtil.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select email from wrongEmailUniqe");
            List<String> wrongEmails = new ArrayList<>();
            while (resultSet.next()){
                wrongEmails.add(resultSet.getString(1));
            }
            String sql = "INSERT INTO wrongEmailUniqe VALUES ";
            String values = emails.parallelStream().filter(email -> !wrongEmails.contains(email)).map(email -> "(\""+email+"\")").collect(Collectors.joining(","));
            if (!"".equals(values)){
                System.out.println(sql+values);
                statement.execute(sql+values);
            }
            System.out.println("本次更新无效邮件地址："+(values.split(",").length-1));
            DbUtil.release(con,statement,resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
