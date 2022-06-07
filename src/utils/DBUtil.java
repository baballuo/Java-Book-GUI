package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/*
    *@Ballas_code
    *create_time:2022/5/22 9:27
    @param null
    *@return
*/
public class DBUtil {
	private static final String USER = "root";
	private static final String PWD = "111111";
	private static final String URL = "jdbc:mysql://localhost:3306/mydb?characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=true";
	private static Statement stmt;
	private static Connection con;

	private static DBUtil utils = null;
	private static PreparedStatement pstmt; // Ԥ����������

	private DBUtil() {

	}

	// �����̰߳�ȫ�� ����в�������ʵ������ʱ�������̰߳�ȫ�����⣬����취��ͬ����synchronized
	public synchronized static DBUtil getDBUtil() {
		if (utils == null) {
			utils = new DBUtil();
			return utils;
		}
		return utils;
	}

	public static Connection getConn() {
		if (con == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");//com.mysql.jdbc.Driver
				con = DriverManager.getConnection(URL, USER, PWD);
				System.out.println("�������ӳɹ�");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return con;
	}

	public static Statement getStatement() {
		if (stmt == null) {
			try {
				if (con == null) {
					con = getConn();
				}
				stmt = con.createStatement();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return stmt;
	}

	// Ԥ����������
	public static PreparedStatement getPstmt(String sql) {
		if (pstmt == null) {
			try {
				pstmt = con.prepareStatement(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}