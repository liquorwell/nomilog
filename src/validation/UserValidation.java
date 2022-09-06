package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.User;
import dao.UserDao;

public class UserValidation {

	private static final int USER_NAME_MIN_LENGTH = 1;
	private static final int USER_NAME_MAX_LENGTH = 20;
	private static final int USER_PASS_MIN_LENGTH = 8;
	private static final int USER_PASS_MAX_LENGTH = 20;

	private static final String FILL_USER_NAME = "ユーザー名を入力してください。";
	private static final String NOT_CORRECT_USER_NAME_LENGTH = 
			"ユーザー名は" + USER_NAME_MIN_LENGTH + "文字以上" + USER_NAME_MAX_LENGTH + "文字以内で入力してください。";
	private static final String NOT_UNIQUE_USER_NAME = "そのユーザー名はすでに既に使用されています";
	
	private static final String FILL_USER_PASS = "パスワードを入力してください。";
	private static final String NOT_CORRECT_USER_PASS_LENGTH = 
			"パスワードは" + USER_PASS_MIN_LENGTH + "文字以上" + USER_PASS_MAX_LENGTH + "文字以内で入力してください。";
	private static final String NOT_CORRECT_USER_PASS = "パスワードが違います。";
	private static final String NOT_CORRECT_CHECK_PASS = "パスワードと確認用パスワードが異なります。"; 
	
	private static final String USER_PASS_PATTERN = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]+$";
	private static final String NOT_MATCH_REGEX_PASS = "パスワードは半角英数記号で入力してください。";
	
	private static final String USER_DONT_EXIST = "ユーザーが存在しません。";
	
	
	
	public static UserError loginValidation(String userName, String userPass) {
		UserError userError = new UserError();
		if (isEmpty(userName)) {
			userError.setUserNameErrorMessage(FILL_USER_NAME);
		}
		if (isEmpty(userPass)) {
			userError.setUserPassErrorMessage(FILL_USER_PASS);
		}
		if (isNotUserExist(userName, userPass) && userError.isNameAndPassErrorNull()) {
			userError.setUserExistErrorMessage(USER_DONT_EXIST);
		}
		
		if (userError.isAllFieldNull()) {
			userError = null;
		}		
		return userError;
	}
	
	
	public static UserError signUpValidation(String userName, String userPass, String checkPass) {
		UserError userError = new UserError();
		if (isEmpty(userName)) {
			userError.setUserNameErrorMessage(FILL_USER_NAME);
		} else if (isNotCorrectLength(userName, USER_NAME_MIN_LENGTH, USER_NAME_MAX_LENGTH)) {
			userError.setUserNameErrorMessage(NOT_CORRECT_USER_NAME_LENGTH);
		} else if (isNotUniqueUserName(userName)) {
			userError.setUserNameErrorMessage(NOT_UNIQUE_USER_NAME);
		}
		
		if (isEmpty(userPass)) {
			userError.setUserPassErrorMessage(FILL_USER_PASS);
		} else if (isNotCorrectLength(userPass, USER_PASS_MIN_LENGTH, USER_PASS_MAX_LENGTH)) {
			userError.setUserPassErrorMessage(NOT_CORRECT_USER_PASS_LENGTH);
		} else if (isNotMatchRegex(userPass, USER_PASS_PATTERN)) {
			userError.setUserPassErrorMessage(NOT_MATCH_REGEX_PASS);
		} else if (isNotCorrectCheckPass(userPass, checkPass)) {
			userError.setUserPassErrorMessage(NOT_CORRECT_CHECK_PASS);
		} 
		
		if (userError.isAllFieldNull()) {
			userError = null;
		}
		return userError;
	}
	
	
	public static UserError updateUserNameValidation(String userName, int userId) {
		UserError userError = new UserError();
		if (isEmpty(userName)) {
			userError.setUserNameErrorMessage(FILL_USER_NAME);
		} else if (isNotCorrectLength(userName, USER_NAME_MIN_LENGTH, USER_NAME_MAX_LENGTH)) {
			userError.setUserNameErrorMessage(NOT_CORRECT_USER_NAME_LENGTH);
		} else if (isNotUniqueUserName(userName, userId)) {
			userError.setUserNameErrorMessage(NOT_UNIQUE_USER_NAME);
		}
		
		if (userError.isAllFieldNull()) {
			userError = null;
		}
		return userError;
	}
	
	
	public static UserError updateUserPassValidation(String currPass, String newPass, String checkPass, int userId) {
		User user = UserDao.findByUserId(userId);
		String userPass = user.getUserPass();
		
		UserError userError = new UserError();
		if (isEmpty(currPass)) {
			userError.setUserPassErrorMessage(FILL_USER_PASS);
		} else if (isNotCorrectCheckPass(currPass, userPass)) {
			userError.setUserPassErrorMessage(NOT_CORRECT_USER_PASS);
		}
		
		if (isEmpty(newPass)) {
			userError.setUserNewPassErrorMessage(FILL_USER_PASS);
		} else if (isNotCorrectLength(newPass, USER_PASS_MIN_LENGTH, USER_PASS_MAX_LENGTH)) {
			userError.setUserNewPassErrorMessage(NOT_CORRECT_USER_PASS_LENGTH);
		} else if (isNotMatchRegex(newPass, USER_PASS_PATTERN)) {
			userError.setUserPassErrorMessage(NOT_MATCH_REGEX_PASS);
		} else if (isNotCorrectCheckPass(newPass, checkPass)) {
			userError.setUserNewPassErrorMessage(NOT_CORRECT_USER_PASS);
		}
		
		if (userError.isAllFieldNull()) {
			userError = null;
		}
		return userError;
	}
	
	public static UserError deleteUserValidation(String userPass, int userId) {
		User user = UserDao.findByUserId(userId);
		String checkPass = user.getUserPass();
		
		UserError userError = new UserError();
		if (isNotCorrectCheckPass(userPass, checkPass)) {
			userError.setUserPassErrorMessage(NOT_CORRECT_USER_PASS);
		}
		
		if (userError.isAllFieldNull()) {
			userError = null;
		}
		return userError;
	}
	
	
	
	private static boolean isEmpty(String value) {
		return (value == "");
	}
	
	private static boolean isNotCorrectLength(String value, int min, int max) {
		if (value.length() < min || value.length() > max) {
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isNotUniqueUserName(String userName) {
		User user = UserDao.findByUserName(userName);
		if (user != null) {
			return true;
		} else {
			return false;
		}
	}
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
	
	private static boolean isNotUserExist(String userName, String userPass) {
		User user = UserDao.findByNamePass(userName, userPass);
		if (user == null) {
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isNotCorrectCheckPass(String userPass, String checkPass) {
		if (!userPass.equals(checkPass)) {
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isNotMatchRegex(String value, String strPattern) {
		Pattern pattern = Pattern.compile(strPattern); 
        Matcher matcher = pattern.matcher(value);
        if (!matcher.find()) {
            return true;
        } else {
            return false;
        } 
	}
	
}
