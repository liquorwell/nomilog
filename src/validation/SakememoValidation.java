package validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 酒メモのバリデーション用
 */
public class SakememoValidation extends Validation {
	
	/** 酒メモ名の最小文字数 */
	private static final int SAKEMEMO_NAME_MIN_LENGTH = 1;
	/** 酒メモ名の最大文字数 */
	private static final int SAKEMEMO_NAME_MAX_LENGTH = 20;
	/** コメントの最小文字数 */
	private static final int SAKEMEMO_COMMENT_MIN_LENGTH = 1;
	/** コメントの最大文字数 */
	private static final int SAKEMEMO_COMMENT_MAX_LENGTH = 100;
	
	/** 酒メモ名が空の場合のメッセージ */
	private static final String FILL_SAKEMEMO_NAME = "酒メモ名を入力してください。";
	/** 酒メモ名の文字数が不正な場合のメッセージ */
	private static final String NOT_CORRECT_SAKEMEMO_NAME_LENGTH = "酒メモ名は" + SAKEMEMO_NAME_MAX_LENGTH + "文字以内で入力してください。";
	/** カテゴリが空の場合のメッセージ */
	private static final String CHOICE_CATEGORY = "カテゴリを選択してください。";
	/** コメントが空の場合のメッセージ */
	private static final String FILL_SAKEMEMO_COMMENT = "コメントを入力してください。";
	/** コメントの文字数が不正な場合のメッセージ */
	private static final String NOT_CORRECT_SAKEMEMO_COMMENT_LENGTH = "コメントは" + SAKEMEMO_COMMENT_MAX_LENGTH + "文字以内で入力してください。";
	/** 絞り込み条件が空の場合のメッセージ */
	private static final String FILL_FILTER_VALUE = "絞り込み条件を入力してください。";
	/** 登録日の新旧が逆の場合のメッセージ */
	private static final String ORDER_INS_DATE = "登録日は古い順に入力してください。";
	
	
	
	/**
	 * 酒メモ登録処理用バリデーション
	 * @param sakememoName 酒メモ名
	 * @param categoryId カテゴリID
	 * @param sakememoComment コメント
	 * @return	不正がある場合、エラーメッセージを格納したSakelogError <br>
	 * 			不正がない場合、null
	 */
	public static SakememoError validateInsertValue(String sakememoName, String categoryId, String sakememoComment) {
		SakememoError sakememoError = new SakememoError();
		sakememoError = checkSakememoName(sakememoError, sakememoName);
		sakememoError = checkCategoryId(sakememoError, categoryId);
		sakememoError = checkSakememoComment(sakememoError, sakememoComment);
		return sakememoError;
	}
	
	
	/**
	 * 酒メモ更新処理用バリデーション
	 * @param sakememoName 酒メモ名
	 * @param sakememoComment コメント
	 * @return	不正がある場合、エラーメッセージを格納したSakelogError <br>
	 * 			不正がない場合、null
	 */
	public static SakememoError validateUpdateValue(String sakememoName, String sakememoComment) {
		SakememoError sakememoError = new SakememoError();
		sakememoError = checkSakememoName(sakememoError, sakememoName);
		sakememoError = checkSakememoComment(sakememoError, sakememoComment);
		return sakememoError;
	}
	
	
	/**
	 * 酒メモ絞り込み処理用バリデーション(登録日以外)
	 * @param filterValue 絞り込み条件
	 * @return	不正がある場合、エラーメッセージを格納したSakelogError <br>
	 * 			不正がない場合、null
	 */
	public static SakememoError validateFilterValue(String filterValue) {
		SakememoError sakememoError = new SakememoError();
		sakememoError = checkFilterValue(sakememoError, filterValue);
		return sakememoError;
	}
	
	
	/**
	 * 酒メモ絞り込み処理用バリデーション(登録日)
	 * @param strInsDateOld 登録日(古)
	 * @param strInsDateNew 登録日(新)
	 * @return	不正がある場合、エラーメッセージを格納したSakelogError <br>
	 * 			不正がない場合、null
	 */
	public static SakememoError validateFilterInsDate(String strInsDateOld, String strInsDateNew) {
		SakememoError sakememoError = new SakememoError();
		sakememoError = checkInsDate(sakememoError, strInsDateOld, strInsDateNew);
		return sakememoError;
	}
	
	
	/**
	 * 酒メモ名チェック用メソッド <br>
	 * ・空文字でないか <br>
	 * ・文字数が最小文字数と最大文字数の間に収まっているか <br>
	 * をチェック
	 * @param sakememoError
	 * @param sakememoName
	 * @return SakememoError <br>
	 * 			不正がある場合 sakememoNameErrorMessageフィールドに値をセットして返す <br>
	 * 			不正がない場合 引数として受け取ったものをそのまま返す
	 */
	private static SakememoError checkSakememoName(SakememoError sakememoError, String sakememoName) {
		//入力されているか？
		if (sakememoName.isEmpty()) {
			sakememoError.setSakememoNameErrorMessage(FILL_SAKEMEMO_NAME);
		//不正な文字数ではないか？
		} else if (isNotCorrectLength(sakememoName, SAKEMEMO_NAME_MIN_LENGTH, SAKEMEMO_NAME_MAX_LENGTH)) {
			sakememoError.setSakememoNameErrorMessage(NOT_CORRECT_SAKEMEMO_NAME_LENGTH);
		}
		return sakememoError;
	}
	
