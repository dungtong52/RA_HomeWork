import java.util.Scanner;

public class Main {
	public static boolean isPrime (int n) {
		int count = 0;
		if (n == 0) return false;
		for (int i = 1; i <= n; i++) {
			if (n % i == 0) count++;
		}
		return count == 2;
	}

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập vào số nguyên N: ");
		int n = Integer.parseInt(sc.nextLine());

		if (n <= 0 || n > 100) {
			System.err.println("Số lượng phần tử không hợp lệ");
		} else {
			int[] arr = new int[n];
			int sum = 0;

			System.out.println("Nhập các phần tử của mảng:");
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(sc.nextLine());
			}

			for (int i = 0; i < n; i++) {
				sum += isPrime(arr[i]) ? arr[i] : 0;
			}
			System.out.println("sum = " + sum);
		}
	}
}
