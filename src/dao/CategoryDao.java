package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Category;

public class CategoryDao {
	
	private static final String FIND_BY_CATEGORY_ID = "SELECT category_id, category_name "
			+ "FROM m_category "
			+ "WHERE category_id = ? AND is_deleted = '0'";
	
	private static final String FIND_BY_USER_ID = "SELECT category_id, category_name "
			+ "FROM m_category "
			+ "WHERE user_id = ? AND is_deleted = '0'";
	
	
	private static final String INSERT = "INSERT INTO m_category "
			+ "(category_id, category_name, user_id, is_deleted, ins_date, upd_date) "
			+ "VALUES (seq_category.NEXTVAL, ?, ?, '0', sysdate, sysdate)";
	
	private static final String UPDATE = "UPDATE m_category "
			+ "SET category_name = ?, upd_date = sysdate "
			+ "WHERE category_id = ?";
	
	private static final String DELETE = "UPDATE m_category "
			+ "SET is_deleted = '1' , upd_date = sysdate "
			+ "WHERE category_id = ?";
	
	private static final String DELETE_USER = "UPDATE m_category "
			+ "SET is_deleted = '1', upd_date = sysdate "
			+ "WHERE user_id = ?";
	
	
	
	public static Category findByCategoryId(int categoryId) {
		Category category = null;
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_CATEGORY_ID)
		){
			ps.setInt(1, categoryId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				category = new Category();
				category.setCategoryId(rs.getInt("category_id"));
				category.setCategoryName(rs.getString("category_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}
	public static Category findByCategoryId(String strCategoryId) {
		if (strCategoryId == null) {
			return null;
		}
		int categoryId = Integer.parseInt(strCategoryId);
		Category category = findByCategoryId(categoryId);
		return category;
	}
	
	public static List<Category> findByUserId(int userId){
		List<Category> categoryList = new ArrayList<Category>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_USER_ID)
		){
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setCategoryId(rs.getInt("category_id"));				
				category.setCategoryName(rs.getString("category_name"));
				categoryList.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryList;
	}
	public static List<Category> findByUserId(String strUserId){
		int userId = Integer.parseInt(strUserId);
		List<Category> categoryList = findByUserId(userId);
		return categoryList;
	}
	
	
	public static void insert(Category category) {
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(INSERT)
		){
			ps.setString(1, category.getCategoryName());
			ps.setInt(2, category.getUser().getUserId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void update(Category category) {
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(UPDATE)
		){
			ps.setString(1, category.getCategoryName());
			ps.setInt(2, category.getCategoryId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void delete(int categoryId) {
		try (
				Connection con = DBManager.getConnection();
				PreparedStatement ps = con.prepareStatement(DELETE)
			){
				ps.setInt(1, categoryId);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public static void delete(String strCategoryId) {
		int categoryId = Integer.parseInt(strCategoryId);
		delete(categoryId);
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
