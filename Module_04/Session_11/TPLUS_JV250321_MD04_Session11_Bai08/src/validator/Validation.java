package validator;

public class Validation {
    public static boolean isEmpty(String data) {
        return data == null || data.trim().isEmpty();
    }
}