package validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SakelogValidation extends Validation {
	
	private static String FILL_SAKELOG_NAME = "酒ログ名を入力してください。";
	private static String CHOICE_CATEGORY = "カテゴリを選択してください。";
	private static String CHOICE_RATING = "評価を選択してください。";
	private static String FILL_FILTER_VALUE = "絞り込み条件を入力してください。";
	private static String ORDER_INS_DATE = "登録日は古い順に入力してください。";

	
	
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
