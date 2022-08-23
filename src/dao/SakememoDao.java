package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Category;
import bean.Sakememo;

public class SakememoDao {
	private static final String FIND_BY_ID = "SELECT sakememo_id, sakememo_name, sakememo_comment, category_id "
			+ "FROM t_sakememo "
			+ "WHERE sakememo_id = ? AND is_deleted = '0'";
	
	private static final String FIND_BY_USER_ID = "SELECT skmm.sakememo_id, skmm.sakememo_name, skmm.sakememo_comment, ctgr.category_name "
			+ "FROM t_sakememo skmm "
			+ "INNER JOIN m_category ctgr ON skmm.category_id = ctgr.category_id "
			+ "WHERE skmm.user_id = ? AND skmm.is_deleted = '0'";
	
	
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
	
	
	
	public static Sakememo findById(int sakememoId) {
		Sakememo sakememo = null;
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_ID)
		){
			ps.setInt(1, sakememoId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sakememo = new Sakememo();
				sakememo.setSakememoId(rs.getInt("sakememo_id"));
				sakememo.setSakememoName(rs.getString("sakememo_name"));
				sakememo.setSakememoComment(rs.getString("sakememo_comment"));
				Category category = new Category();
				category.setCategoryId(rs.getInt("category_id"));
				sakememo.setCategory(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sakememo;
	}
	public static Sakememo findById(String strSakememoId) {
		int sakememoId = Integer.parseInt(strSakememoId);
		Sakememo sakememo = findById(sakememoId);
		return sakememo;
	}
	
	public static List<Sakememo> findByUserId(int userId){
		List<Sakememo> sakememoList = new ArrayList<Sakememo>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_USER_ID)
		){
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sakememo sakememo = new Sakememo();
				sakememo.setSakememoId(rs.getInt("sakememo_id"));
				sakememo.setSakememoName(rs.getString("sakememo_name"));
				sakememo.setSakememoComment(rs.getString("sakememo_comment"));
				Category category = new Category();
				category.setCategoryName(rs.getString("category_name"));
				sakememo.setCategory(category);
				sakememoList.add(sakememo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sakememoList;
	}
	public static List<Sakememo> findByUserId(String strUserId){
		int userId = Integer.parseInt(strUserId);
		List<Sakememo> sakememoList = findByUserId(userId);
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
			int sakelogId = SakelogDao.findRatestId();
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
}