import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập số nguyên dương N: ");
		int n = sc.nextInt();

		if(n <= 0) System.out.println("Số nhập vào không hợp lệ");
		else {
			int sum = 0;
			for (int i = 1; i <= n; i++){
				sum += i;
			}
			System.out.printf("Tổng của các số từ 1 đên %d là: %d", n, sum);
		}
	}
}
