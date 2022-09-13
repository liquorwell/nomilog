package validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SakememoValidation extends Validation {
	
	private static final int SAKEMEMO_NAME_MIN_LENGTH = 1;
	private static final int SAKEMEMO_NAME_MAX_LENGTH = 20;
	private static final int SAKEMEMO_COMMENT_MIN_LENGTH = 1;
	private static final int SAKEMEMO_COMMENT_MAX_LENGTH = 100;
	
	private static String FILL_SAKEMEMO_NAME = "酒メモ名を入力してください。";
	private static final String NOT_CORRECT_SAKEMEMO_NAME_LENGTH = 
			"酒メモ名は" + SAKEMEMO_NAME_MIN_LENGTH + "文字以上" + SAKEMEMO_NAME_MAX_LENGTH + "文字以内で入力してください。";
	private static String CHOICE_CATEGORY = "カテゴリを選択してください。";
	private static String FILL_SAKEMEMO_COMMENT = "コメントを入力してください。";
	private static final String NOT_CORRECT_SAKEMEMO_COMMENT_LENGTH = 
			"コメントは" + SAKEMEMO_COMMENT_MIN_LENGTH + "文字以上" + SAKEMEMO_COMMENT_MAX_LENGTH + "文字以内で入力してください。";
	private static String FILL_FILTER_VALUE = "絞り込み条件を入力してください。";
	private static String ORDER_INS_DATE = "登録日は古い順に入力してください。";

	
	public static SakememoError insertValidation(String sakememoName, String categoryId, String sakememoComment) {
		SakememoError sakememoError = new SakememoError();
		
		if (isEmpty(sakememoName)) {
			sakememoError.setSakememoNameErrorMessage(FILL_SAKEMEMO_NAME);
		} else if (isNotCorrectLength(sakememoName, SAKEMEMO_NAME_MIN_LENGTH, SAKEMEMO_NAME_MAX_LENGTH)) {
			sakememoError.setSakememoNameErrorMessage(NOT_CORRECT_SAKEMEMO_NAME_LENGTH);
		}
		
		if (categoryId == null) {
			sakememoError.setCategoryErrorMessage(CHOICE_CATEGORY);
		}
		
		if (isEmpty(sakememoComment)) {
			sakememoError.setSakememoCommentErrorMessage(FILL_SAKEMEMO_COMMENT);
		} else if (isNotCorrectLength(sakememoComment, SAKEMEMO_COMMENT_MIN_LENGTH, SAKEMEMO_COMMENT_MAX_LENGTH)) {
			sakememoError.setSakememoCommentErrorMessage(NOT_CORRECT_SAKEMEMO_COMMENT_LENGTH);
		}
		
		if (sakememoError.isAllFieldNull()) {
			sakememoError = null;
		}
		
		return sakememoError;
	}
	
	public static SakememoError updateValidation(String sakememoName, String sakememoComment) {
		SakememoError sakememoError = new SakememoError();
		
		if (isEmpty(sakememoName)) {
			sakememoError.setSakememoNameErrorMessage(FILL_SAKEMEMO_NAME);
		} else if (isNotCorrectLength(sakememoName, SAKEMEMO_NAME_MIN_LENGTH, SAKEMEMO_NAME_MAX_LENGTH)) {
			sakememoError.setSakememoNameErrorMessage(NOT_CORRECT_SAKEMEMO_NAME_LENGTH);
		}
		
		if (isEmpty(sakememoComment)) {
			sakememoError.setSakememoCommentErrorMessage(FILL_SAKEMEMO_COMMENT);
		} else if (isNotCorrectLength(sakememoComment, SAKEMEMO_COMMENT_MIN_LENGTH, SAKEMEMO_COMMENT_MAX_LENGTH)) {
			sakememoError.setSakememoCommentErrorMessage(NOT_CORRECT_SAKEMEMO_COMMENT_LENGTH);
		}
		
		if (sakememoError.isAllFieldNull()) {
			sakememoError = null;
		}
		
		return sakememoError;
	}
	
	
	public static SakememoError filterValueValidation(String value) {
		SakememoError sakememoError = new SakememoError();
		if (value == null) {
			sakememoError.setFilterErrorMessage(FILL_FILTER_VALUE);
		}
		return sakememoError;
	}
	
	public static SakememoError filterInsDateValidation(String strInsDateOld, String strInsDateNew) {
		SakememoError sakememoError = new SakememoError();
		if (isEmpty(strInsDateOld) && isEmpty(strInsDateNew)) {
			sakememoError.setFilterErrorMessage(FILL_FILTER_VALUE);
		} else if (!isEmpty(strInsDateOld) && !isEmpty(strInsDateNew)){
			LocalDate insDateOld = LocalDate.parse(strInsDateOld, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			LocalDate insDateNew = LocalDate.parse(strInsDateNew, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			if (insDateOld.isAfter(insDateNew)) {
				sakememoError.setFilterErrorMessage(ORDER_INS_DATE);
			}
		}
		
		return sakememoError;
	}
	
}
