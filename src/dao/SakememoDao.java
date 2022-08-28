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
import bean.Sakememo;

public class SakememoDao {
	
	private static final String FIND = "SELECT sakememo_id, sakememo_name, sakememo_comment, category_id "
			+ "FROM t_sakememo ";
	
	private static final String IS_NOT_DELETED = "AND is_deleted = '0' ";
	private static final String BY_SAKEMEMO_ID = "WHERE sakememo_id = ? " + IS_NOT_DELETED;
	private static final String BY_SAKEMEMO_NAME = "WHERE sakememo_name LIKE ? " + IS_NOT_DELETED;
	private static final String BY_CATEGORY_ID = "WHERE category_id = ? " + IS_NOT_DELETED;
	private static final String BY_USER_ID = "WHERE user_id = ? " + IS_NOT_DELETED;
	private static final String BY_INS_DATE = "WHERE ins_date BETWEEN ? AND ? " + IS_NOT_DELETED;
	
	private static final String ORDER_CATEGORY_ID = "ORDER BY category_id ";
	private static final String ORDER_INS_DATE = "ORDER BY ins_date ";
	
	private static final String ASC = "ASC ";
	private static final String DESC = "DESC ";
	
	
	private static final String INSERT = "INSERT INTO t_sakememo "
			+ "(category_id, user_id, sakememo_id, sakememo_name, sakememo_comment, sakelog_id, is_deleted, ins_date, upd_date) "
			+ "VALUES (?, ?, seq_sakememo.NEXTVAL, ?, ?, 0, '0', sysdate, sysdate)";
	
	private static final String UPDATE = "UPDATE t_sakememo "
			+ "SET category_id = ?, sakememo_name = ?, sakememo_comment = ?, upd_date = sysdate "
			+ "WHERE sakememo_id = ?";
	
	private static final String DELETE = "UPDATE t_sakememo "
			+ "SET is_deleted = '1', upd_date = sysdate "
			+ "WHERE sakememo_id = ?";
	
	private static final String DELETE_USER = "UPDATE t_sakememo "
			+ "SET is_deleted = '1', upd_date = sysdate "
			+ "WHERE user_id = ?";
	
	
	private static final String MOVE = "UPDATE t_sakememo "
			+ "SET sakelog_id = ?, is_deleted = '1', upd_date = sysdate "
			+ "WHERE sakememo_id = ?";
	
	
	
	public static Sakememo findBySakememoId(int sakememoId) {
		Sakememo sakememo = null;
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND + BY_SAKEMEMO_ID)
		){
			ps.setInt(1, sakememoId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sakememo = resultSakememo(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sakememo;
	}
	public static Sakememo findBySakememoId(String strSakememoId) {
		int sakememoId = Integer.parseInt(strSakememoId);
		Sakememo sakememo = findBySakememoId(sakememoId);
		return sakememo;
	}
	
	public static List<Sakememo> findBySakememoName(String sakememoName) {
		List<Sakememo> sakememoList = new ArrayList<Sakememo>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND + BY_SAKEMEMO_NAME + ORDER_INS_DATE + DESC)
		){
			ps.setString(1, "%"+sakememoName+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sakememo sakememo = resultSakememo(rs);
				sakememoList.add(sakememo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sakememoList;
	}
	
	public static List<Sakememo> findByCategoryId(int categoryId){
		List<Sakememo> sakememoList = new ArrayList<Sakememo>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND + BY_CATEGORY_ID + ORDER_INS_DATE + DESC)
		){
			ps.setInt(1, categoryId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sakememo sakememo = resultSakememo(rs);
				sakememoList.add(sakememo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sakememoList;
	}
	public static List<Sakememo> findByCategoryId(String strCategoryId) {
		int categoryId = Integer.parseInt(strCategoryId);
		List<Sakememo> sakememoList = findByCategoryId(categoryId);
		return sakememoList;
	}
	
	public static List<Sakememo> findByUserId(int userId, String sortStatement){
		List<Sakememo> sakememoList = new ArrayList<Sakememo>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND + BY_USER_ID + sortStatement)
		){
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sakememo sakememo = resultSakememo(rs);
				sakememoList.add(sakememo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sakememoList;
	}
	
		public static List<Sakememo> findByUserIdCategoryIdAsc(int userId){
			List<Sakememo> sakememoList = findByUserId(userId, ORDER_CATEGORY_ID + ASC);
			return sakememoList;
		}
		
		public static List<Sakememo> findByUserIdInsDateDesc(int userId){
			List<Sakememo> sakememoList = findByUserId(userId, ORDER_INS_DATE + DESC);
			return sakememoList;
		}
		public static List<Sakememo> findByUserIdInsDateDesc(String strUserId) {
			int userId = Integer.parseInt(strUserId);
			List<Sakememo> sakememoList = findByUserIdInsDateDesc(userId);
			return sakememoList;
		}
		
		public static List<Sakememo> findByUserIdInsDateAsc(int userId){
			List<Sakememo> sakememoList = findByUserId(userId, ORDER_INS_DATE + ASC);
			return sakememoList;
		}
	
	public static List<Sakememo> findByInsDate(String strInsDateOld, String strInsDateNew){
		List<Sakememo> sakememoList = new ArrayList<Sakememo>();
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
				Sakememo sakememo = resultSakememo(rs);
				sakememoList.add(sakememo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sakememoList;
	}
	
	
	public static void insert(Sakememo sakememo) {
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(INSERT)
		){
			ps.setInt(1, sakememo.getCategory().getCategoryId());
			ps.setInt(2, sakememo.getUser().getUserId());
			ps.setString(3, sakememo.getSakememoName());
			ps.setString(4, sakememo.getSakememoComment());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void update(Sakememo sakememo) {
		try(
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(UPDATE)
		){
			ps.setInt(1, sakememo.getCategory().getCategoryId());
			ps.setString(2, sakememo.getSakememoName());
			ps.setString(3, sakememo.getSakememoComment());
			ps.setInt(4, sakememo.getSakememoId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void delete(int sakememoId) {
		try(
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(DELETE)
		){
			ps.setInt(1, sakememoId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void delete(String strSakememoId) {
		int sakememoId = Integer.parseInt(strSakememoId);
		delete(sakememoId);
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
	
	
	public static void move(int sakememoId) {
		try(
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(MOVE)
		){
			int sakelogId = SakelogDao.findRatestSakelogId();
			ps.setInt(1, sakelogId);
			ps.setInt(2, sakememoId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void move(String strSakememoId) {
		int sakememoId = Integer.parseInt(strSakememoId);
		move(sakememoId);
	}
	
	
	
	public static Sakememo resultSakememo(ResultSet rs) throws SQLException {
		Sakememo sakememo = new Sakememo();
		sakememo.setSakememoId(rs.getInt("sakememo_id"));
		sakememo.setSakememoName(rs.getString("sakememo_name"));
		sakememo.setSakememoComment(rs.getString("sakememo_comment"));
		Category category = CategoryDao.findByCategoryId(rs.getInt("category_id"));
		sakememo.setCategory(category);
		return sakememo;
	}
}
