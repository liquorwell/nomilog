package validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SakelogValidation extends Validation {
	
	private static final int SAKELOG_NAME_MIN_LENGTH = 1;
	private static final int SAKELOG_NAME_MAX_LENGTH = 20;
	private static final int SAKELOG_COMMENT_MIN_LENGTH = 1;
	private static final int SAKELOG_COMMENT_MAX_LENGTH = 100;
	
	private static String FILL_SAKELOG_NAME = "酒ログ名を入力してください。";
	private static final String NOT_CORRECT_SAKELOG_NAME_LENGTH = 
			"酒ログ名は" + SAKELOG_NAME_MIN_LENGTH + "文字以上" + SAKELOG_NAME_MAX_LENGTH + "文字以内で入力してください。";
	private static String CHOICE_CATEGORY = "カテゴリを選択してください。";
	private static String CHOICE_RATING = "評価を選択してください。";
	private static String FILL_SAKELOG_COMMENT = "コメントを入力してください。";
	private static final String NOT_CORRECT_SAKELOG_COMMENT_LENGTH = 
			"コメントは" + SAKELOG_COMMENT_MIN_LENGTH + "文字以上" + SAKELOG_COMMENT_MAX_LENGTH + "文字以内で入力してください。";
	private static String FILL_FILTER_VALUE = "絞り込み条件を入力してください。";
	private static String ORDER_INS_DATE = "登録日は古い順に入力してください。";

	
	public static SakelogError insertValidation(String sakelogName, String categoryId, String rating, String sakelogComment) {
		SakelogError sakelogError = new SakelogError();
		
		if (isEmpty(sakelogName)) {
			sakelogError.setSakelogNameErrorMessage(FILL_SAKELOG_NAME);
		} else if (isNotCorrectLength(sakelogName, SAKELOG_NAME_MIN_LENGTH, SAKELOG_NAME_MAX_LENGTH)) {
			sakelogError.setSakelogNameErrorMessage(NOT_CORRECT_SAKELOG_NAME_LENGTH);
		}
		
		if (categoryId == null) {
			sakelogError.setCategoryErrorMessage(CHOICE_CATEGORY);
		}
		
		if (rating == null) {
			sakelogError.setRatingErrorMessage(CHOICE_RATING);
		}
		
		if (isEmpty(sakelogComment)) {
			sakelogError.setSakelogCommentErrorMessage(FILL_SAKELOG_COMMENT);
		} else if (isNotCorrectLength(sakelogComment, SAKELOG_COMMENT_MIN_LENGTH, SAKELOG_COMMENT_MAX_LENGTH)) {
			sakelogError.setSakelogCommentErrorMessage(NOT_CORRECT_SAKELOG_COMMENT_LENGTH);
		}
		
		if (sakelogError.isAllFieldNull()) {
			sakelogError = null;
		}
		
		return sakelogError;
	}
	
	public static SakelogError updateValidation(String sakelogName, String sakelogComment) {
		SakelogError sakelogError = new SakelogError();
		
		if (isEmpty(sakelogName)) {
			sakelogError.setSakelogNameErrorMessage(FILL_SAKELOG_NAME);
		} else if (isNotCorrectLength(sakelogName, SAKELOG_NAME_MIN_LENGTH, SAKELOG_NAME_MAX_LENGTH)) {
			sakelogError.setSakelogNameErrorMessage(NOT_CORRECT_SAKELOG_NAME_LENGTH);
		}
		
		if (isEmpty(sakelogComment)) {
			sakelogError.setSakelogCommentErrorMessage(FILL_SAKELOG_COMMENT);
		} else if (isNotCorrectLength(sakelogComment, SAKELOG_COMMENT_MIN_LENGTH, SAKELOG_COMMENT_MAX_LENGTH)) {
			sakelogError.setSakelogCommentErrorMessage(NOT_CORRECT_SAKELOG_COMMENT_LENGTH);
		}
		
		if (sakelogError.isAllFieldNull()) {
			sakelogError = null;
		}
		
		return sakelogError;
	}
	
	
	public static SakelogError filterValueValidation(String value) {
		SakelogError sakelogError = new SakelogError();
		if (value == null) {
			sakelogError.setFilterErrorMessage(FILL_FILTER_VALUE);
		}
		return sakelogError;
	}
	
	public static SakelogError filterInsDateValidation(String strInsDateOld, String strInsDateNew) {
		SakelogError sakelogError = new SakelogError();
		if (isEmpty(strInsDateOld) && isEmpty(strInsDateNew)) {
			sakelogError.setFilterErrorMessage(FILL_FILTER_VALUE);
		} else if (!isEmpty(strInsDateOld) && !isEmpty(strInsDateNew)){
			LocalDate insDateOld = LocalDate.parse(strInsDateOld, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			LocalDate insDateNew = LocalDate.parse(strInsDateNew, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			if (insDateOld.isAfter(insDateNew)) {
				sakelogError.setFilterErrorMessage(ORDER_INS_DATE);
			}
		}
		
		return sakelogError;
	}
	
}
