package me.zhangjin.study.demos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ZhangJin on 2017/7/11.
 */
public class JdbcDemo {
    public static void jdbcDemo() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost\\SQLExpress;databaseName=JavaTempSolution;IntegratedSecurity=True";
        Connection con = DriverManager.getConnection(url);
        con.close();
    }
}
