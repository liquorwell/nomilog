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

/**
 * 酒メモテーブル用DAO
 */
public class SakememoDao {
	
	/** 検索SQL */
	private static final String FIND = "SELECT sakememo_id, sakememo_name, sakememo_comment, category_id "
			+ "FROM t_sakememo ";
	
	/** 検索条件 論理削除されていない */
	private static final String IS_NOT_DELETED = "AND is_deleted = '0' ";
	
	/** 検索条件 酒メモIDによる */
	private static final String BY_SAKEMEMO_ID = "WHERE sakememo_id = ? " + IS_NOT_DELETED;
	/** 検索条件 酒メモ名による */
	private static final String BY_SAKEMEMO_NAME = "WHERE user_id = ? AND sakememo_name LIKE ? " + IS_NOT_DELETED;
	/** 検索条件 カテゴリIDによる */
	private static final String BY_CATEGORY_ID = "WHERE user_id = ? AND category_id = ? " + IS_NOT_DELETED;
	/** 検索条件 ユーザーIDによる */
	private static final String BY_USER_ID = "WHERE user_id = ? " + IS_NOT_DELETED;
	/** 検索条件 登録日による */
	private static final String BY_INS_DATE = "WHERE user_id = ? AND ins_date BETWEEN ? AND ? " + IS_NOT_DELETED;
	
	/** 並び替え条件 カテゴリIDによる */
	private static final String ORDER_CATEGORY_ID = "ORDER BY category_id ";
	/** 並び替え条件 登録日による */
	private static final String ORDER_INS_DATE = "ORDER BY ins_date ";
	
	/** 並び替え条件 昇順 */
	private static final String ASC = "ASC ";
	/** 並び替え条件 降順 */
	private static final String DESC = "DESC ";
	
	
	/** 登録SQL */
	private static final String INSERT = "INSERT INTO t_sakememo "
			+ "(category_id, user_id, sakememo_id, sakememo_name, sakememo_comment, sakelog_id, is_deleted, ins_date, upd_date) "
			+ "VALUES (?, ?, seq_sakememo.NEXTVAL, ?, ?, 0, '0', sysdate, sysdate)";
	/** 更新SQL */
	private static final String UPDATE = "UPDATE t_sakememo "
			+ "SET category_id = ?, sakememo_name = ?, sakememo_comment = ?, upd_date = sysdate "
			+ "WHERE sakememo_id = ?";
	/** 論理削除SQL */
	private static final String DELETE = "UPDATE t_sakememo "
			+ "SET is_deleted = '1', upd_date = sysdate "
			+ "WHERE sakememo_id = ?";
	/** ユーザー削除時の論理削除SQL */
	private static final String DELETE_USER = "UPDATE t_sakememo "
			+ "SET is_deleted = '1', upd_date = sysdate "
			+ "WHERE user_id = ?";
	/** 酒メモ→酒ログ移動時の論理削除SQL */
	private static final String MOVE = "UPDATE t_sakememo "
			+ "SET sakelog_id = ?, is_deleted = '1', upd_date = sysdate "
			+ "WHERE sakememo_id = ?";
	
	
	
	/**
	 * 酒メモID(int)による検索メソッド
	 * @param sakememoId 酒メモID(int)
	 * @return 1件のSakememo
	 */
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
	/**
	 * 酒メモID(String)による検索メソッド
	 * @param strSakememoId 酒メモID(String)
	 * @return 1件のSakememo
	 */
	public static Sakememo findBySakememoId(String strSakememoId) {
		int sakememoId = Integer.parseInt(strSakememoId);
		Sakememo sakememo = findBySakememoId(sakememoId);
		return sakememo;
	}
	
