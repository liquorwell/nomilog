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
	
	private static final String FIND = "SELECT sakelog_id, sakelog_name, rating, sakelog_comment, category_id "
			+ "FROM t_sakelog ";
	
	private static final String IS_NOT_DELETED = "AND is_deleted = '0' ";
	private static final String BY_SAKELOG_ID = "WHERE sakelog_id = ? " + IS_NOT_DELETED;
	private static final String BY_SAKELOG_NAME = "WHERE sakelog_name LIKE ? " + IS_NOT_DELETED;
	private static final String BY_RATING = "WHERE rating = ? " + IS_NOT_DELETED;
	private static final String BY_CATEGORY_ID = "WHERE category_id = ? " + IS_NOT_DELETED;
	private static final String BY_USER_ID = "WHERE user_id = ? " + IS_NOT_DELETED;
	private static final String BY_INS_DATE = "WHERE ins_date BETWEEN ? AND ? " + IS_NOT_DELETED;
	
	private static final String ORDER_RATING = "ORDER BY rating ";
	private static final String ORDER_CATEGORY_ID = "ORDER BY category_id ";
	private static final String ORDER_INS_DATE = "ORDER BY ins_date ";
	
	private static final String ASC = "ASC ";
	private static final String DESC = "DESC ";
	
	private static final String FIND_RATEST_SAKELOG_ID = "SELECT sakelog_id "
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
	
	
	
	public static Sakelog findBySakelogId(int sakelogId) {
		Sakelog sakelog = null;
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND + BY_SAKELOG_ID)
		){
			ps.setInt(1, sakelogId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sakelog = resultSakelog(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sakelog;
	}
	public static Sakelog findBySakelogId(String strSakelogId) {
		int sakelogId = Integer.parseInt(strSakelogId);
		Sakelog sakelog = findBySakelogId(sakelogId);
		return sakelog;
	}
	
	public static List<Sakelog> findBySakelogName(String sakelogName) {
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND + BY_SAKELOG_NAME + ORDER_INS_DATE + DESC)
		){
			ps.setString(1, "%"+sakelogName+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sakelog sakelog = resultSakelog(rs);
				sakelogList.add(sakelog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sakelogList;
	}
	
	public static List<Sakelog> findByRating(int rating) {
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND + BY_RATING + ORDER_INS_DATE + DESC)
		){
			ps.setInt(1, rating);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sakelog sakelog = resultSakelog(rs);
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
	
	public static List<Sakelog> findByCategoryId(int categoryId) {
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND + BY_CATEGORY_ID + ORDER_INS_DATE + DESC)
		){
			ps.setInt(1, categoryId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sakelog sakelog = resultSakelog(rs);
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
	
	public static List<Sakelog> findByUserId(int userId, String sortStatement) {
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND + BY_USER_ID + sortStatement)
		){
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sakelog sakelog = resultSakelog(rs);
				sakelogList.add(sakelog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sakelogList;
	}
	
		public static List<Sakelog>findByUserIdRatingDesc(int userId) {
			List<Sakelog> sakelogList = findByUserId(userId, ORDER_RATING + DESC);
			return sakelogList;
		}
		
		public static List<Sakelog>findByUserIdRatingAsc(int userId) {
			List<Sakelog> sakelogList = findByUserId(userId, ORDER_RATING + ASC);
			return sakelogList;
		}
		
		public static List<Sakelog>findByUserIdCategoryIdAsc(int userId) {
			List<Sakelog> sakelogList = findByUserId(userId, ORDER_CATEGORY_ID + ASC);
			return sakelogList;
		}
		
		public static List<Sakelog>findByUserIdInsDateDesc(int userId) {
			List<Sakelog> sakelogList = findByUserId(userId, ORDER_INS_DATE + DESC);
			return sakelogList;
		}
		public static List<Sakelog> findByUserIdInsDateDesc(String strUserId){
			int userId = Integer.parseInt(strUserId);
			List<Sakelog> sakelogList = findByUserIdInsDateDesc(userId);
			return sakelogList;
		}
		
		public static List<Sakelog>findByUserIdInsDateAsc(int userId) {
			List<Sakelog> sakelogList = findByUserId(userId, ORDER_INS_DATE + ASC);
			return sakelogList;
		}
	
	public static List<Sakelog> findByInsDate(String strInsDateOld, String strInsDateNew) {
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND + BY_INS_DATE + ORDER_INS_DATE + DESC)
		){
			LocalDate insDateOld = LocalDate.parse(strInsDateOld, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			LocalDate insDateNew = LocalDate.parse(strInsDateNew, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			java.sql.Date sqlInsDateOld = java.sql.Date.valueOf(insDateOld);
			java.sql.Date sqlInsDateNew = java.sql.Date.valueOf(insDateNew.plusDays(1));
			ps.setDate(1, sqlInsDateOld);
			ps.setDate(2, sqlInsDateNew);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sakelog sakelog = resultSakelog(rs);
				sakelogList.add(sakelog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sakelogList;
	}
	
	public static int findRatestSakelogId() {
		int sakelogId = 0;
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_RATEST_SAKELOG_ID)
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
	
	
	
	public static Sakelog resultSakelog(ResultSet rs) throws SQLException {
		Sakelog sakelog = new Sakelog();
		sakelog.setSakelogId(rs.getInt("sakelog_id"));
		sakelog.setSakelogName(rs.getString("sakelog_name"));
		sakelog.setRating(rs.getInt("rating"));
		sakelog.setSakelogComment(rs.getString("sakelog_comment"));
		Category category = CategoryDao.findByCategoryId(rs.getInt("category_id"));
		sakelog.setCategory(category);
		return sakelog;
	}
}