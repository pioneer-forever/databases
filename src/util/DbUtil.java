package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*数据库工具类*/

public class DbUtil {
    
    private String Url = "jdbc:mysql://localhost:3306/library_use"; // 数据库链接地址
    private String UserName = "yuan";
    private String Password = "yuan";
    private String jdbcName = "com.mysql.cj.jdbc.Driver"; // 对应版本的MySQL JDBC驱动

    /*获取数据库链接*/
    public Connection getCon() throws Exception {
        Connection con = null;
        try {
            // 注册JDBC驱动程序
            Class.forName(jdbcName);
            // 建立连接
            con = DriverManager.getConnection(Url, UserName, Password);
            if (!con.isClosed()) {
                System.out.println("数据库连接成功");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
        return con;
    }

    /*关闭链接*/
    public void closeCon(Connection con) throws Exception {
        if (con != null) {
            con.close();
        }
    }

    public static void main(String[] args) {
        DbUtil dbUtil = new DbUtil();
        try {
            dbUtil.getCon();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