	/**
	 * ユーザーIDと酒メモ名による検索メソッド
	 * @param userId ユーザーID
	 * @param sakememoName 酒メモ名
	 * @return Sakememoが格納されたList
	 */
	public static List<Sakememo> findBySakememoName(int userId, String sakememoName) {
		List<Sakememo> sakememoList = new ArrayList<Sakememo>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND + BY_SAKEMEMO_NAME + ORDER_INS_DATE + DESC)
		){
			ps.setInt(1, userId);
			ps.setString(2, "%"+sakememoName+"%");
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
	
	/**
	 * ユーザーIDとカテゴリID(int)による検索メソッド
	 * @param userId ユーザーID
	 * @param categoryId カテゴリID(int)
	 * @return Sakememoが格納されたList
	 */
	public static List<Sakememo> findByCategoryId(int userId, int categoryId){
		List<Sakememo> sakememoList = new ArrayList<Sakememo>();
		try (
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(FIND + BY_CATEGORY_ID + ORDER_INS_DATE + DESC)
		){
			ps.setInt(1, userId);
			ps.setInt(2, categoryId);
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
	/**
	 * ユーザーIDとカテゴリID(String)による検索メソッド
	 * @param userId ユーザーID
	 * @param strCategoryId カテゴリID(String)
	 * @return Sakememoが格納されたList
	 */
	public static List<Sakememo> findByCategoryId(int userId, String strCategoryId) {
		int categoryId = Integer.parseInt(strCategoryId);
		List<Sakememo> sakememoList = findByCategoryId(userId, categoryId);
		return sakememoList;
	}
	
	/**
	 * ユーザーIDによる検索メソッド
	 * @param userId ユーザーID
	 * @param sortStatement where以降(order)の検索文
	 * @return Sakememoが格納されたList
	 */
	private static List<Sakememo> findByUserId(int userId, String sortStatement){
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
	
		/**
		 * ユーザーIDによる検索メソッド(カテゴリIDの昇順)
		 * @param userId ユーザーID
		 * @return Sakememoが格納されたList
		 */
		public static List<Sakememo> findByUserIdCategoryIdAsc(int userId){
			List<Sakememo> sakememoList = findByUserId(userId, ORDER_CATEGORY_ID + ASC);
			return sakememoList;
		}
		
		/**
		 * ユーザーID(int)による検索メソッド(登録日の降順)
		 * @param userId ユーザーID(int)
		 * @return Sakememoが格納されたList
		 */
		public static List<Sakememo> findByUserIdInsDateDesc(int userId){
			List<Sakememo> sakememoList = findByUserId(userId, ORDER_INS_DATE + DESC);
			return sakememoList;
		}
		/**
		 * ユーザーID(String)による検索メソッド(登録日の降順)
		 * @param userId ユーザーID(String)
		 * @return Sakememoが格納されたList
		 */
		public static List<Sakememo> findByUserIdInsDateDesc(String strUserId) {
			int userId = Integer.parseInt(strUserId);
			List<Sakememo> sakememoList = findByUserIdInsDateDesc(userId);
			return sakememoList;
		}
		
		/**
		 * ユーザーIDによる検索メソッド(登録日の昇順)
		 * @param userId ユーザーID
		 * @return Sakememoが格納されたList
		 */
		public static List<Sakememo> findByUserIdInsDateAsc(int userId){
			List<Sakememo> sakememoList = findByUserId(userId, ORDER_INS_DATE + ASC);
			return sakememoList;
		}
	
	/**
	 * ユーザーIDと登録日による検索メソッド
	 * @param userId ユーザーID
	 * @param strInsDateOld 登録日(古) String yyyy-MM-dd
	 * @param strInsDateNew 登録日(新) String yyyy-MM-dd
	 * @return Sakememoが格納されたList
	 */
	public static List<Sakememo> findByInsDate(int userId, String strInsDateOld, String strInsDateNew){
		List<Sakememo> sakememoList = new ArrayList<Sakememo>();
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
				Sakememo sakememo = resultSakememo(rs);
				sakememoList.add(sakememo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sakememoList;
	}
	
	
	/**
	 * 登録メソッド
	 * @param sakememo
	 */
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
	
	/**
	 * 更新メソッド
	 * @param sakememo
	 */
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
	
	/**
	 * 論理削除メソッド
	 * @param sakememoId 酒メモID(int)
	 */
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
	/**
	 * 論理削除メソッド
	 * @param strSakememoId 酒メモID(String)
	 */
	public static void delete(String strSakememoId) {
		int sakememoId = Integer.parseInt(strSakememoId);
		delete(sakememoId);
	}
	
	/**
	 * ユーザー論理削除時の酒メモ論理削除メソッド <br>
	 * ユーザーIDに紐づく酒メモ全件を論理削除
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
	 * 酒ログ→酒メモ移動用SQL <br>
	 * 酒メモの論理削除と、移動後の酒ログIDの更新を行う
	 * @param sakememoId 酒メモID(int)
	 */
	public static void move(int sakememoId) {
		try(
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(MOVE)
		){
			int sakelogId = SakelogDao.findLatestSakelogId();
			ps.setInt(1, sakelogId);
			ps.setInt(2, sakememoId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 酒ログ→酒メモ移動用SQL <br>
	 * 酒メモの論理削除と、移動後の酒ログIDの更新を行う
	 * @param sakememoId 酒メモID(String)
	 */
	public static void move(String strSakememoId) {
		int sakememoId = Integer.parseInt(strSakememoId);
		move(sakememoId);
	}
	
	
	/**
	 * 検索SQLの実行結果をSakememoにセットするメソッド
	 * @param rs ResultSet
	 * @return 1件のSakememo
	 * @throws SQLException
	 */
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
