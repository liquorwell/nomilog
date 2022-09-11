package validation;

public class Validation {

	protected static boolean isEmpty(String value) {
		return (value == "");
	}
	
	protected static boolean isNotCorrectLength(String value, int min, int max) {
		return (value.length() < min || value.length() > max);
	}
}
