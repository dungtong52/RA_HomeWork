package ra.validator;

import java.util.Scanner;

public class Validation {
	public static boolean isEmpty (String inputData) {
		if (inputData == null || inputData.trim().isEmpty()) {
			return true;
		}
		return false;
	}

	public static int inputChoice (Scanner scanner, String message, int maxValue) {
		System.out.println(message);
		do {
			if (scanner.hasNextInt()) {
				int value = Integer.parseInt(scanner.nextLine());
				if (value >= 1 && value <= maxValue) {
					return value;
				}
				System.err.println("Giá trị nhập không hợp lệ, vui lòng nhập lại");
			}
			System.err.println("Phải là số nguyên, vui lòng nhập lại");
		} while (true);
	}
}
