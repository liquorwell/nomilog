package validation;

public class SakememoError {

	private String sakememoNameErrorMessage;
	private String categoryErrorMessage;
	private String sakememoCommentErrorMessage;
	private String filterErrorMessage;
	
	
	public String getSakememoNameErrorMessage() {
		return sakememoNameErrorMessage;
	}
	public void setSakememoNameErrorMessage(String sakememoNameErrorMessage) {
		this.sakememoNameErrorMessage = sakememoNameErrorMessage;
	}
	public String getCategoryErrorMessage() {
		return categoryErrorMessage;
	}
	public void setCategoryErrorMessage(String categoryErrorMessage) {
		this.categoryErrorMessage = categoryErrorMessage;
	}
	public String getSakememoCommentErrorMessage() {
		return sakememoCommentErrorMessage;
	}
	public void setSakememoCommentErrorMessage(String sakememoCommentErrorMessage) {
		this.sakememoCommentErrorMessage = sakememoCommentErrorMessage;
	}
	public String getFilterErrorMessage() {
		return filterErrorMessage;
	}
	public void setFilterErrorMessage(String filterErrorMessage) {
		this.filterErrorMessage = filterErrorMessage;
	}
	
	public boolean isAllFieldNull() {
		return (this.getSakememoNameErrorMessage() == null && this.getCategoryErrorMessage() == null &&
				this.getSakememoCommentErrorMessage() == null && this.getFilterErrorMessage() == null);
	}
}
