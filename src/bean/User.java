package bean;

/** ユーザー用bean */
public class User {
	
	/** ユーザーID */
	private int userId;
	/** ユーザー名 */
	private String userName;
	/** パスワード */
	private String userPass;
	
	
	/** デフォルトコンストラクタ */
	public User() {
		
	}
	
	/**
	 * 引数を受け取り、フィールドにセットするコンストラクタ
	 * @param userId	ユーザーID (nullを受け取った場合、0をセット)
	 * @param userName	ユーザー名
	 * @param userPass	パスワード
	 */
	public User(String userId, String userName, String userPass) {
		this.setUserId(userId);
		this.setUserName(userName);
		this.setUserPass(userPass);
	}
	
	
	/** @return ユーザーID */
	public int getUserId() {
		return userId;
	}
	
	/** @param userId ユーザーID */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @param strUserId ユーザーID(String) <br>
	 * nullを受け取った場合、0をセットする
	 */
	public void setUserId(String strUserId) {
		if(strUserId == null) {
			this.userId = 0;
		} else {
			this.userId = Integer.parseInt(strUserId);
		}
	}
	
	/** @return ユーザー名 */
	public String getUserName() {
		return userName;
	}
	
	/** @param userName ユーザー名 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/** @return パスワード */
	public String getUserPass() {
		return userPass;
	}
	
	/** @param userPass パスワード */
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
}
