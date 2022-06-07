package Dao;

import utils.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private UserBean user;
    private PreparedStatement pstmt2; //Ԥ����������
    
    public UserBean findUser(String name,String Pwd){
    	//?Ϊռλ��
    	String sql = "select * from user where username = ? AND userpsw = ?;";
    	pstmt2 = DBUtil.getPstmt(sql);
    	try {
			pstmt2.setString(1, name); //1�ǵ�һ���ʺ�
			pstmt2.setString(2, Pwd);
			ResultSet rs = pstmt2.executeQuery(sql); //ִ�в�ѯ���
			if (rs.next()) {
				user = new UserBean();
				user.setUsername(name);
				user.setPwd(Pwd);
			}else {
				user = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
    }
}
