package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.User;

public class TestDao {
	public static List<User> findAll(){
		Connection con = null;
		PreparedStatement ps = null;
		List<User> userList = new ArrayList<User>();
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement("SELECT * FROM user");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setAge(rs.getInt("age"));
				user.setAddress(rs.getString("address"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(ps, con);
		}
		return userList;
	}
}
