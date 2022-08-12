package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Category;
import bean.Sakelog;

public class SakelogDao {
	
	private static final String FIND_BY_ID = "SELECT sakelog_id, sakelog_name, rating, sakelog_comment, category_id "
			+ "FROM t_sakelog "
			+ "WHERE sakelog_id = ? AND is_deleted = '0'";
	
	private static final String FIND_BY_USER_ID = "SELECT sklg.sakelog_id, sklg.sakelog_name, sklg.rating, sklg.sakelog_comment, ctgr.category_name "
			+ "FROM t_sakelog sklg "
			+ "INNER JOIN m_category ctgr ON sklg.category_id = ctgr.category_id "
			+ "WHERE sklg.user_id = ? AND sklg.is_deleted = '0'";
	
	private static final String INSERT = "INSERT INTO t_sakelog "
			+ "(category_id, user_id, sakelog_id, sakelog_name, rating, sakelog_comment, is_deleted, ins_date, upd_date) "
			+ "VALUES (?, ?, seq_sakelog.NEXTVAL, ?, ?, ?, '0', sysdate, sysdate)";
	
	private static final String UPDATE = "UPDATE t_sakelog "
			+ "SET category_id = ?, sakelog_name = ?, rating = ?, sakelog_comment = ?, upd_date = sysdate "
			+ "WHERE sakelog_id = ?";
	
	
	public static Sakelog findById(String sakelogId) {
		Sakelog sakelog = null;
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_ID)
		){
			ps.setString(1, sakelogId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sakelog = new Sakelog();
				sakelog.setSakelogId(rs.getInt("sakelog_id"));
				sakelog.setSakelogName(rs.getString("sakelog_name"));
				sakelog.setRating(rs.getInt("rating"));
				sakelog.setSakelogComment(rs.getString("sakelog_comment"));
				Category category = new Category();
				category.setCategoryId(rs.getInt("category_id"));
				sakelog.setCategory(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sakelog;
	}
	
	public static List<Sakelog> findByUserId(int userId){
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_USER_ID)
		){
			String strUserId = String.valueOf(userId);
			ps.setString(1, strUserId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sakelog sakelog = new Sakelog();
				sakelog.setSakelogId(rs.getInt("sakelog_id"));
				sakelog.setSakelogName(rs.getString("sakelog_name"));
				sakelog.setRating(rs.getInt("rating"));
				sakelog.setSakelogComment(rs.getString("sakelog_comment"));
				Category category = new Category();
				category.setCategoryName(rs.getString("category_name"));
				sakelog.setCategory(category);
				sakelogList.add(sakelog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sakelogList;
	}
	
	public static void insert(Sakelog sakelog) {
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(INSERT)
		){
			ps.setInt(1, sakelog.getCategory().getCategoryId());
			ps.setInt(2, sakelog.getUser().getUserId());
			ps.setString(3, sakelog.getSakelogName());
			ps.setInt(4, sakelog.getRating());
			ps.setString(5, sakelog.getSakelogComment());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void update(Sakelog sakelog) {
		try(
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(UPDATE)
		){
			ps.setInt(1, sakelog.getCategory().getCategoryId());
			ps.setString(2, sakelog.getSakelogName());
			ps.setInt(3, sakelog.getRating());
			ps.setString(4, sakelog.getSakelogComment());
			ps.setInt(5, sakelog.getSakelogId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
