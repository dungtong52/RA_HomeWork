package presentation;

import entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManager {
	public static List <User> userList = new ArrayList <>();

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("\n***************** MENU QUẢN LÝ NGƯỜI DÙNG ******************");
			System.out.println("1. Thêm người dùng");
			System.out.println("2. Xóa người dùng");
			System.out.println("3. Hiển thị danh sách người dùng");
			System.out.println("4. Thoát");
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
				case 1:
					addUser(scanner);
					break;
				case 2:
					deleteUser(scanner);
					break;
				case 3:
					displayUserList();
					break;
				case 4:
					System.exit(0);
				default:
					System.err.println("Nhập số 1-4");
			}
		} while (true);

	}

	public static void addUser (Scanner scanner) {
		User newUser = new User();
		newUser.inputData(scanner);
		userList.add(newUser);
		System.out.println("Người dùng được thêm thành công");
	}

	public static void deleteUser (Scanner scanner) {
		System.out.println("Nhập email của người dùng muốn tìm");
		String email = scanner.nextLine();
		boolean isExits = false;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getEmail().equals(email)) {
				userList.remove(i);
				System.out.printf("Đã xóa user có email: %s", email);
				isExits = true;
			}
		}
		if (!isExits) {
			System.err.println("Không tồn tại email này!");
		}
	}

	public static void displayUserList () {
		System.out.println("Danh sách User");
		for (User user : userList) {
			user.displayUser();
		}
	}

}
