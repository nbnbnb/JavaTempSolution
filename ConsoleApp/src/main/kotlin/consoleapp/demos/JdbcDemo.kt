package consoleapp.demos

import java.sql.DriverManager

/**
 * Created by ZhangJin on 2017/7/11.
 */
object JdbcDemo {
    fun basicQueryForSQLServer() {
        try {
            // 加载驱动包
            // 如果包已经在路径中，则不需要显式加载
            // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            val url = "jdbc:sqlserver://localhost\\SQLExpress;databaseName=JavaTempSolution;IntegratedSecurity=True"
            val con = DriverManager.getConnection(url)

            val sql = "SELECT CURRENT_TIMESTAMP "
            val statement = con.createStatement()
            val resultSet = statement.executeQuery(sql)

            while (resultSet.next()) {
                println("DateTime From SQLServer - " + resultSet.getDate(1))  // # 索引从 1 开始
            }

            con.close()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}
