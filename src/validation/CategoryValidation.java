package validation;

/**
 * カテゴリのバリデーション用
 */
public class CategoryValidation extends Validation {

	
	/** カテゴリ名の最小文字数 */
	private static final int CATEGORY_NAME_MIN_LENGTH = 1;
	/** カテゴリ名の最大文字数 */
	private static final int CATEGORY_NAME_MAX_LENGTH = 20;
	
	/** カテゴリ名が空の場合のメッセージ */
	private static final String FILL_CATEGORY_NAME = "カテゴリ名を入力してください。";
	/** カテゴリ名の文字数が不正な場合のメッセージ */
	private static final String NOT_CORRECT_CATEGORY_NAME_LENGTH = "カテゴリ名は" + CATEGORY_NAME_MAX_LENGTH + "文字以内で入力してください。";
	
	
	/**
	 * カテゴリ名のバリデーションを行うメソッド
	 * @param 	categoryName カテゴリ名
	 * @return 	不正がある場合、エラーメッセージを格納したCategoryError <br>
	 * 			不正がない場合、null
	 */
	public static CategoryError validateCategoryName(String categoryName) {
		//結果格納用
		CategoryError categoryError = new CategoryError();
		
		//空でないか？
		if(categoryName.isEmpty()) {
			categoryError.setCategoryNameErrorMessage(FILL_CATEGORY_NAME);
		//不正な文字数ではないか？
		} else if (isNotCorrectLength(categoryName, CATEGORY_NAME_MIN_LENGTH, CATEGORY_NAME_MAX_LENGTH)) {
			categoryError.setCategoryNameErrorMessage(NOT_CORRECT_CATEGORY_NAME_LENGTH);
		}
		
		return categoryError;
	}
}
