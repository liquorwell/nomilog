package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.User;
import dao.UserDao;

/**
 * ユーザーのバリデーション用
 */
public class UserValidation extends Validation {

	/** ユーザー名の最小文字数 */
	private static final int USER_NAME_MIN_LENGTH = 1;
	/** ユーザー名の最大文字数 */
	private static final int USER_NAME_MAX_LENGTH = 20;
	/** パスワードの最小文字数 */
	private static final int USER_PASS_MIN_LENGTH = 8;
	/** パスワードの最大文字数 */
	private static final int USER_PASS_MAX_LENGTH = 20;
	
	/** ユーザー名が空の場合のエラーメッセージ */
	private static final String FILL_USER_NAME = "ユーザー名を入力してください。";
	/** ユーザー名の文字数が不正な場合のエラーメッセージ */
	private static final String NOT_CORRECT_USER_NAME_LENGTH = "ユーザー名は" + USER_NAME_MAX_LENGTH + "文字以内で入力してください。";
	/** ユーザー名が既に使用されている場合のエラーメッセージ */
	private static final String NOT_UNIQUE_USER_NAME = "そのユーザー名は既に使用されています";
	
	/** パスワードが空の場合のエラーメッセージ */
	private static final String FILL_USER_PASS = "パスワードを入力してください。";
	/** パスワードの文字数が不正な場合のエラーメッセージ */
	private static final String NOT_CORRECT_USER_PASS_LENGTH = 
			"パスワードは" + USER_PASS_MIN_LENGTH + "文字以上" + USER_PASS_MAX_LENGTH + "文字以内で入力してください。";
	/** パスワードがユーザーテーブルの値と一致しない場合のエラーメッセージ */
	private static final String NOT_CORRECT_USER_PASS = "パスワードが違います。";
	/** 確認用パスワードが一致しない場合のエラーメッセージ */
	private static final String NOT_CORRECT_CHECK_PASS = "パスワードと確認用パスワードが異なります。"; 
	
