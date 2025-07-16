package validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Validation {
    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isIntegerInRange(String input, int min, int max) {
        if (!isNotEmpty(input)) return false;
        try {
            int number = Integer.parseInt(input);
            return number >= min && number <= max;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isPositiveInteger(String input) {
        if (!isNotEmpty(input)) return false;
        try {
            int number = Integer.parseInt(input);
            return number > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isPositiveFloat(String input) {
        if (!isNotEmpty(input)) return false;
        try {
            float number = Float.parseFloat(input);
            return number > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidLength(String input, int maxLength) {
        if (!isNotEmpty(input)) return false;
        try {
            return input.length() <= maxLength;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidDate(String input, String pattern) {
        if (!isNotEmpty(input)) return false;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate.parse(input, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidUserName(String input) {
        return isNotEmpty(input) && input.matches("^[a-zA-Z0-9_]{1,30}$");
    }

    public static boolean isValidPassword(String input) {
        return isNotEmpty(input) && input.matches("^[a-zA-Z0-9$%]{1,30}$");
    }
}
