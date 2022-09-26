package validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 酒ログのバリデーション用
 */
public class SakelogValidation extends Validation {
	
	
	/** 酒ログ名の最小文字数 */
	private static final int SAKELOG_NAME_MIN_LENGTH = 1;
	/** 酒ログ名の最大文字数 */
	private static final int SAKELOG_NAME_MAX_LENGTH = 20;
	/** コメントの最小文字数 */
	private static final int SAKELOG_COMMENT_MIN_LENGTH = 1;
	/** コメントの最大文字数 */
	private static final int SAKELOG_COMMENT_MAX_LENGTH = 100;
	
	/** 酒ログ名が空の場合のメッセージ */
	private static final String FILL_SAKELOG_NAME = "酒ログ名を入力してください。";
	/** 酒ログ名の文字数が不正な場合のメッセージ */
	private static final String NOT_CORRECT_SAKELOG_NAME_LENGTH = "酒ログ名は" + SAKELOG_NAME_MAX_LENGTH + "文字以内で入力してください。";
	/** カテゴリが空の場合のメッセージ */
	private static final String CHOICE_CATEGORY = "カテゴリを選択してください。";
	/** 評価が空の場合のメッセージ */
	private static final String CHOICE_RATING = "評価を選択してください。";
	/** コメントが空の場合のメッセージ */
	private static final String FILL_SAKELOG_COMMENT = "コメントを入力してください。";
	/** コメントの文字数が不正な場合のメッセージ */
	private static final String NOT_CORRECT_SAKELOG_COMMENT_LENGTH = "コメントは" + SAKELOG_COMMENT_MAX_LENGTH + "文字以内で入力してください。";
	/** 絞り込み条件が空の場合のメッセージ */
	private static final String FILL_FILTER_VALUE = "絞り込み条件を入力してください。";
	/** 登録日の新旧が逆の場合のメッセージ */
	private static final String ORDER_INS_DATE = "登録日は古い順に入力してください。";
	
	
	
	/**
	 * 酒ログ登録処理用バリデーション
	 * @param 	sakelogName 酒ログ名
	 * @param 	categoryId カテゴリID
	 * @param	rating 評価
	 * @param 	sakelogComment コメント
	 * @return 	不正がある場合、エラーメッセージを格納したSakelogError <br>
	 * 			不正がない場合、null
	 */
	public static SakelogError validateInsertValue(String sakelogName, String categoryId, String rating, String sakelogComment) {
		SakelogError sakelogError = new SakelogError();
		sakelogError = checkSakelogName(sakelogError, sakelogName);
		sakelogError = checkCategoryId(sakelogError, categoryId);
		sakelogError = checkRating(sakelogError, rating);
		sakelogError = checkSakelogComment(sakelogError, sakelogComment);
		return sakelogError;
	}
	
	/**
	 * 酒ログ更新処理用バリデーション
	 * @param sakelogName 酒ログ名
	 * @param sakelogComment コメント
	 * @return 	不正がある場合、エラーメッセージを格納したSakelogError <br>
	 * 			不正がない場合、null
	 */
	public static SakelogError validateUpdateValue(String sakelogName, String sakelogComment) {
		SakelogError sakelogError = new SakelogError();
		sakelogError = checkSakelogName(sakelogError, sakelogName);
		sakelogError = checkSakelogComment(sakelogError, sakelogComment);
		return sakelogError;
	}
	
	/**
	 * 酒ログ絞り込み処理用バリデーション(登録日以外)
	 * @param filterValue 絞り込み条件
	 * @return 	不正がある場合、エラーメッセージを格納したSakelogError <br>
	 * 			不正がない場合、null
	 */
	public static SakelogError validateFilterValue(String filterValue) {
		SakelogError sakelogError = new SakelogError();
		sakelogError = checkFilterValue(sakelogError, filterValue);
		return sakelogError;
	}
	
	/**
	 * 酒ログ絞り込み処理用バリデーション(登録日)
	 * @param strInsDateOld 登録日(古)
	 * @param strInsDateNew 登録日(新)
	 * @return 	不正がある場合、エラーメッセージを格納したSakelogError <br>
	 * 			不正がない場合、null
	 */
	public static SakelogError validateFilterInsDate(String strInsDateOld, String strInsDateNew) {
		SakelogError sakelogError = new SakelogError();
		sakelogError = checkInsDate(sakelogError, strInsDateOld, strInsDateNew);
		return sakelogError;
	}
	
	
	
	/**
	 * 酒ログ名チェック用メソッド <br>
	 * ・空文字でないか <br>
	 * ・文字数が最小文字数と最大文字数の間に収まっているか <br>
	 * をチェック
	 * @param sakelogError
	 * @param sakelogName 酒ログ名
	 * @return SakelogError <br>
	 * 			不正がある場合 sakelogNameErrorMessageフィールドに値をセットして返す <br>
	 * 			不正がない場合 引数として受け取ったものをそのまま返す
	 */
	private static SakelogError checkSakelogName(SakelogError sakelogError, String sakelogName) {
		//入力されているか？
		if (sakelogName.isEmpty()) {
			sakelogError.setSakelogNameErrorMessage(FILL_SAKELOG_NAME);
		//不正な文字数ではないか？
		} else if (isNotCorrectLength(sakelogName, SAKELOG_NAME_MIN_LENGTH, SAKELOG_NAME_MAX_LENGTH)) {
			sakelogError.setSakelogNameErrorMessage(NOT_CORRECT_SAKELOG_NAME_LENGTH);
		}
		return sakelogError;
	}
	