	/** パスワードの正規表現パターン(半角英数記号) */
	private static final String USER_PASS_PATTERN = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]+$";
	/** パスワードが正規表現パターンと一致しない場合のエラーメッセージ */
	private static final String NOT_MATCH_REGEX_PASS = "パスワードは半角英数記号で入力してください。";
	
	/** ユーザーテーブルの値と一致しない場合のエラーメッセージ */
	private static final String USER_DONT_EXIST = "ユーザーが存在しません。";
	
	
	
	/**
	 * ログイン処理用バリデーション
	 * @param userName ユーザー名
	 * @param userPass パスワード
	 * @return 	不正がある場合、エラーメッセージを格納したuserError <br>
	 * 			不正がない場合、null
	 */
	public static UserError validateLoginValue(String userName, String userPass) {
		UserError userError = new UserError();
		//ユーザー名が入力されているか？
		if (userName.isEmpty()) {
			userError.setUserNameErrorMessage(FILL_USER_NAME);
		}
		//パスワードが入力されているか？
		if (userPass.isEmpty()) {
			userError.setUserPassErrorMessage(FILL_USER_PASS);
		}
		//ユーザーが存在しているか？
		//※ユーザー名かパスワードにエラーが発生した場合、この処理は行う必要がない
		if (isNotUserExist(userName, userPass) && userError.isNameAndPassErrorNull()) {
			userError.setUserExistErrorMessage(USER_DONT_EXIST);
		}
		
		return userError;
	}
	
	
	/**
	 * サインアップ処理用バリデーション
	 * @param userName ユーザー名
	 * @param userPass パスワード
	 * @param checkPass 確認用パスワード
	 * @return 	不正がある場合、エラーメッセージを格納したuserError <br>
	 * 			不正がない場合、null
	 */
	public static UserError validateSignUpValue(String userName, String userPass, String checkPass) {
		UserError userError = new UserError();
		//ユーザー名が入力されているか？
		if (userName.isEmpty()) {
			userError.setUserNameErrorMessage(FILL_USER_NAME);
		//不正な文字数ではないか？
		} else if (isNotCorrectLength(userName, USER_NAME_MIN_LENGTH, USER_NAME_MAX_LENGTH)) {
			userError.setUserNameErrorMessage(NOT_CORRECT_USER_NAME_LENGTH);
		//一意か？
		} else if (isNotUniqueUserName(userName)) {
			userError.setUserNameErrorMessage(NOT_UNIQUE_USER_NAME);
		}
		
		//パスワードが入力されているか？
		if (userPass.isEmpty()) {
			userError.setUserPassErrorMessage(FILL_USER_PASS);
		//不正な文字数ではないか？
		} else if (isNotCorrectLength(userPass, USER_PASS_MIN_LENGTH, USER_PASS_MAX_LENGTH)) {
			userError.setUserPassErrorMessage(NOT_CORRECT_USER_PASS_LENGTH);
		//正規表現パターンに一致しているか？
		} else if (isNotMatchRegex(userPass, USER_PASS_PATTERN)) {
			userError.setUserPassErrorMessage(NOT_MATCH_REGEX_PASS);
		//確認用パスワードと一致しているか？
		} else if (isNotCorrectCheckPass(userPass, checkPass)) {
			userError.setUserPassErrorMessage(NOT_CORRECT_CHECK_PASS);
		} 
		
		return userError;
	}
	
	/**
	 * ユーザー名更新処理用バリデーション
	 * @param userName ユーザー名
	 * @param userId ユーザーID
	 * @return	不正がある場合、エラーメッセージを格納したuserError <br>
	 * 			不正がない場合、null
	 */
	public static UserError validateUpdateUserNameValue(String userName, int userId) {
		UserError userError = new UserError();
		//ユーザー名が入力されているか？
		if (userName.isEmpty()) {
			userError.setUserNameErrorMessage(FILL_USER_NAME);
		//不正な文字数ではないか？
		} else if (isNotCorrectLength(userName, USER_NAME_MIN_LENGTH, USER_NAME_MAX_LENGTH)) {
			userError.setUserNameErrorMessage(NOT_CORRECT_USER_NAME_LENGTH);
		//一意か？
		} else if (isNotUniqueUserName(userName, userId)) {
			userError.setUserNameErrorMessage(NOT_UNIQUE_USER_NAME);
		}
		
		return userError;
	}
	
	/**
	 * パスワード更新機能用バリデーション
	 * @param currPass 現在のパスワード
	 * @param newPass 新しいパスワード
	 * @param checkPass 確認用パスワード
	 * @param userId ユーザーID
	 * @return 	不正がある場合、エラーメッセージを格納したuserError <br>
	 * 			不正がない場合、null
	 */
	public static UserError validateUpdateUserPassValue(String currPass, String newPass, String checkPass, int userId) {
		//ユーザーIDからテーブルに登録されたパスワードを入手
		User user = UserDao.findByUserId(userId);
		String userPass = user.getUserPass();
		
		UserError userError = new UserError();
		//現在のパスワードが入力されているか？
		if (currPass.isEmpty()) {
			userError.setUserPassErrorMessage(FILL_USER_PASS);
		//テーブルに登録されている値と一致するか？
		} else if (isNotCorrectCheckPass(currPass, userPass)) {
			userError.setUserPassErrorMessage(NOT_CORRECT_USER_PASS);
		}
		
		//新しいパスワードが入力されているか？
		if (newPass.isEmpty()) {
			userError.setUserNewPassErrorMessage(FILL_USER_PASS);
		//不正な文字数ではないか？
		} else if (isNotCorrectLength(newPass, USER_PASS_MIN_LENGTH, USER_PASS_MAX_LENGTH)) {
			userError.setUserNewPassErrorMessage(NOT_CORRECT_USER_PASS_LENGTH);
		//正規表現パターンに一致するか？
		} else if (isNotMatchRegex(newPass, USER_PASS_PATTERN)) {
			userError.setUserPassErrorMessage(NOT_MATCH_REGEX_PASS);
		//確認用パスワードと一致するか？
		} else if (isNotCorrectCheckPass(newPass, checkPass)) {
			userError.setUserNewPassErrorMessage(NOT_CORRECT_USER_PASS);
		}
		
		return userError;
	}
	
	/**
	 * ユーザー削除処理用バリデーション
	 * @param userPass パスワード
	 * @param userId ユーザーID
	 * @return 	不正がある場合、エラーメッセージを格納したuserError <br>
	 * 			不正がない場合、null
	 */
	public static UserError validateDeleteUserValue(String userPass, int userId) {
		//ユーザーIDからテーブルに登録されたパスワードを入手
		User user = UserDao.findByUserId(userId);
		String checkPass = user.getUserPass();
		
		UserError userError = new UserError();
		if (isNotCorrectCheckPass(userPass, checkPass)) {
			userError.setUserPassErrorMessage(NOT_CORRECT_USER_PASS);
		}
		
		return userError;
	}
	
	
	/**
	 * ユーザー名が存在しているかどうかを判定するメソッド(ユーザー登録処理用)
	 * @param userName ユーザー名
	 * @return 	userNameがユーザーテーブルから見つかった場合 true <br>
	 * 			見つからなかった場合 false
	 */
	private static boolean isNotUniqueUserName(String userName) {
		User user = UserDao.findByUserName(userName);
		return user != null;
	}
	/**
	 * ユーザー名が存在しているかどうかを判定するメソッド(ユーザー名更新処理用) <br>
	 * 自身のユーザー名は当然ユーザーテーブルに登録されているので、その場合は除く <br>
	 * ※現在のパスワードと同じパスワードを入力したとき、エラーとせずに変更なしで処理を進めるため
	 * @param userName ユーザー名
	 * @param userId ユーザーID
	 * @return 	userNameが別のユーザーのユーザー名と同じ場合 true <br>
	 * 			userNameがユーザーテーブルから見つからない場合 false <br>
	 * 			userNameがuserIdのユーザー名と一致する場合 false
	 */
	private static boolean isNotUniqueUserName(String userName, int userId) {
		User user = UserDao.findByUserName(userName);
		if (user != null) {
			if (user.getUserId() == userId) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * ユーザーが存在しているかどうか判定するメソッド
	 * @param userName ユーザー名
	 * @param userPass パスワード
	 * @return 	userNameとuserPassを持つユーザーが見つからなかった場合 true <br>
	 * 			見つかった場合 false
	 */
	private static boolean isNotUserExist(String userName, String userPass) {
		User user = UserDao.findByNamePass(userName, userPass);
		return user == null;
	}
	
	/**
	 * 2つのパスワードが一致しているかどうか判定するメソッド
	 * @param pass 対象
	 * @param checkPass 比較対象
	 * @return	passとcheckPassの文字列が一致していない場合 true <br>
	 * 			一致した場合 false
	 */
	private static boolean isNotCorrectCheckPass(String pass, String checkPass) {
		return !pass.equals(checkPass);
	}
	
	/**
	 * 正規表現パターンに一致しているかどうかを判定するメソッド
	 * @param value 対象
	 * @param strPattern 正規表現パターン
	 * @return 	valueがstrPatternの示す正規表現パターンに一致していない場合 true <br>
	 * 			一致した場合 false
	 */
	private static boolean isNotMatchRegex(String value, String strPattern) {
		Pattern pattern = Pattern.compile(strPattern); 
        Matcher matcher = pattern.matcher(value);
        return !matcher.find(); 
	}
	
}
