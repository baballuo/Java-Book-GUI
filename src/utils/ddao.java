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
            //1,��������
            Class.forName("com.mysql.cj.jdbc.Driver");//com.mysql.jdbc.Driver
            con = DriverManager.getConnection(URL, USER, PWD);

            System.out.println("�������ӳɹ�");

            String sql="select BookName from book where price=98";
            //4.�õ�statement����ִ��sql
              stmt = con.createStatement();
            //5.�õ������
            rs = stmt.executeQuery(sql);
            //6.��������
            while(rs.next()){
                System.out.println(rs.getString(1));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //7.�ر�
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

            System.out.println("�رճɹ�");
        }
    }
}


