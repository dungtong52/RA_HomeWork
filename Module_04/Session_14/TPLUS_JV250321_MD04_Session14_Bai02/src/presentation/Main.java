package presentation;

import business.BusManager;
import validator.Validation;

import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		BusManager busManager = new BusManager();
		do {
			System.out.println("\n*************** QUẢN LÝ XE KHÁCH ******************");
			System.out.println(
					"1. Hiển thị danh sách xe\n" +
							"2. Thêm xe khách\n" +
							"3. Sửa xe khách\n" +
							"4. Xóa xe khách\n" +
							"5. Lọc xe theo ngày tham gia\n" +
							"6. Tìm kiếm xe theo biển số xe\n" +
							"7. Thoát");
			System.out.print("Lựa chọn của bạn: ");
			String choiceInput = scanner.nextLine();
			if (Validation.isIntegerInRange(choiceInput, 1, 7)) {
				int choice = Integer.parseInt(choiceInput);
				switch (choice) {
					case 1 -> busManager.displayBusMap();
					case 2 -> busManager.addBus(scanner);
					case 3 -> busManager.updateBus(scanner);
					case 4 -> busManager.deleteBus(scanner);
					case 5 -> busManager.filterBusByDate(scanner);
					case 6 -> busManager.findBusByLicensePlate(scanner);
					default -> System.exit(0);
				}
			} else {
				System.err.println("Nhập vào số nguyên từ 1-7");
			}
		} while (true);
	}
}
