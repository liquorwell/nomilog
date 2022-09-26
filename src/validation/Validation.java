package validation;

/**
 * バリデーションの親クラス
 */
public class Validation {

	/**
	 * 文字数が最小文字数と最大文字数の間かを判定するメソッド
	 * @param value 対象
	 * @param min 最小文字数
	 * @param max 最大文字数
	 * @return 	対象の文字数が最小文字数を下回っている場合 true <br>
	 * 			最大文字数を上回っている場合 true <br>
	 * 			最小文字数と最大文字数の間にある場合 false <br>
	 * 			※最小最大いずれも、一致する場合はfalse
	 */
	protected static boolean isNotCorrectLength(String value, int min, int max) {
		return (value.length() < min || value.length() > max);
	}
}
