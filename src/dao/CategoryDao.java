package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Category;

/**
 * カテゴリテーブル用DAO
 */
public class CategoryDao {
	
	/** カテゴリIDによる検索SQL */
	private static final String FIND_BY_CATEGORY_ID = "SELECT category_id, category_name "
			+ "FROM m_category "
			+ "WHERE category_id = ? AND is_deleted = '0'";
	/** ユーザーIDによる検索SQL */
	private static final String FIND_BY_USER_ID = "SELECT category_id, category_name "
			+ "FROM m_category "
			+ "WHERE user_id = ? AND is_deleted = '0'";
	
	
	/** 登録SQL */
	private static final String INSERT = "INSERT INTO m_category "
			+ "(category_id, category_name, user_id, is_deleted, ins_date, upd_date) "
			+ "VALUES (seq_category.NEXTVAL, ?, ?, '0', sysdate, sysdate)";
	/** 更新SQL */
	private static final String UPDATE = "UPDATE m_category "
			+ "SET category_name = ?, upd_date = sysdate "
			+ "WHERE category_id = ?";
	/** 論理削除SQL */
	private static final String DELETE = "UPDATE m_category "
			+ "SET is_deleted = '1' , upd_date = sysdate "
			+ "WHERE category_id = ?";
	/** ユーザー削除時のカテゴリ論理削除SQL */
	private static final String DELETE_USER = "UPDATE m_category "
			+ "SET is_deleted = '1', upd_date = sysdate "
			+ "WHERE user_id = ?";
	
	
	
	/**
	 * カテゴリID(int)による検索メソッド
	 * @param categoryId カテゴリID
	 * @return 1件のCategory 
	 */
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
	/**
	 * カテゴリID(String)による検索メソッド
	 * @param strCategoryId カテゴリID(String)
	 * @return 	1件のCategory <br>
	 * 			※nullを受け取った場合、nullを返す
	 */
	public static Category findByCategoryId(String strCategoryId) {
		if (strCategoryId == null) {
			return null;
		}
		int categoryId = Integer.parseInt(strCategoryId);
		Category category = findByCategoryId(categoryId);
		return category;
	}
	
	/**
	 * ユーザーID(int)による検索メソッド
	 * @param userId ユーザーID
	 * @return	Categoryが格納されたList
	 */
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
	/**
	 * ユーザーID(String)による検索メソッド
	 * @param strUserId ユーザーID(String)
	 * @return	Categoryが格納されたList
	 */
	public static List<Category> findByUserId(String strUserId){
		int userId = Integer.parseInt(strUserId);
		List<Category> categoryList = findByUserId(userId);
		return categoryList;
	}
	
	
	/**
	 * 登録メソッド
	 * @param category
	 */
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
	
	/**
	 * 更新メソッド
	 * @param category
	 */
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
	
	/**
	 * 論理削除メソッド
	 * @param categoryId カテゴリID(int)
	 */
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
	/**
	 * 論理削除メソッド
	 * @param strCategoryId カテゴリID(String)
	 */
	public static void delete(String strCategoryId) {
		int categoryId = Integer.parseInt(strCategoryId);
		delete(categoryId);
	}
	
	/**
	 * ユーザー論理削除時のカテゴリ論理削除メソッド <br>
	 * ユーザーIDに紐づくカテゴリ全件を論理削除
	 * @param userId ユーザーID
	 */
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
