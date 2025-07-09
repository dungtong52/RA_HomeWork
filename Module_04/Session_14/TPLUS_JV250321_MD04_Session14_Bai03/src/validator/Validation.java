package validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validation {
	public static boolean isEmpty (String input) {
		return input == null || input.trim().isEmpty();
	}

	public static boolean isIntegerInRange (String input, int min, int max) {
		if (isEmpty(input)) return false;
		try {
			int number = Integer.parseInt(input);
			return number >= min && number <= max;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isPositiveInteger (String input) {
		if (isEmpty(input)) return false;
		try {
			int number = Integer.parseInt(input);
			return number > 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isValidDate (String input, String pattern) {
		if (isEmpty(input)) return false;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		try {
			LocalDate.parse(input.trim(), formatter);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}
}
