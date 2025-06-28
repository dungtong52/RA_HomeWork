import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] wordList = new String[100];
		int currentIndex = 0;
		do {
			System.out.println(
					"\n======= QUẢN LÝ TỪ VỰNG TIẾNG ANH =======\n" +
							"1. Thêm từ mới\n" +
							"2. Hiển thị danh sách từ vựng\n" +
							"3. Sửa từ vựng theo vị trí\n" +
							"4. Xóa từ vựng theo vị trí\n" +
							"5. Tìm từ chứa ký tự người dùng nhập vào\n" +
							"6. Đếm số từ bắt đầu bằng chữ cái người dùng nhập vào\n" +
							"7. Sắp xếp danh sách từ theo thứ tự A-Z\n" +
							"8. Thoát\n" +
							"=========================================");
			System.out.print("Chọn: ");
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
				case 1:
					// Thêm từ mới
					System.out.print("Nhập số lượng từ mới muốn thêm vào: ");
					int n = Integer.parseInt(sc.nextLine());
					for (int i = 0; i < n; i++) {
						System.out.printf("Nhập vào từ thứ %d: ", i + 1);
						wordList[currentIndex] = sc.nextLine();
						currentIndex++;
					}
					System.out.printf("Đã thêm thành công. Danh sách hiện có %d từ", currentIndex);
					break;

				case 2:
					// Hiển thị danh sách từ vựng
					System.out.println("Danh sách từ vựng: ");
					for (int i = 0; i < currentIndex; i++) {
						System.out.printf("%2d. %s\n", i + 1, wordList[i]);
					}
					break;

				case 3:
					// Sửa từ vựng theo vị trí
					System.out.print("Nhập vị trí từ vựng muốn sửa: ");
					int updateIndex = Integer.parseInt(sc.nextLine());
					if (updateIndex < 0 || updateIndex >= currentIndex) {
						System.err.println("Vị trí muốn sửa không chính xác");
					} else {
						System.out.print("Nhập nội dung từ sau khi sửa: ");
						wordList[updateIndex] = sc.nextLine();
						System.out.println("Đã sửa thành công!");
					}
					break;

				case 4:
					// Xóa từ vựng theo vị trí
					System.out.print("Nhập vị trí từ vựng muốn xóa: ");
					int deleteIndex = Integer.parseInt(sc.nextLine());
					if (deleteIndex < 0 || deleteIndex >= currentIndex) {
						System.err.println("Vị trí muốn xóa không chính xác");
					} else {
						for (int i = deleteIndex; i < currentIndex - 1; i++) {
							wordList[i] = wordList[i + 1];
						}
						wordList[currentIndex - 1] = null;
						currentIndex--;
						System.out.println("Đã xóa thành công!");
					}
					break;

				case 5:
					// Tìm từ chứa ký tự người dùng nhập vào
					System.out.print("Nhập vào ký tự muốn tìm: ");
					char key = sc.nextLine().charAt(0);
					System.out.print("Kết quả tìm: ");
					for (int i = 0; i < currentIndex; i++) {
						if (wordList[i].contains(key + "")) {
							System.out.printf("%2d. %s\n", i + 1, wordList[i]);
						}
					}
					break;

				case 6:
					// Đếm số từ bắt đầu bằng chữ cái người dùng nhập vào
					System.out.print("Nhập vào ký tự muốn tìm: ");
					char firstLetter = sc.nextLine().charAt(0);
					int count = 0;
					for (int i = 0; i < currentIndex; i++) {
						if (wordList[i].charAt(0) == firstLetter) {
							count++;
						}
					}
					System.out.printf("Có %d từ có ký tự đầu tiên là %s", count, firstLetter);
					break;

				case 7:
					// Sắp xếp danh sách từ theo thứ tự A-Z
					for (int i = 0; i < currentIndex - 1; i++) {
						for (int j = i + 1; j < currentIndex; j++) {
							if (wordList[i].toLowerCase().compareTo(wordList[j].toLowerCase()) > 0) {
								String temp = wordList[i];
								wordList[i] = wordList[j];
								wordList[j] = temp;
							}
						}
					}
					System.out.println("Danh sách đã được sắp xếp lại từ A-Z");
					break;

				case 8:
					System.exit(0);
				default:
					System.err.println("Nhập vào số trong khoảng 1-8");
			}
		} while (true);
	}
}
