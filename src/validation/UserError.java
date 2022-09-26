package validation;

/** ユーザーのエラー用bean */
public class UserError {

	/** ユーザー名のエラーメッセージ */
	private String userNameErrorMessage;
	/** パスワードのエラーメッセージ */
	private String userPassErrorMessage;
	/** 新しいパスワードのエラーメッセージ */
	private String userNewPassErrorMessage;
	/** ユーザーの存在判定のエラーメッセージ */
	private String userExistErrorMessage;
	
	
	/** @return ユーザー名のエラーメッセージ */
	public String getUserNameErrorMessage() {
		return userNameErrorMessage;
	}
	
	/** @param userNameErrorMessage ユーザー名のエラーメッセージ */
	public void setUserNameErrorMessage(String userNameErrorMessage) {
		this.userNameErrorMessage = userNameErrorMessage;
	}
	
	/** @return パスワードのエラーメッセージ */
	public String getUserPassErrorMessage() {
		return userPassErrorMessage;
	}
	
	/** @param userPassErrorMessage パスワードのエラーメッセージ */
	public void setUserPassErrorMessage(String userPassErrorMessage) {
		this.userPassErrorMessage = userPassErrorMessage;
	}
	
	/** @return 新しいパスワードのエラーメッセージ */
	public String getUserNewPassErrorMessage() {
		return userNewPassErrorMessage;
	}
	
	/** @param userNewPassErrorMessage 新しいパスワードのエラーメッセージ */
	public void setUserNewPassErrorMessage(String userNewPassErrorMessage) {
		this.userNewPassErrorMessage = userNewPassErrorMessage;
	}
	
	/** @return ユーザーの存在判定のエラーメッセージ */
	public String getUserExistErrorMessage() {
		return userExistErrorMessage;
	}
	
	/** @param userExistErrorMessage ユーザーの存在判定のエラーメッセージ */
	public void setUserExistErrorMessage(String userExistErrorMessage) {
		this.userExistErrorMessage = userExistErrorMessage;
	}
	
	
	/**
	 * ユーザー名とパスワードのフィールドがnullかどうかを判定するメソッド
	 * @return すべてのフィールドがnullの場合はtrue, そうでない場合はfalse
	 */
	public boolean isNameAndPassErrorNull() {
		return (this.getUserNameErrorMessage() == null && this.getUserPassErrorMessage() == null);
	}
	
}
