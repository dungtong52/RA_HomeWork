import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("***************************** MENU *****************************");
			System.out.println("1. Nhập chuỗi");
			System.out.println("2. Đếm số ký tự thường, hoa, số, đặc biệt");
			System.out.println("3. Đảo ngược chuỗi");
			System.out.println("4. Kiểm tra Palindrome");
			System.out.println("5. Chuẩn hóa chuỗi (xóa khoảng trắng dư thừa, viết hoa chữ cái đầu)");
			System.out.println("6. Thoát");
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice){
				case 1:
					// Nhập chuỗi
					String str = new String();
					System.out.println("Hãy nhập chuỗi: ");
					str = scanner.nextLine();
					System.out.println("Chuỗi được thêm thành công!");
					break;
				case 2:
					// Đếm số ký tự thường, hoa, số, đặc biệt

					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					System.exit(0);
				default:
					System.err.println("Hãy nhập các số từ 1-6");
			}
		} while (true);
	}
}
