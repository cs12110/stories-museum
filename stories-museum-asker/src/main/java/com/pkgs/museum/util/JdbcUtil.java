package com.pkgs.museum.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author huanghuapeng create at 2019/7/26 11:16
 * @version 1.0.0
 */
public class JdbcUtil {

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://47.98.104.252:3306/museum_db?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Root@3306";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }


    public static void update(String sql) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void close(Connection conn, Statement stm, ResultSet resultSet) {
        if (null != resultSet) {
            try {
                resultSet.close();
            } catch (Exception e) {
                //
            }
        }
        if (null != stm) {
            try {
                stm.close();
            } catch (Exception e) {
                //
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                //
            }
        }

    }
}
