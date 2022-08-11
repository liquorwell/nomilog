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
	
	private static final String FIND_BY_USER_ID = "SELECT sklg.sakelog_name, sklg.rating, sklg.sakelog_comment, ctgr.category_name "
			+ "FROM t_sakelog sklg "
			+ "INNER JOIN m_category ctgr ON sklg.category_id = ctgr.category_id "
			+ "WHERE sklg.user_id = ? AND sklg.is_deleted = '0'";
	
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
	
}
