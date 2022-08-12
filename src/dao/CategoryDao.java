package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Category;

public class CategoryDao {
	private static final String FIND_BY_ID = "SELECT category_id, category_name "
			+ "FROM m_category "
			+ "WHERE category_id = ? AND is_deleted = '0'";
	private static final String FIND_BY_USER_ID = "SELECT category_id, category_name "
			+ "FROM m_category "
			+ "WHERE user_id = ? AND is_deleted = '0'";
	
	
	public static Category findById(String categoryId) {
		Category category = null;
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_ID)
		){
			ps.setString(1, categoryId);
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
	
	public static List<Category> findByUserId(int userId){
		List<Category> categoryList = new ArrayList<Category>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_USER_ID)
		){
			String strUserId = String.valueOf(userId);
			ps.setString(1, strUserId);
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
}
