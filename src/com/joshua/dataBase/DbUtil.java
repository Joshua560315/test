package com.joshua.dataBase;

import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.*;

/**
 * Created by bmk on 18-3-15.
 */
public class DbUtil {
    private static String url = null;
    private static String uerName = null;
    private static String password = null;

    static {
        try {
            String properties = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"/src/com/joshua/dataBase/config/db.json"));
            JSONObject dataSource = JSONObject.fromObject(properties);
            String driver = dataSource.getString("driver");
            url = dataSource.getString("url");
            uerName = dataSource.getString("username");
            password = dataSource.getString("password");
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,uerName,password);
    }

    public static void release (Connection connection, Statement statement, ResultSet resultSet){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
