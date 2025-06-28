import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] studentList = new String[100];
		int currentIndex = 0;
		do {
			System.out.println(
					"\n========== QUẢN LÝ TÊN SINH VIÊN ==========\n" +
							"1. Thêm tên sinh viên\n" +
							"2. Hiển thị danh sách\n" +
							"3. Tìm tên sinh viên chứa từ khóa\n" +
							"4. Đếm số sinh viên có tên bắt đầu bằng chữ cái nhập vào\n" +
							"5. Sắp xếp danh sách tên theo thứ tự A-Z\n" +
							"6. Thoát\n" +
							"==========================================");
			System.out.print("Chọn: ");
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
				case 1:
					// Thêm tên sinh viên
					System.out.print("Nhập số sinh viên muốn thêm: ");
					int n = Integer.parseInt(sc.nextLine());

					for (int i = 0; i < n; i++) {
						System.out.printf("Nhập tên sinh viên %d: ", i+1);
						studentList[currentIndex] = sc.nextLine();
						currentIndex++;
					}
					System.out.printf("Đã nhập xong. Mảng có %d sinh viên", currentIndex);
					break;

				case 2:
					// Hiển thị danh sách
					System.out.print("Danh sách sinh viên: ");
					for (int i = 0; i < currentIndex; i++) {
						System.out.printf("%10s", studentList[i]);
					}
					break;

				case 3:
					// Tìm tên sinh viên chứa từ khóa
					System.out.print("Hãy nhập vào từ khóa: ");
					String key = sc.nextLine();
					System.out.println("Kết quả tìm kiếm: ");
					for (int i = 0; i < currentIndex; i++) {
						if (studentList[i].toLowerCase().contains(key)) {
							System.out.printf("%10s", studentList[i]);
						}
					}
					break;
				case 4:
					// Đếm số sinh viên có tên bắt đầu bằng chữ cái nhập vào
					System.out.print("Hãy nhập vào chữ cái: ");
					char ch = sc.nextLine().charAt(0);
					int count = 0;
					for (int i = 0; i < currentIndex; i++) {
						if (studentList[i].charAt(0) == ch) {
							count++;
						}
					}
					System.out.printf("Có %d sinh viên có tên bắt đầu bằng %c", count, ch);
					break;

				case 5:
					// Sắp xếp danh sách tên theo thứ tự A-Z
					for (int i = 0; i < currentIndex - 1; i++) {
						for (int j = i + 1; j < currentIndex; j++) {
							if (studentList[i].compareTo(studentList[j]) > 0) {
								String temp = studentList[i];
								studentList[i] = studentList[j];
								studentList[j] = temp;
							}
						}
					}
					System.out.println("Danh sách sau khi sắp xếp: ");
					for (int i = 0; i < currentIndex; i++) {
						System.out.printf("%10s", studentList[i]);
					}
					break;
				case 6:
					System.exit(0);
				default:
					System.err.println("Hãy nhập vào ca số trong khoảng 1-6!");
			}
		} while (true);
	}
}