	/**
	 * カテゴリIDチェック用メソッド <br>
	 * nullでないかをチェック
	 * @param sakelogError
	 * @param categoryId カテゴリID
	 * @return SakelogError <br>
	 * 			不正がある場合 categoryErrorMessageフィールドに値をセットして返す <br>
	 * 			不正がない場合 引数として受け取ったものをそのまま返す
	 */
	private static SakelogError checkCategoryId(SakelogError sakelogError, String categoryId) {
		//nullでないか？
		if (categoryId == null) {
			sakelogError.setCategoryErrorMessage(CHOICE_CATEGORY);
		}
		return sakelogError;
	}
	
	/**
	 * 評価チェック用メソッド <br>
	 * nullでないかをチェック
	 * @param sakelogError
	 * @param rating 評価
	 * @return SakelogError <br>
	 * 			不正がある場合 ratingErrorMessageフィールドに値をセットして返す <br>
	 * 			不正がない場合 引数として受け取ったものをそのまま返す
	 */
	private static SakelogError checkRating(SakelogError sakelogError, String rating) {
		//nullでないか？
		if (rating == null) {
			sakelogError.setRatingErrorMessage(CHOICE_RATING);
		}
		return sakelogError;
	}
	
	/**
	 * コメントチェック用メソッド <br>
	 * ・空文字でないか <br>
	 * ・文字数が最小文字数と最大文字数の間に収まっているか <br>
	 * をチェック
	 * @param sakelogError
	 * @param sakelogComment
	 * @return SakelogError <br>
	 * 			不正がある場合 sakelogCommentErrorMessageフィールドに値をセットして返す <br>
	 * 			不正がない場合 引数として受け取ったものをそのまま返す
	 */
	private static SakelogError checkSakelogComment(SakelogError sakelogError, String sakelogComment) {
		//入力されているか？
		if (sakelogComment.isEmpty()) {
			sakelogError.setSakelogCommentErrorMessage(FILL_SAKELOG_COMMENT);
		//不正な文字数ではないか？
		} else if (isNotCorrectLength(sakelogComment, SAKELOG_COMMENT_MIN_LENGTH, SAKELOG_COMMENT_MAX_LENGTH)) {
			sakelogError.setSakelogCommentErrorMessage(NOT_CORRECT_SAKELOG_COMMENT_LENGTH);
		}
		return sakelogError;
	}
	
	/**
	 * 絞り込み条件(登録日以外)チェック用メソッド <br>
	 * 渡された値がnullでないかをチェック
	 * @param sakelogError
	 * @param filterValue 絞り込み条件
	 * @return SakelogError <br>
	 * 			不正がある場合 filterErrorMessageフィールドに値をセットして返す <br>
	 * 			不正がない場合 引数として受け取ったものをそのまま返す
	 */
	private static SakelogError checkFilterValue(SakelogError sakelogError, String filterValue) {
		//nullでないか？
		if (filterValue == null) {
			sakelogError.setFilterErrorMessage(FILL_FILTER_VALUE);
		}
		return sakelogError;
	}
	
	/**
	 * 絞り込み条件(登録日)チェック用メソッド <br>
	 * ・新旧両方の値が空でないか <br>
	 * ・両方が空ではない場合、正しい順番になっているか <br>
	 * をチェック
	 * @param sakelogError
	 * @param strInsDateOld 登録日(旧)
	 * @param strInsDateNew 登録日(新)
	 * @return 	SakelogError <br>
	 * 			不正がある場合 filterErrorMessageフィールドに値をセットして返す <br>
	 * 			不正がない場合 引数として受け取ったものをそのまま返す
	 */
	private static SakelogError checkInsDate(SakelogError sakelogError, String strInsDateOld, String strInsDateNew) {
		//いずれかが入力されているか？
		if (strInsDateOld.isEmpty() && strInsDateNew.isEmpty()) {
			sakelogError.setFilterErrorMessage(FILL_FILTER_VALUE);
		//どちらも入力されている場合(一方が空の場合はデフォルト値を埋めるため、この処理は不要)
		} else if (!strInsDateOld.isEmpty() && !strInsDateNew.isEmpty()){
			LocalDate insDateOld = LocalDate.parse(strInsDateOld, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			LocalDate insDateNew = LocalDate.parse(strInsDateNew, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			//登録日の新旧が逆でないか？
			if (insDateOld.isAfter(insDateNew)) {
				sakelogError.setFilterErrorMessage(ORDER_INS_DATE);
			}
		}
		return sakelogError;
	}
	
}
