package com.joshua.dataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by bmk on 18-3-15.
 */
public class JdbcTest {
    public static void main(String[] args) {
        try {
            Connection connection = DbUtil.getConnection();
            String sql = "select * from t_book";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("--------------------");
            while (resultSet.next()){
                System.out.print(resultSet.getInt(1)+"\t");
                System.out.print(resultSet.getString(2)+"\t");
                System.out.print(resultSet.getString(3)+"\t");
                System.out.print(resultSet.getDouble(4)+"\n");
            }
            System.out.println("--------------------\n");
            DbUtil.release(connection,statement,resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
