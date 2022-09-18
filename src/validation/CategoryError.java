package validation;

public class CategoryError {

	private String categoryNameErrorMessage;

	public String getCategoryNameErrorMessage() {
		return categoryNameErrorMessage;
	}

	public void setCategoryNameErrorMessage(String categoryNameErrorMessage) {
		this.categoryNameErrorMessage = categoryNameErrorMessage;
	}
	
	public boolean isAllFieldNull() {
		return (categoryNameErrorMessage == null);
	}
}
