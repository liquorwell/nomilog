package bean;

/** 酒メモ用bean */
public class Sakememo {
	
	/** 酒メモID */
	private int sakememoId;
	/** 酒メモ名 */
	private String sakememoName;
	/** コメント */
	private String sakememoComment;
	/** 移動後酒ログID */
	private int sakelogId;
	/** カテゴリ */
	private Category category;
	/** ユーザー */
	private User user;
	
	
	/** デフォルトコンストラクタ */
	public Sakememo() {
		
	}
	
	/**
	 * 引数を受け取り、フィールドにセットするコンストラクタ
	 * @param sakememoId		酒メモID (nullを受け取った場合、0をセット)
	 * @param sakememoName		酒メモ名
	 * @param sakememoComment	コメント
	 * @param sakelogId			移動後酒ログID (nullを受け取った場合、0をセット)
	 * @param category			カテゴリ
	 * @param user				ユーザー
	 */
	public Sakememo(String sakememoId, String sakememoName, String sakememoComment, String sakelogId, Category category, User user) {
		this.setSakememoId(sakememoId);
		this.setSakememoName(sakememoName);
		this.setSakememoComment(sakememoComment);
		this.setSakelogId(sakelogId);
		this.setCategory(category);
		this.setUser(user);
	}
	
	
	/** @return 酒メモID */
	public int getSakememoId() {
		return sakememoId;
	}
	
	/** @param sakememoId 酒メモID */
	public void setSakememoId(int sakememoId) {
		this.sakememoId = sakememoId;
	}
	/**
	 * @param strSakememoId 酒メモID(String) <br>
	 * nullを受け取った場合、0をセットする
	 */
	public void setSakememoId(String strSakememoId) {
		if (strSakememoId == null) {
			this.sakememoId = 0;
		} else {
			this.sakememoId = Integer.parseInt(strSakememoId);
		}
	}
	
	/** @return 酒メモ名 */
	public String getSakememoName() {
		return sakememoName;
	}
	
	/** @param sakememoName 酒メモ名 */
	public void setSakememoName(String sakememoName) {
		this.sakememoName = sakememoName;
	}
	
	/** @return コメント */
	public String getSakememoComment() {
		return sakememoComment;
	}
	
	/** @param sakememoComment コメント */
	public void setSakememoComment(String sakememoComment) {
		this.sakememoComment = sakememoComment;
	}
	
	/** @return 移動後酒ログID */
	public int getSakelogId() {
		return sakelogId;
	}
	
	/** @param sakelogId 移動後酒ログID */
	public void setSakelogId(int sakelogId) {
		this.sakelogId = sakelogId;
	}
	/**
	 * @param strSakelogId 移動後酒ログID(String) <br>
	 * nullを受け取った場合、0をセットする
	 */
	public void setSakelogId(String strSakelogId) {
		if(strSakelogId == null) {
			this.sakelogId = 0;
		} else {
			this.sakelogId = Integer.parseInt(strSakelogId);
		}
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