	/**
	 * カテゴリIDチェック用メソッド <br>
	 * nullでないかをチェック
	 * @param sakememoError
	 * @param categoryId カテゴリID
	 * @return SakememoError <br>
	 * 			不正がある場合 categoryErrorMessageフィールドに値をセットして返す <br>
	 * 			不正がない場合 引数として受け取ったものをそのまま返す
	 */
	private static SakememoError checkCategoryId(SakememoError sakememoError, String categoryId) {
		//nullでないか？
		if (categoryId == null) {
			sakememoError.setCategoryErrorMessage(CHOICE_CATEGORY);
		}
		return sakememoError;
	}
	
	/**
	 * コメントチェック用メソッド <br>
	 * ・空文字でないか <br>
	 * ・文字数が最小文字数と最大文字数の間に収まっているか <br>
	 * をチェック
	 * @param sakememoError
	 * @param sakememoComment コメント
	 * @return SakememoError <br>
	 * 			不正がある場合 sakememoCommentErrorMessageフィールドに値をセットして返す <br>
	 * 			不正がない場合 引数として受け取ったものをそのまま返す
	 */
	private static SakememoError checkSakememoComment(SakememoError sakememoError, String sakememoComment) {
		//入力されているか？
		if (sakememoComment.isEmpty()) {
			sakememoError.setSakememoCommentErrorMessage(FILL_SAKEMEMO_COMMENT);
		//不正な文字数ではないか？
		} else if (isNotCorrectLength(sakememoComment, SAKEMEMO_COMMENT_MIN_LENGTH, SAKEMEMO_COMMENT_MAX_LENGTH)) {
			sakememoError.setSakememoCommentErrorMessage(NOT_CORRECT_SAKEMEMO_COMMENT_LENGTH);
		}
		return sakememoError;
	}
	
	/**
	 * 絞り込み条件(登録日以外)チェック用メソッド <br>
	 * nullでないかをチェック
	 * @param sakememoError
	 * @param filterValue 絞り込み条件
	 * @return SakememoError <br>
	 * 			不正がある場合 filterErrorMessageフィールドに値をセットして返す <br>
	 * 			不正がない場合 引数として受け取ったものをそのまま返す
	 */
	private static SakememoError checkFilterValue(SakememoError sakememoError, String filterValue) {
		//nullでないか？
		if (filterValue == null) {
			sakememoError.setFilterErrorMessage(FILL_FILTER_VALUE);
		}
		return sakememoError;
	}
	
	/**
	 * 絞り込み条件(登録日)チェック用メソッド <br>
	 * ・新旧両方の値が空でないか <br>
	 * ・両方が空ではない場合、正しい順番になっているか <br>
	 * をチェック
	 * @param sakememoError
	 * @param strInsDateOld 登録日(古) 
	 * @param strInsDateNew 登録日(新)
	 * @return SakelogError <br>
	 * 			不正がある場合 filterErrorMessageフィールドに値をセットして返す <br>
	 * 			不正がない場合 引数として受け取ったものをそのまま返す
	 */
	private static SakememoError checkInsDate(SakememoError sakememoError, String strInsDateOld, String strInsDateNew) {
		//いずれかが入力されているか？
		if (strInsDateOld.isEmpty() && strInsDateNew.isEmpty()) {
			sakememoError.setFilterErrorMessage(FILL_FILTER_VALUE);
		//どちらも入力されている場合(一方が空の場合はデフォルト値を埋めるため、この処理は不要)
		} else if (!strInsDateOld.isEmpty() && !strInsDateNew.isEmpty()){
			LocalDate insDateOld = LocalDate.parse(strInsDateOld, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			LocalDate insDateNew = LocalDate.parse(strInsDateNew, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			//登録日の新旧が逆でないか？
			if (insDateOld.isAfter(insDateNew)) {
				sakememoError.setFilterErrorMessage(ORDER_INS_DATE);
			}
		}
		return sakememoError;
	}
}
