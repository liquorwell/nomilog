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

/**
 * 酒ログテーブル用DAO
 */
public class SakelogDao {
	
	/** 検索SQL */
	private static final String FIND = "SELECT sakelog_id, sakelog_name, rating, sakelog_comment, category_id "
			+ "FROM t_sakelog ";
	
	/** 検索条件 論理削除されていない */
	private static final String IS_NOT_DELETED = "AND is_deleted = '0' ";
	
	/** 検索条件 酒ログIDによる */
	private static final String BY_SAKELOG_ID = "WHERE sakelog_id = ? " + IS_NOT_DELETED;
	/** 検索条件 酒ログ名による */
	private static final String BY_SAKELOG_NAME = "WHERE user_id = ? AND sakelog_name LIKE ? " + IS_NOT_DELETED;
	/** 検索条件 評価による */
	private static final String BY_RATING = "WHERE user_id = ? AND rating = ? " + IS_NOT_DELETED;
	/** 検索条件 カテゴリIDによる */
	private static final String BY_CATEGORY_ID = "WHERE user_id = ? AND category_id = ? " + IS_NOT_DELETED;
	/** 検索条件 ユーザーIDによる */
	private static final String BY_USER_ID = "WHERE user_id = ? " + IS_NOT_DELETED;
	/** 検索条件 登録日による */
	private static final String BY_INS_DATE = "WHERE user_id = ? AND ins_date BETWEEN ? AND ? " + IS_NOT_DELETED;
	
	/** 並び替え条件 評価による */
	private static final String ORDER_RATING = "ORDER BY rating ";
	/** 並び替え条件 カテゴリIDによる */
	private static final String ORDER_CATEGORY_ID = "ORDER BY category_id ";
	/** 並び替え条件 登録日による */
	private static final String ORDER_INS_DATE = "ORDER BY ins_date ";
	
	/** 並び替え条件 昇順 */
	private static final String ASC = "ASC ";
	/** 並び替え条件 降順 */
	private static final String DESC = "DESC ";
	
	/** 最新の酒ログ検索SQL */
	private static final String FIND_RATEST_SAKELOG_ID = "SELECT sakelog_id "
			+ "FROM (SELECT sakelog_id FROM t_sakelog ORDER BY ins_date DESC) "
			+ "WHERE rownum = 1";
	
	
	/** 登録SQL */
	private static final String INSERT = "INSERT INTO t_sakelog "
			+ "(category_id, user_id, sakelog_id, sakelog_name, rating, sakelog_comment, is_deleted, ins_date, upd_date) "
			+ "VALUES (?, ?, seq_sakelog.NEXTVAL, ?, ?, ?, '0', sysdate, sysdate)";
	/** 更新SQL */
	private static final String UPDATE = "UPDATE t_sakelog "
			+ "SET category_id = ?, sakelog_name = ?, rating = ?, sakelog_comment = ?, upd_date = sysdate "
			+ "WHERE sakelog_id = ?";
	/** 論理削除SQL */
	private static final String DELETE = "UPDATE t_sakelog "
			+ "SET is_deleted = '1' , upd_date = sysdate "
			+ "WHERE sakelog_id = ?";
	/** ユーザー削除時の酒ログ論理削除SQL */
	private static final String DELETE_USER = "UPDATE t_sakelog "
			+ "SET is_deleted = '1', upd_date = sysdate "
			+ "WHERE user_id = ?";
	
	
	
	/**
	 * 酒ログID(int)による検索メソッド
	 * @param sakelogId 酒ログID(int)
	 * @return 1件のSakelog
	 */
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
	/**
	 * 酒ログID(String)による検索メソッド
	 * @param strSakelogId 酒ログID(String)
	 * @return 1件のSakelog
	 */
	public static Sakelog findBySakelogId(String strSakelogId) {
		int sakelogId = Integer.parseInt(strSakelogId);
		Sakelog sakelog = findBySakelogId(sakelogId);
		return sakelog;
	}
	
