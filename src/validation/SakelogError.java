package validation;

/** 酒ログのエラー用bean */
public class SakelogError {

	/** 酒ログ名のエラーメッセージ */
	private String sakelogNameErrorMessage;
	/** カテゴリのエラーメッセージ */
	private String categoryErrorMessage;
	/** 評価のエラーメッセージ */
	private String ratingErrorMessage;
	/** コメントのエラーメッセージ */
	private String sakelogCommentErrorMessage;
	/** 絞り込みのエラーメッセージ */
	private String filterErrorMessage;
	
	
	/** @return 酒ログ名のエラーメッセージ */
	public String getSakelogNameErrorMessage() {
		return sakelogNameErrorMessage;
	}

	/** @param sakelogNameErrorMessage 酒ログ名のエラーメッセージ */
	public void setSakelogNameErrorMessage(String sakelogNameErrorMessage) {
		this.sakelogNameErrorMessage = sakelogNameErrorMessage;
	}

	/** @return カテゴリのエラーメッセージ */
	public String getCategoryErrorMessage() {
		return categoryErrorMessage;
	}

	/** @param categoryErrorMessage カテゴリのエラーメッセージ */
	public void setCategoryErrorMessage(String categoryErrorMessage) {
		this.categoryErrorMessage = categoryErrorMessage;
	}

	/** @return 評価のエラーメッセージ */
	public String getRatingErrorMessage() {
		return ratingErrorMessage;
	}

	/** @param ratingErrorMessage 評価のエラーメッセージ */
	public void setRatingErrorMessage(String ratingErrorMessage) {
		this.ratingErrorMessage = ratingErrorMessage;
	}

	/** @return コメントのエラーメッセージ */
	public String getSakelogCommentErrorMessage() {
		return sakelogCommentErrorMessage;
	}

	/** @param sakelogCommentErrorMessage コメントのエラーメッセージ */
	public void setSakelogCommentErrorMessage(String sakelogCommentErrorMessage) {
		this.sakelogCommentErrorMessage = sakelogCommentErrorMessage;
	}

	/** @return 絞り込みのエラーメッセージ */
	public String getFilterErrorMessage() {
		return filterErrorMessage;
	}

	/** @param filterErrorMessage 絞り込みのエラーメッセージ */
	public void setFilterErrorMessage(String filterErrorMessage) {
		this.filterErrorMessage = filterErrorMessage;
	}
	
}
