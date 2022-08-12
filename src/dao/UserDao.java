package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.User;

public class UserDao {
	
	private static final String USER_FIND_BY_NAME_PASS = "SELECT user_id, user_name, user_pass, is_admin, display_order, revision "
			+ "FROM m_user "
			+ "WHERE user_name = ? AND user_pass = ? AND is_deleted = '0'";

	
	public static User findByNamePass(String userName, String userPass) {
		User user = null;
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(USER_FIND_BY_NAME_PASS)
		){
			ps.setString(1, userName);
			ps.setString(2, userPass);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPass(rs.getString("user_pass"));
				user.setIsAdmin(rs.getBoolean("is_admin"));
				user.setDisplayOrder(rs.getInt("display_order"));
				user.setRevision(rs.getInt("revision"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
