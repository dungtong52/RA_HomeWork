package ra.presentation;

import ra.entity.Student;

import java.util.Scanner;

public class StudentManagement {
	public static Student[] students = new Student[100];
	public static int currentIndex = 0;

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println(
					"\n********************* QUẢN LÝ SINH VIÊN ********************\n" +
							"1. Hiển thị danh sách sinh viên\n" +
							"2. Thêm sinh viên\n" +
							"3. Cập nhật thông tin sinh viên theo mã sinh viên\n" +
							"4. Xóa sinh viên theo mã sinh viên\n" +
							"5. Tìm sinh viên theo tên sinh viên\n" +
							"6. Thoát");
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
				case 1:
					showAllStudent();
					break;
				case 2:
					addStudent(scanner);
					break;
				case 3:
					updateStudentData(scanner);
					break;
				case 4:
					deleteStudentById(scanner);
					break;
				case 5:
					findStudentByName(scanner);
					break;
				case 6:
					System.exit(0);
				default:
					System.err.println("Nhập vào số trong khoảng 1-6");
			}
		} while (true);
	}

	public static void showAllStudent () {
		System.out.println("----- Student List -----");
		for (int i = 0; i < currentIndex; i++) {
			students[i].displayData();
		}
	}

	public static void addStudent (Scanner scanner) {
		System.out.print("Nhập số sinh viên muốn thêm: ");
		int countStudent = Integer.parseInt(scanner.nextLine());

		for (int i = 0; i < countStudent; i++) {
			students[currentIndex] = new Student();
			students[currentIndex].inputData(scanner);
			currentIndex++;
		}
		System.out.println("Thêm sinh viên thành công!");
	}

	public static void updateStudentData (Scanner scanner) {
		System.out.print("Nhập vào mã sinh viên muốn cập nhật: ");
		String idForUpdate = scanner.nextLine();
		int indexForUpdate = findIndexById(idForUpdate);

		if (indexForUpdate == -1) {
			System.err.println("Không tồn tại mã sinh viên này!");
		} else {
			System.out.println("Chọn thông tin bạn muốn cập nhật");
			do {
				System.out.println("1. Cập nhật tên sinh viên");
				System.out.println("2. Cập nhật tuổi sinh viên");
				System.out.println("3. Cập nhật chuyên ngành của sinh viên");
				System.out.println("4. Thoát cập nhật");
				System.out.print("Lựa chọn của bạn: ");
				int choice = Integer.parseInt(scanner.nextLine());
				switch (choice) {
					case 1:
						students[indexForUpdate].setStudentName(students[indexForUpdate].inputStudentName(scanner));
						System.out.println("Cập nhật tên thành công!");
						break;
					case 2:
						students[indexForUpdate].setAge(students[indexForUpdate].inputAge(scanner));
						System.out.println("Cập nhật tuổi thành công!");
						break;
					case 3:
						students[indexForUpdate].setMajor(students[indexForUpdate].inputMajor(scanner));
						System.out.println("Cập nhật chuyên ngành thành công!");
						break;
					case 4:
						return;
					default:
						System.err.println("Nhập vào số trong khoảng 1-4");
				}
			} while (true);
		}
	}

	public static void deleteStudentById (Scanner scanner) {
		System.out.print("Nhập vào mã sinh viên muốn cập nhật: ");
		String idForDelete = scanner.nextLine();
		int indexForDelte = findIndexById(idForDelete);

		if (indexForDelte == -1) {
			System.err.println("Không tồn tại mã sinh viên này!");
		} else {
			for (int i = indexForDelte; i < currentIndex - 1; i++) {
				students[i] = students[i + 1];
			}
			students[currentIndex - 1] = null;
			currentIndex--;
			System.out.println("Xóa thành công");
		}
	}

	public static int findIndexById (String id) {
		for (int i = 0; i < currentIndex; i++) {
			if (students[i].getStudentId().equalsIgnoreCase(id)) {
				return i;
			}
		}
		return -1;
	}

	public static void findStudentByName (Scanner scanner) {
		System.out.print("Nhập tên sinh viên muốn tìm: ");
		String nameToFind = scanner.nextLine();

		System.out.println("Danh sách sinh viên cần tìm: ");
		for (int i = 0; i < currentIndex; i++) {
			if (students[i].getStudentName().toLowerCase().contains(nameToFind.trim().toLowerCase())) {
				students[i].displayData();
			}
		}
	}
}