	/**
	 * ユーザーIDと酒ログ名による検索メソッド
	 * @param userId ユーザーID
	 * @param sakelogName 酒ログ名
	 * @return Sakelogが格納されたList
	 */
	public static List<Sakelog> findBySakelogName(int userId, String sakelogName) {
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND + BY_SAKELOG_NAME + ORDER_INS_DATE + DESC)
		){
			ps.setInt(1, userId);
			ps.setString(2, "%"+sakelogName+"%");
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
	
	/**
	 * ユーザーIDと評価(int)による検索メソッド
	 * @param userId ユーザーID
	 * @param rating 評価(int)
	 * @return Sakelogが格納されたList
	 */
	public static List<Sakelog> findByRating(int userId, int rating) {
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND + BY_RATING + ORDER_INS_DATE + DESC)
		){
			ps.setInt(1, userId);
			ps.setInt(2, rating);
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
	/**
	 * ユーザーIDと評価(String)による検索メソッド
	 * @param userId ユーザーID
	 * @param strRating 評価(String)
	 * @return Sakelogが格納されたList
	 */
	public static List<Sakelog> findByRating(int userId, String strRating) {
		int rating = Integer.parseInt(strRating);
		List<Sakelog> sakelogList = findByRating(userId, rating);
		return sakelogList;
	}
	
	/**
	 * ユーザーIDとカテゴリID(int)による検索メソッド
	 * @param userId ユーザーID
	 * @param categoryId カテゴリID(int)
	 * @return Sakelogが格納されたList
	 */
	public static List<Sakelog> findByCategoryId(int userId, int categoryId) {
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND + BY_CATEGORY_ID + ORDER_INS_DATE + DESC)
		){
			ps.setInt(1, userId);
			ps.setInt(2, categoryId);
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
	/**
	 * ユーザーIDとカテゴリID(String)による検索メソッド
	 * @param userId ユーザーID
	 * @param strCategoryId カテゴリID(String)
	 * @return Sakelogが格納されたList
	 */
	public static List<Sakelog> findByCategoryId(int userId, String strCategoryId) {
		int categoryId = Integer.parseInt(strCategoryId);
		List<Sakelog> sakelogList = findByCategoryId(userId, categoryId);
		return sakelogList;
	}
	
	/**
	 * ユーザーIDによる検索メソッド
	 * @param userId ユーザーID
	 * @param sortStatement where以降(order)の検索文
	 * @return Sakelogが格納されたList
	 */
	private static List<Sakelog> findByUserId(int userId, String sortStatement) {
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
	
		/**
		 * ユーザーIDによる検索メソッド(評価の降順)
		 * @param userId ユーザーID
		 * @return Sakelogが格納されたList
		 */
		public static List<Sakelog>findByUserIdRatingDesc(int userId) {
			List<Sakelog> sakelogList = findByUserId(userId, ORDER_RATING + DESC);
			return sakelogList;
		}
		
		/**
		 * ユーザーIDによる検索メソッド(評価の昇順)
		 * @param userId ユーザーID
		 * @return Sakelogが格納されたList
		 */
		public static List<Sakelog>findByUserIdRatingAsc(int userId) {
			List<Sakelog> sakelogList = findByUserId(userId, ORDER_RATING + ASC);
			return sakelogList;
		}
		
		/**
		 * ユーザーIDによる検索メソッド(カテゴリIDの昇順)
		 * @param userId ユーザーID
		 * @return Sakelogが格納されたList
		 */
		public static List<Sakelog>findByUserIdCategoryIdAsc(int userId) {
			List<Sakelog> sakelogList = findByUserId(userId, ORDER_CATEGORY_ID + ASC);
			return sakelogList;
		}
		
		/**
		 * ユーザーID(int)による検索メソッド(登録日の降順)
		 * @param userId ユーザーID(int)
		 * @return Sakelogが格納されたList
		 */
		public static List<Sakelog>findByUserIdInsDateDesc(int userId) {
			List<Sakelog> sakelogList = findByUserId(userId, ORDER_INS_DATE + DESC);
			return sakelogList;
		}
		/**
		 * ユーザーID(String)による検索メソッド(登録日の降順)
		 * @param userId ユーザーID(String)
		 * @return Sakelogが格納されたList
		 */
		public static List<Sakelog> findByUserIdInsDateDesc(String strUserId){
			int userId = Integer.parseInt(strUserId);
			List<Sakelog> sakelogList = findByUserIdInsDateDesc(userId);
			return sakelogList;
		}
		
		/**
		 * ユーザーIDによる検索メソッド(登録日の昇順)
		 * @param userId ユーザーID
		 * @return Sakelogが格納されたList
		 */
		public static List<Sakelog>findByUserIdInsDateAsc(int userId) {
			List<Sakelog> sakelogList = findByUserId(userId, ORDER_INS_DATE + ASC);
			return sakelogList;
		}
	
	/**
	 * ユーザーIDと登録日による検索メソッド
	 * @param userId ユーザーID
	 * @param strInsDateOld 登録日(古) String yyyy-MM-dd
	 * @param strInsDateNew 登録日(新) String yyyy-MM-dd
	 * @return Sakelogが格納されたList
	 */
	public static List<Sakelog> findByInsDate(int userId, String strInsDateOld, String strInsDateNew) {
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND + BY_INS_DATE + ORDER_INS_DATE + DESC)
		){
			LocalDate insDateOld = LocalDate.of(1800, 1, 1);  //デフォルト値
			if (strInsDateOld != "") {
				insDateOld = LocalDate.parse(strInsDateOld, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}
			LocalDate insDateNew = LocalDate.of(9999, 12, 30);  //デフォルト値
			if (strInsDateNew != "") {
				insDateNew = LocalDate.parse(strInsDateNew, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}
			java.sql.Date sqlInsDateOld = java.sql.Date.valueOf(insDateOld);
			java.sql.Date sqlInsDateNew = java.sql.Date.valueOf(insDateNew.plusDays(1));  //betweenで検索するため
			ps.setInt(1, userId);
			ps.setDate(2, sqlInsDateOld);
			ps.setDate(3, sqlInsDateNew);
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
	
	/**
	 * 最新の酒ログを検索するメソッド <br>
	 * 酒メモ→酒ログ移動時に、酒ログIDを控える際に用いる
	 * @return 最新の酒ログの酒ログID
	 */
	public static int findLatestSakelogId() {
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
	
	
	/**
	 * 登録メソッド
	 * @param sakelog
	 */
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
	
	/**
	 * 更新メソッド
	 * @param sakelog
	 */
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
	
	/**
	 * 論理削除メソッド
	 * @param sakelogId(int)
	 */
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
	/**
	 * 論理削除メソッド
	 * @param strSakelogId(String)
	 */
	public static void delete(String strSakelogId) {
		int sakelogId = Integer.parseInt(strSakelogId);
		delete(sakelogId);
	}
	
	/**
	 * ユーザー論理削除時の酒ログ論理削除メソッド <br>
	 * ユーザーIDに紐づく酒ログ全件を論理削除
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
	
	
	
	/**
	 * 検索SQLの実行結果をSakelogにセットするメソッド
	 * @param rs ResultSet
	 * @return 1件のSakelog
	 * @throws SQLException
	 */
	private static Sakelog resultSakelog(ResultSet rs) throws SQLException {
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