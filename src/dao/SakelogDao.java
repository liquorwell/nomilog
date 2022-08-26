package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import bean.Category;
import bean.Sakelog;

public class SakelogDao {
	
	private static final String FIND_BY_ID = "SELECT sakelog_id, sakelog_name, rating, sakelog_comment, category_id "
			+ "FROM t_sakelog "
			+ "WHERE sakelog_id = ? AND is_deleted = '0'";
	
	private static final String FIND_BY_USER_ID_INS_DATE_DESC = "SELECT sklg.sakelog_id, sklg.sakelog_name, sklg.rating, sklg.sakelog_comment, ctgr.category_name "
			+ "FROM t_sakelog sklg "
			+ "INNER JOIN m_category ctgr ON sklg.category_id = ctgr.category_id "
			+ "WHERE sklg.user_id = ? AND sklg.is_deleted = '0' "
			+ "ORDER BY sklg.ins_date DESC";
	
	private static final String FIND_BY_USER_ID_INS_DATE_ASC = "SELECT sklg.sakelog_id, sklg.sakelog_name, sklg.rating, sklg.sakelog_comment, ctgr.category_name "
			+ "FROM t_sakelog sklg "
			+ "INNER JOIN m_category ctgr ON sklg.category_id = ctgr.category_id "
			+ "WHERE sklg.user_id = ? AND sklg.is_deleted = '0' "
			+ "ORDER BY sklg.ins_date ASC";
	
	private static final String FIND_BY_USER_ID_RATING_DESC = "SELECT sklg.sakelog_id, sklg.sakelog_name, sklg.rating, sklg.sakelog_comment, ctgr.category_name "
			+ "FROM t_sakelog sklg "
			+ "INNER JOIN m_category ctgr ON sklg.category_id = ctgr.category_id "
			+ "WHERE sklg.user_id = ? AND sklg.is_deleted = '0' "
			+ "ORDER BY sklg.rating DESC";
	
	private static final String FIND_BY_USER_ID_RATING_ASC = "SELECT sklg.sakelog_id, sklg.sakelog_name, sklg.rating, sklg.sakelog_comment, ctgr.category_name "
			+ "FROM t_sakelog sklg "
			+ "INNER JOIN m_category ctgr ON sklg.category_id = ctgr.category_id "
			+ "WHERE sklg.user_id = ? AND sklg.is_deleted = '0' "
			+ "ORDER BY sklg.rating ASC";
	
	private static final String FIND_BY_USER_ID_CATEGORY_ID_ASC = "SELECT sklg.sakelog_id, sklg.sakelog_name, sklg.rating, sklg.sakelog_comment, ctgr.category_name "
			+ "FROM t_sakelog sklg "
			+ "INNER JOIN m_category ctgr ON sklg.category_id = ctgr.category_id "
			+ "WHERE sklg.user_id = ? AND sklg.is_deleted = '0' "
			+ "ORDER BY sklg.category_id ASC";
	
	private static final String FIND_BY_SAKELOG_NAME = "SELECT sklg.sakelog_id, sklg.sakelog_name, sklg.rating, sklg.sakelog_comment, ctgr.category_name "
			+ "FROM t_sakelog sklg "
			+ "INNER JOIN m_category ctgr ON sklg.category_id = ctgr.category_id "
			+ "WHERE sklg.sakelog_name LIKE ? AND sklg.is_deleted = '0' "
			+ "ORDER BY sklg.ins_date DESC";
	
	private static final String FIND_BY_CATEGORY_ID = "SELECT sklg.sakelog_id, sklg.sakelog_name, sklg.rating, sklg.sakelog_comment, ctgr.category_name "
			+ "FROM t_sakelog sklg "
			+ "INNER JOIN m_category ctgr ON sklg.category_id = ctgr.category_id "
			+ "WHERE sklg.category_id = ? AND sklg.is_deleted = '0' "
			+ "ORDER BY sklg.ins_date DESC";
	
	private static final String FIND_BY_RATING = "SELECT sklg.sakelog_id, sklg.sakelog_name, sklg.rating, sklg.sakelog_comment, ctgr.category_name "
			+ "FROM t_sakelog sklg "
			+ "INNER JOIN m_category ctgr ON sklg.category_id = ctgr.category_id "
			+ "WHERE sklg.rating = ? AND sklg.is_deleted = '0' "
			+ "ORDER BY sklg.ins_date DESC";
	
	private static final String FIND_BY_INS_DATE = "SELECT sklg.sakelog_id, sklg.sakelog_name, sklg.rating, sklg.sakelog_comment, ctgr.category_name "
			+ "FROM t_sakelog sklg "
			+ "INNER JOIN m_category ctgr ON sklg.category_id = ctgr.category_id "
			+ "WHERE sklg.ins_date BETWEEN ? AND ?  AND sklg.is_deleted = '0' "
			+ "ORDER BY sklg.ins_date DESC";
	
