import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		Student[] students = new Student[100];
		int currentIndex = 0;

		do {
			System.out.println(
					"\n--------------- MENU -----------------\n" +
							"1. Hiển thị danh sách tất cả sinh viên.\n" +
							"2. Thêm mới sinh viên.\n" +
							"3. Sửa thông tin sinh viên dựa vào mã sinh viên.\n" +
							"4. Xóa sinh viên.\n" +
							"5. Thoát."
			);
			System.out.print("Chọn: ");

			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
				case 1:
					// Hiển thị danh sách tất cả sinh viên
					if (currentIndex == 0) {
						System.out.println("Danh sách hiện chưa có sinh viên nào");
					} else {
						System.out.println("Danh sách sinh viên: ");
						for (int i = 0; i < currentIndex; i++) {
							students[i].display();
						}
						System.out.println("----------------------------");
					}
					break;

				case 2:
					// Thêm mới sinh viên
					System.out.println("------ Thêm mới sinh viên -----");
					Student newStudent = new Student();
					newStudent.inputData();
					students[currentIndex] = newStudent;
					currentIndex++;
					System.out.println("Đã thêm sinh viên mới vào danh sách");
					break;

				case 3:
					// Sửa thông tin sinh viên dựa vào mã sinh viên
					System.out.print("Nhập mã sinh viên muốn sửa thông tin: ");
					int idUpate = Integer.parseInt(scanner.nextLine());

					int updateIndex = -1;
					for (int i = 0; i < currentIndex; i++) {
						if (students[i].getId() == idUpate) {
							updateIndex = i;
							break;
						}
					}

					if (updateIndex == -1) {
						System.out.printf("Không tìm thấy sinh viên có ID %d", idUpate);
					} else {
						students[updateIndex].inputData();
						System.out.println("Đã cập nhật thông tin sinh viên!");
					}
					break;

				case 4:
					// Xóa sinh viên dựa vào mã SV
					System.out.print("Nhập mã sinh viên muốn xóa thông tin: ");
					int idDelete = Integer.parseInt(scanner.nextLine());

					int deleteIndex = -1;
					for (int i = 0; i < currentIndex; i++) {
						if (students[i].getId() == idDelete) {
							deleteIndex = i;
							break;
						}
					}

					if (deleteIndex == -1) {
						System.out.printf("Không tìm thấy sinh viên có ID %d", idDelete);
					} else {
						for (int i = deleteIndex; i < currentIndex - 1; i++) {
							students[i] = students[i + 1];
						}
						students[currentIndex - 1] = null;
						currentIndex--;
						System.out.printf("Đã xóa sinh viên có ID %d", idDelete);
					}
					break;

				case 5:
					System.exit(0);

				default:
					System.err.println("Hãy nhập vào các số trong khoảng 1-5!");
			}
		} while (true);
	}
}
