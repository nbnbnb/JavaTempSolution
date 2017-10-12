package demos;

import java.sql.*;

/**
 * Created by ZhangJin on 2017/7/11.
 */
public class JdbcDemo {
    public static void basicQueryForSQLServer() {

        try {
            // 加载驱动包
            // 如果包已经在路径中，则不需要显式加载
            // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost\\SQLExpress;databaseName=JavaTempSolution;IntegratedSecurity=True";
            Connection con = DriverManager.getConnection(url);

            String sql = "SELECT CURRENT_TIMESTAMP";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                System.out.println("DateTime From SQLServer - " + resultSet.getDate(1));  // # 索引从 1 开始
            }

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
