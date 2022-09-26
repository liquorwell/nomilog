package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.User;

/**
 * ユーザーテーブル用DAO
 */
public class UserDao {
	
	/** ユーザーIDによる検索SQL */
	private static final String FIND_BY_USER_ID = "SELECT user_id, user_name, user_pass "
			+ "FROM m_user "
			+ "WHERE user_id = ? AND is_deleted = '0'";
	/** ユーザー名による検索SQL */
	private static final String FIND_BY_USER_NAME = "SELECT user_id, user_name, user_pass "
			+ "FROM m_user "
			+ "WHERE user_name = ? AND is_deleted = '0'";
	/** ユーザー名、パスワードによる検索SQL */
	private static final String FIND_BY_NAME_PASS = "SELECT user_id, user_name, user_pass "
			+ "FROM m_user "
			+ "WHERE user_name = ? AND user_pass = ? AND is_deleted = '0'";
	
	/** 登録SQL */
	private static final String INSERT = "INSERT INTO m_user "
			+ "(user_id, user_name, user_pass, is_admin, display_order, is_deleted, revision) "
			+ "VALUES (seq_user.NEXTVAL, ?, ?, '0', 0, '0', 1)";
	/** ユーザー名更新SQL */
	private static final String UPDATE_USER_NAME = "UPDATE m_user "
			+ "SET user_name = ?, revision = revision + 1 "
			+ "WHERE user_id = ?";
	/** パスワード更新SQL */
	private static final String UPDATE_USER_PASS = "UPDATE m_user "
			+ "SET user_pass = ?, revision = revision + 1 "
			+ "WHERE user_id = ?";
	/** 論理削除SQL */
	private static final String DELETE = "UPDATE m_user "
			+ "SET is_deleted = '1', revision = revision + 1 "
			+ "WHERE user_id = ?";
	
	/** 版数検索SQL */
	private static final String GET_REVISION = "SELECT revision "
			+ "FROM m_user "
			+ "WHERE user_id = ?";



	/**
	 * ユーザーID(int)による検索メソッド
	 * @param userId ユーザーID(int)
	 * @return 1件のユーザー
	 */
	public static User findByUserId(int userId) {
		User user = null;
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_USER_ID)
		){
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user = resultUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	/**
	 * ユーザーID(String)による検索メソッド
	 * @param userId ユーザーID(String)
	 * @return 1件のユーザー
	 */
	public static User findById(String strUserId) {
		int userId = Integer.parseInt(strUserId);
		User user = findByUserId(userId);
		return user;
	}
	
	/**
	 * ユーザー名による検索メソッド
	 * @param userName ユーザー名
	 * @return 1件のユーザー
	 */
	public static User findByUserName(String userName) {
		User user = null;
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_USER_NAME)
		){
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user = resultUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * ユーザー名とパスワードによる検索メソッド
	 * @param userName ユーザー名
	 * @param userPass パスワード
	 * @return 1件のユーザー
	 */
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
				user = resultUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	/**
	 * 登録メソッド
	 * @param user
	 */
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
	
	/**
	 * ユーザー名更新メソッド
	 * @param userId ユーザーID
	 * @param userName ユーザー名
	 */
	public static void updateUserName(int userId, String userName) {
		try(
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(UPDATE_USER_NAME)
		){
			int startRevision = getRevision(userId); 
			ps.setString(1, userName);
			ps.setInt(2, userId);
			int endRevision = getRevision(userId);
			//版数管理 割り込み処理を防ぐ
			if (startRevision == endRevision) {
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	
	/**
	 * パスワード更新メソッド
	 * @param userId ユーザーID
	 * @param userPass パスワード
	 */
	public static void updateUserPass(int userId, String userPass) {
		try(
				Connection con = DBManager.getConnection();
				PreparedStatement ps = con.prepareStatement(UPDATE_USER_PASS)
			){
				int startRevision = getRevision(userId);
				ps.setString(1, userPass);
				ps.setInt(2, userId);
				int endRevision = getRevision(userId);
				//版数管理 割り込み処理を防ぐ
				if (startRevision == endRevision) {
					ps.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 論理削除メソッド <br>
	 * ユーザーに紐づいたカテゴリ、酒ログ、酒メモもすべて論理削除
	 * @param userId ユーザーID
	 */
	public static void delete(int userId) {
		try(
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(DELETE)
		){
			int startRevision = getRevision(userId);
			ps.setInt(1, userId);
			int endRevision = getRevision(userId);
			if(startRevision == endRevision) {
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SakelogDao.deleteUser(userId);
		SakememoDao.deleteUser(userId);
		CategoryDao.deleteUser(userId);
	}
	
	
	/**
	 * ユーザーの版数を返すメソッド
	 * @param userId ユーザーID
	 * @return 版数
	 * @throws SQLException
	 */
	private static int getRevision(int userId) throws SQLException {
		Connection con = DBManager.getConnection();
		PreparedStatement ps = con.prepareStatement(GET_REVISION);
		
		int revision = 0;
		ps.setInt(1,  userId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			revision =  rs.getInt("revision");
		}
		
		return revision;
	}
	
	
	/**
	 * 検索SQLの実行結果をUserにセットするメソッド
	 * @param rs ResultSet
	 * @return 1件のUser
	 * @throws SQLException
	 */
	public static User resultUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUserId(rs.getInt("user_id"));
		user.setUserName(rs.getString("user_name"));
		user.setUserPass(rs.getString("user_pass"));
		return user;
	}
}
