package validation;

public class Validator {
	public static boolean isEmpty (String data) {
		return data == null || data.trim().isEmpty();
	}
}
