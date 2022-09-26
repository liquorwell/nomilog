package bean;

/** カテゴリ用bean */
public class Category {
	
	/** カテゴリID */
	private int categoryId;
	/** カテゴリ名 */
	private String categoryName;
	/** ユーザー */
	private User user;
	
	
	/** デフォルトコンストラクタ */
	public Category() {
	}
	
	/**
	 * 引数を受け取り、フィールドにセットするコンストラクタ
	 * @param categoryId	カテゴリID
	 * 						nullを受け取った場合、0をセット
	 * @param categoryName	カテゴリ名
	 * @param user			ユーザー
	 */
	public Category(String categoryId, String categoryName, User user) {
		this.setCategoryId(categoryId);
		this.setCategoryName(categoryName);
		this.setUser(user);
	}
	
	
	/** @return カテゴリID */
	public int getCategoryId() {
		return categoryId;
	}
	
	/** @param categoryId カテゴリID */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @param strCategoryId カテゴリID(String型)
	 * nullを受け取った場合、0をセットする
	 */
	public void setCategoryId(String strCategoryId) {
		if (strCategoryId == null) {
			this.categoryId = 0; 
		} else {
			this.categoryId = Integer.parseInt(strCategoryId);
		}
	}
	
	/** @return カテゴリ名 */
	public String getCategoryName() {
		return categoryName;
	}
	
	/** @param categoryName カテゴリ名 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
