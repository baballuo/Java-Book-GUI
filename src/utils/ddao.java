package utils;

import javax.swing.*;
import java.sql.*;

/*
    *@Ballas_code
    *create_time:2022/5/22 9:28
    @param null
    *@return
*/
public class ddao {
    private static final String USER = "root";
    private static final String PWD = "111111";
    private static final String URL = "jdbc:mysql://localhost:3306/mydb?characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=true";

    private static Statement stmt;
    private static Connection con;
    public static void main(String[] args) {

        ResultSet rs=null;
        Connection connection = null;
        try {
            //1,加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");//com.mysql.jdbc.Driver
            con = DriverManager.getConnection(URL, USER, PWD);

            System.out.println("创建连接成功");

            String sql="select BookName from book where price=98";
            //4.得到statement对象执行sql
              stmt = con.createStatement();
            //5.得到结果集
            rs = stmt.executeQuery(sql);
            //6.处理结果集
            while(rs.next()){
                System.out.println(rs.getString(1));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //7.关闭
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt!=null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("关闭成功");
        }
    }
}


