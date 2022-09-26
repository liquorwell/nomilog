package validation;

/** 酒メモのエラー用bean */
public class SakememoError {

	/** 酒メモ名のエラーメッセージ */
	private String sakememoNameErrorMessage;
	/** カテゴリのエラーメッセージ */
	private String categoryErrorMessage;
	/** コメントのエラーメッセージ */
	private String sakememoCommentErrorMessage;
	/** 絞り込みのエラーメッセージ */
	private String filterErrorMessage;
	
	
	/** @return 酒メモ名のエラーメッセージ */
	public String getSakememoNameErrorMessage() {
		return sakememoNameErrorMessage;
	}
	
	/** @param sakememoNameErrorMessage 酒メモ名のエラーメッセージ */
	public void setSakememoNameErrorMessage(String sakememoNameErrorMessage) {
		this.sakememoNameErrorMessage = sakememoNameErrorMessage;
	}
	
	/** @return カテゴリのエラーメッセージ */
	public String getCategoryErrorMessage() {
		return categoryErrorMessage;
	}
	
	/** @param categoryErrorMessage カテゴリのエラーメッセージ*/
	public void setCategoryErrorMessage(String categoryErrorMessage) {
		this.categoryErrorMessage = categoryErrorMessage;
	}
	
	/** @return コメントのエラーメッセージ */
	public String getSakememoCommentErrorMessage() {
		return sakememoCommentErrorMessage;
	}
	
	/** @param sakememoCommentErrorMessage コメントのエラーメッセージ */
	public void setSakememoCommentErrorMessage(String sakememoCommentErrorMessage) {
		this.sakememoCommentErrorMessage = sakememoCommentErrorMessage;
	}
	
	/** @return 絞り込み条件のエラーメッセージ */
	public String getFilterErrorMessage() {
		return filterErrorMessage;
	}
	
	/** @param filterErrorMessage 絞り込み条件のエラーメッセージ */
	public void setFilterErrorMessage(String filterErrorMessage) {
		this.filterErrorMessage = filterErrorMessage;
	}
	
}
