package validation;

public class CategoryValidation extends Validation {

	private static final int CATEGORY_NAME_MIN_LENGTH = 1;
	private static final int CATEGORY_NAME_MAX_LENGTH = 20;
	
	private static final String FILL_CATEGORY_NAME = "カテゴリ名を入力してください。";
	private static final String NOT_CORRECT_CATEGORY_NAME = "カテゴリ名は" + CATEGORY_NAME_MAX_LENGTH + "文字以内で入力してください。";
	
	public static CategoryError categoryNameValidation(String categoryName) {
		CategoryError categoryError = new CategoryError();
		
		if(isEmpty(categoryName)) {
			categoryError.setCategoryNameErrorMessage(FILL_CATEGORY_NAME);
		} else if (isNotCorrectLength(categoryName, CATEGORY_NAME_MIN_LENGTH, CATEGORY_NAME_MAX_LENGTH)) {
			categoryError.setCategoryNameErrorMessage(NOT_CORRECT_CATEGORY_NAME);
		}
		
		if(categoryError.isAllFieldNull()) {
			categoryError = null;
		}
		
		return categoryError;
	}
}
