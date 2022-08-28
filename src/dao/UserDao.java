package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.User;

public class UserDao {
	
	private static final String FIND_BY_USER_ID = "SELECT user_id, user_name "
			+ "FROM m_user "
			+ "WHERE user_id = ? AND is_deleted = '0'";
	
	private static final String FIND_BY_NAME_PASS = "SELECT user_id, user_name, user_pass, is_admin, revision "
			+ "FROM m_user "
			+ "WHERE user_name = ? AND user_pass = ? AND is_deleted = '0'";


	private static final String INSERT = "INSERT INTO m_user "
			+ "(user_id, user_name, user_pass, is_admin, display_order, is_deleted, revision) "
			+ "VALUES (seq_user.NEXTVAL, ?, ?, '0', 0, '0', 1)";
	
	private static final String UPDATE_USER_NAME = "UPDATE m_user "
			+ "SET user_name = ? "
			+ "WHERE user_id = ?";
	
	private static final String UPDATE_USER_PASS = "UPDATE m_user "
			+ "SET user_pass = ? "
			+ "WHERE user_id = ?";
	
	private static final String DELETE = "UPDATE m_user "
			+ "SET is_deleted = '1', revision = revision + 1 "
			+ "WHERE user_id = ?";



	public static User findByUserId(int userId) {
		User user = null;
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_USER_ID)
		){
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	public static User findById(String strUserId) {
		int userId = Integer.parseInt(strUserId);
		User user = findByUserId(userId);
		return user;
	}
	
	public static User findByNamePass(String userName, String userPass) {
		User user = null;
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_NAME_PASS)
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
				user.setRevision(rs.getInt("revision"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	public static void insert(User user) {
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(INSERT)
		){
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPass());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateUserName(int userId, String userName) {
		try(
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(UPDATE_USER_NAME)
		){
			ps.setString(1, userName);
			ps.setInt(2, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateUserPass(int userId, String userPass) {
		try(
				Connection con = DBManager.getConnection();
				PreparedStatement ps = con.prepareStatement(UPDATE_USER_PASS)
			){
				ps.setString(1, userPass);
				ps.setInt(2, userId);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void delete(int userId) {
		try(
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(DELETE)
		){
			ps.setInt(1, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SakelogDao.deleteUser(userId);
		SakememoDao.deleteUser(userId);
		CategoryDao.deleteUser(userId);
	}
}
