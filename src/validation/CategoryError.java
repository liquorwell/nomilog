package validation;

/** カテゴリのエラー用bean */
public class CategoryError {

	/** カテゴリ名のエラーメッセージ */
	private String categoryNameErrorMessage;
	
	
	/** @return カテゴリ名のエラーメッセージ */
	public String getCategoryNameErrorMessage() {
		return categoryNameErrorMessage;
	}

	/** @param categoryNameErrorMessage カテゴリ名のエラーメッセージ */
	public void setCategoryNameErrorMessage(String categoryNameErrorMessage) {
		this.categoryNameErrorMessage = categoryNameErrorMessage;
	}
	
	
	/**
	 * すべてのフィールドがnullかどうかを判定するメソッド
	 * @return すべてのフィールドがnullの場合はtrue, そうでない場合はfalse
	 */
	public boolean isAllFieldNull() {
		return (this.getCategoryNameErrorMessage() == null);
	}
}
