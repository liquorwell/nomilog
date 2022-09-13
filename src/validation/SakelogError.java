package validation;

public class SakelogError {

	private String sakelogNameErrorMessage;
	private String categoryErrorMessage;
	private String ratingErrorMessage;
	private String sakelogCommentErrorMessage;
	private String filterErrorMessage;

	
	public String getSakelogNameErrorMessage() {
		return sakelogNameErrorMessage;
	}

	public void setSakelogNameErrorMessage(String sakelogNameErrorMessage) {
		this.sakelogNameErrorMessage = sakelogNameErrorMessage;
	}

	public String getCategoryErrorMessage() {
		return categoryErrorMessage;
	}

	public void setCategoryErrorMessage(String categoryErrorMessage) {
		this.categoryErrorMessage = categoryErrorMessage;
	}

	public String getRatingErrorMessage() {
		return ratingErrorMessage;
	}

	public void setRatingErrorMessage(String ratingErrorMessage) {
		this.ratingErrorMessage = ratingErrorMessage;
	}

	public String getSakelogCommentErrorMessage() {
		return sakelogCommentErrorMessage;
	}

	public void setSakelogCommentErrorMessage(String sakelogCommentErrorMessage) {
		this.sakelogCommentErrorMessage = sakelogCommentErrorMessage;
	}

	public String getFilterErrorMessage() {
		return filterErrorMessage;
	}

	public void setFilterErrorMessage(String filterErrorMessage) {
		this.filterErrorMessage = filterErrorMessage;
	}
	
	
	public boolean isAllFieldNull() {
		return (this.getSakelogNameErrorMessage() == null && this.getCategoryErrorMessage() == null && this.getRatingErrorMessage() == null &&
				this.getSakelogCommentErrorMessage() == null && this.getFilterErrorMessage() == null);
	}
	
}