	private static final String FIND_RATEST_ID = "SELECT sakelog_id "
			+ "FROM (SELECT sakelog_id FROM t_sakelog ORDER BY ins_date DESC) "
			+ "WHERE rownum = 1";
	
	
	private static final String INSERT = "INSERT INTO t_sakelog "
			+ "(category_id, user_id, sakelog_id, sakelog_name, rating, sakelog_comment, is_deleted, ins_date, upd_date) "
			+ "VALUES (?, ?, seq_sakelog.NEXTVAL, ?, ?, ?, '0', sysdate, sysdate)";
	
	private static final String UPDATE = "UPDATE t_sakelog "
			+ "SET category_id = ?, sakelog_name = ?, rating = ?, sakelog_comment = ?, upd_date = sysdate "
			+ "WHERE sakelog_id = ?";
	
	private static final String DELETE = "UPDATE t_sakelog "
			+ "SET is_deleted = '1' , upd_date = sysdate "
			+ "WHERE sakelog_id = ?";
	
	private static final String DELETE_USER = "UPDATE t_sakelog "
			+ "SET is_deleted = '1', upd_date = sysdate "
			+ "WHERE user_id = ?";
	
	
	public static Sakelog findById(int sakelogId) {
		Sakelog sakelog = null;
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_ID)
		){
			ps.setInt(1, sakelogId);
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
	public static Sakelog findById(String strSakelogId) {
		int sakelogId = Integer.parseInt(strSakelogId);
		Sakelog sakelog = findById(sakelogId);
		return sakelog;
	}
	
	public static List<Sakelog> findByUserIdInsDateDesc(int userId){
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_USER_ID_INS_DATE_DESC)
		){
			ps.setInt(1, userId);
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
	public static List<Sakelog> findByUserIdInsDateDesc(String strUserId){
		int userId = Integer.parseInt(strUserId);
		List<Sakelog> sakelogList = findByUserIdInsDateDesc(userId);
		return sakelogList;
	}
	
	public static List<Sakelog> findByUserIdInsDateAsc(int userId){
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_USER_ID_INS_DATE_ASC)
		){
			ps.setInt(1, userId);
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
	
	public static List<Sakelog> findByUserIdRatingDesc(int userId){
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_USER_ID_RATING_DESC)
		){
			ps.setInt(1, userId);
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
	
	public static List<Sakelog> findByUserIdRatingAsc(int userId){
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_USER_ID_RATING_ASC)
		){
			ps.setInt(1, userId);
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
	
	public static List<Sakelog> findByUserIdCategoryIdAsc(int userId){
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_USER_ID_CATEGORY_ID_ASC)
		){
			ps.setInt(1, userId);
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
	
	public static List<Sakelog> findBySakelogName(String sakelogName) {
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_SAKELOG_NAME)
		){
			ps.setString(1, "%"+sakelogName+"%");
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
	
	public static List<Sakelog> findByCategoryId(int categoryId) {
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_CATEGORY_ID)
		){
			ps.setInt(1, categoryId);
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
	public static List<Sakelog> findByCategoryId(String strCategoryId) {
		int categoryId = Integer.parseInt(strCategoryId);
		List<Sakelog> sakelogList = findByCategoryId(categoryId);
		return sakelogList;
	}
	
	public static List<Sakelog> findByRating(int rating) {
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_RATING)
		){
			ps.setInt(1, rating);
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
	public static List<Sakelog> findByRating(String strRating) {
		int rating = Integer.parseInt(strRating);
		List<Sakelog> sakelogList = findByRating(rating);
		return sakelogList;
	}
	
	public static List<Sakelog> findByInsDate(String strInsDateOld, String strInsDateNew) {
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_INS_DATE)
		){
			LocalDate insDateOld = LocalDate.parse(strInsDateOld, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			LocalDate insDateNew = LocalDate.parse(strInsDateNew, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			java.sql.Date sqlInsDateOld = java.sql.Date.valueOf(insDateOld);
			java.sql.Date sqlInsDateNew = java.sql.Date.valueOf(insDateNew.plusDays(1));
			ps.setDate(1, sqlInsDateOld);
			ps.setDate(2, sqlInsDateNew);
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
	
	public static int findRatestId() {
		int sakelogId = 0;
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_RATEST_ID)
		){
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sakelogId = rs.getInt("sakelog_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sakelogId;
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
	
	public static void delete(int sakelogId) {
		try(
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(DELETE)
		){
			ps.setInt(1, sakelogId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void delete(String strSakelogId) {
		int sakelogId = Integer.parseInt(strSakelogId);
		delete(sakelogId);
	}
	
	public static void deleteUser(int userId) {
		try(
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(DELETE_USER)
		){
			ps.setInt(1, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
