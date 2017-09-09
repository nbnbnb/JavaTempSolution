package demos;

import java.sql.*;

/**
 * Created by ZhangJin on 2017/7/11.
 */
public class JdbcDemo {
    public static void basicQueryForSQLServer() throws ClassNotFoundException, SQLException {
        // 加载驱动包
        // 此段代码仅仅是验证包是否存在路径中
        // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost\\SQLExpress;databaseName=JavaTempSolution;IntegratedSecurity=True";
        Connection con = DriverManager.getConnection(url);

        String sql = "SELECT CURRENT_TIMESTAMP";
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            System.out.println(resultSet.getDate(1));  // # 索引从 1 开始
        }

        con.close();
    }
}
