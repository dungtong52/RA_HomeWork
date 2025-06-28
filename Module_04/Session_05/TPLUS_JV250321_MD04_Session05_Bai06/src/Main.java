import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] productList = new String[100];
		int currentIndex = 0;
		do {
			System.out.println(
					"\n======== QUẢN LÝ MÃ SẢN PHẨM =========\n" +
							"1. Nhập danh sách mã\n" +
							"2. Hiển thị danh sách\n" +
							"3. Kiểm tra mã nào hợp lệ (Bắt đầu bằng \"SP\" và theo sau là 3 chữ số)\n" +
							"4. Tìm mã sản phẩm có số lớn nhất (ví dụ SP023 > SP007)\n" +
							"5. Sắp xếp mã theo thứ tự tăng dần theo số\n" +
							"6. Thoát\n" +
							"======================================"
			);
			System.out.print("Chọn: ");
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
				case 1:
					// Nhập danh sách mã
					System.out.print("Nhập số mã muốn thêm vào danh sách: ");
					int n = Integer.parseInt(sc.nextLine());
					for (int i = 0; i < n; i++) {
						System.out.printf("Nhập mã số %d: ", i + 1);
						productList[i] = sc.nextLine();
						currentIndex++;
					}
					System.out.printf("Đã thêm xong. Danh sách hiện có %d mã", currentIndex);
					break;
				case 2:
					// Hiển thị danh sách
					System.out.println("Danh sách mã:");
					for (int i = 0; i < currentIndex; i++) {
						System.out.printf("%10s", productList[i]);
					}
					break;
				case 3:
					// Kiểm tra mã nào hợp lệ (Bắt đầu bằng "SP" và theo sau là 3 chữ số)
					String regex = "SP[0-9]{3}";
					System.out.println("Các mã hợp lệ: ");
					for (int i = 0; i < currentIndex; i++) {
						if (Pattern.matches(regex, productList[i])) {
							System.out.printf("%10s", productList[i]);
						}
					}
					break;
				case 4:
					// Tìm mã sản phẩm có số lớn nhất (ví dụ SP023 > SP007)
					String maxProduct = productList[0];
					for (int i = 1; i < currentIndex; i++) {
						int number = Integer.parseInt(productList[i].substring(2));
						int max = Integer.parseInt(maxProduct.substring(2));
						if (number > max) {
							maxProduct = productList[i];
						}
					}
					System.out.println("Sản phẩm có số lớn nhất: " + maxProduct);
					break;
				case 5:
					// Sắp xếp mã theo thứ tự tăng dần theo số
					for (int i = 0; i < currentIndex - 1; i++) {
						int numberI = Integer.parseInt(productList[i].substring(2));
						for (int j = i + 1; j < currentIndex; j++) {
							int numberJ = Integer.parseInt(productList[j].substring(2));
							if (numberI > numberJ) {
								String temp = productList[i];
								productList[i] = productList[j];
								productList[j] = temp;
							}
						}
					}
					System.out.println("Danh sách được sắp xếp xong");
					break;
				case 6:
					System.exit(0);
				default:
					System.err.println("Nhập các số trong khoảng 1-6");
			}
		} while (true);
	}
}
