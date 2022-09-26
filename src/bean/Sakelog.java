package bean;

/** 酒ログ用bean */
public class Sakelog {
	
	/** 酒ログID */
	private int sakelogId;
	/** 酒ログ名 */
	private String sakelogName;
	/** 評価 */
	private int rating;
	/** コメント */
	private String sakelogComment;
	/** カテゴリ */
	private Category category;
	/** ユーザー */
	private User user;
	
	
	/** デフォルトコンストラクタ */
	public Sakelog() {
		
	}
	
	/**
	 * 引数を受け取り、フィールドにセットするコンストラクタ
	 * @param sakelogId			酒ログID (nullを受け取った場合、0をセット)
	 * @param sakelogName		酒ログ名
	 * @param rating			評価 (nullを受け取った場合、0をセット)
	 * @param sakelogComment	コメント
	 * @param category			カテゴリ
	 * @param user				ユーザー
	 */
	public Sakelog(String sakelogId, String sakelogName, String rating, String sakelogComment, Category category, User user) {
		this.setSakelogId(sakelogId);
		this.setSakelogName(sakelogName);
		this.setRating(rating);
		this.setSakelogComment(sakelogComment);
		this.setCategory(category);
		this.setUser(user);
	}
	
	/**
	 * 酒メモから酒ログを作成するコンストラクタ
	 * @param sakememo	酒メモ
	 */
	public Sakelog(Sakememo sakememo) {
		this.setSakelogName(sakememo.getSakememoName());
		this.setSakelogComment(sakememo.getSakememoComment());
		this.setCategory(sakememo.getCategory());
		this.setUser(sakememo.getUser());
	}
	
	
	/** @return 酒ログID */
	public int getSakelogId() {
		return sakelogId;
	}
	
	/** @param sakelogId 酒ログID */
	public void setSakelogId(int sakelogId) {
		this.sakelogId = sakelogId;
	}
	/**
	 * @param strSakelogId 酒ログID(String) <br>
	 * nullを受け取った場合、0をセットする
	 */
	public void setSakelogId(String strSakelogId) {
		if (strSakelogId == null) {
			this.sakelogId = 0;
		} else {
			this.sakelogId = Integer.parseInt(strSakelogId);
		}
	}
	
	/** @return 酒ログ名 */
	public String getSakelogName() {
		return sakelogName;
	}
	
	/** @param sakelogName 酒ログ名 */
	public void setSakelogName(String sakelogName) {
		this.sakelogName = sakelogName;
	}
	
	/** @return 評価 */
	public int getRating() {
		return rating;
	}
	
	/** @param rating 評価 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	/**
	 * @param strRating 評価(String) <br>
	 * nullを受け取った場合、0をセットする
	 */
	public void setRating(String strRating) {
		if (strRating == null) {
			this.rating = 0;
		} else {
			this.rating = Integer.parseInt(strRating);
		}
	}
	
	/** @return コメント */
	public String getSakelogComment() {
		return sakelogComment;
	}
	
	/** @param sakelogComment コメント */
	public void setSakelogComment(String sakelogComment) {
		this.sakelogComment = sakelogComment;
	}
	
	/** @return カテゴリ */
	public Category getCategory() {
		return category;
	}
	
	/** @param category カテゴリ */
	public void setCategory(Category category) {
		this.category = category;
	}
	
	/** @return ユーザー */
	public User getUser() {
		return user;
	}
	
	/** @param user ユーザー */
	public void setUser(User user) {
		this.user = user;
	}
	
}
