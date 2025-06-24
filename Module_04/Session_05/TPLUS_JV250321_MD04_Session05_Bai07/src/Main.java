import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("=================MENU=================");
			System.out.println("1. Kiểm tra số nguyên tố");
			System.out.println("2. Tính giai thừa");
			System.out.println("3. Tìm UCLN");
			System.out.println("4. Thoát");
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
				case 1:
					int count = 0;
					System.out.print("Kiểm tra số nguyên tố. Nhập vào số cần kiểm tra: ");
					int number = Integer.parseInt(sc.nextLine());
					if (number <= 0)
						System.out.println("Số nhập vào không hợp lệ");
					else {
						for (int i = 1; i <= number; i++) {
							if (number % i == 0)
								count++;
						}
						if (count == 2)
							System.out.printf("Số %d là số nguyên tố\n", number);
						else
							System.out.printf("Số %d không phải là số nguyên tố\n", number);
					}
					break;
				case 2:
					int factorial = 1;
					System.out.print("Tính giai thừa. Nhập vào số cần tính: ");
					int n = Integer.parseInt(sc.nextLine());
					if (n <= 0)
						System.out.println("Số nhập vào không hợp lệ");
					else {
						for (int i = 1; i <= n; i++) {
							factorial *= i;
						}
						System.out.printf("Giai thừa của %d = %d\n", n, factorial);
					}
					break;
				case 3:
					System.out.print("Tìm UCLN. Nhập vào số thứ 1: ");
					int number1 = Integer.parseInt(sc.nextLine());
					System.out.print("Tìm UCLN. Nhập vào số thứ 2: ");
					int number2 = Integer.parseInt(sc.nextLine());
					int temp;

					while (number2 != 0) {
						temp = number2;
						number2 = number1 % number2;
						number1 = temp;
					}
					System.out.println("Ước chung lớn nhất: " + number1);
					break;
				case 4:
					System.exit(0);
				default:
					System.err.println("Nhập các số từ 0-4");
			}
		} while (true);
	}
}
