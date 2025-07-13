package validation;

public class Validation {
    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }
    public static boolean isValidLength(String input, int maxLength) {
        return isNotEmpty(input) && input.length() <= maxLength;
    }
    public static boolean isValidDecimal(String input, int precision, int scale) {
        if (!isNotEmpty(input)) return false;
        try {
            double value = Double.parseDouble(input);
            String[] parts = input.split("\\.");
            if (parts.length == 2 && parts[1].length() > scale) return false;
            return input.length() <= precision + 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isPositiveInteger(String input) {
        if (!isNotEmpty(input)) return false;
        try {
            int value = Integer.parseInt(input);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
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
    public static boolean isValidEmail(String input) {
        return input != null && input.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$");
    }
}
