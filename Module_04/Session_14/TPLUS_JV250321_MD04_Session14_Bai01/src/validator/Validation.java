package validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validation {
    public static boolean isEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isPositiveInteger(String input) {
        if (isEmpty(input)) return false;
        try {
            int value = Integer.parseInt(input.trim());
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isIntegerInRange(String input, int min, int max) {
        if (isEmpty(input)) return false;
        try {
            int value = Integer.parseInt(input.trim());
            return value >= min && value <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String input) {
        if (isEmpty(input)) return false;
        try {
            Double.parseDouble(input.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidDate(String input, String pattern) {
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
