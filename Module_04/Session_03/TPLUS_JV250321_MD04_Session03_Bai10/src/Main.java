import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập vào 1 số nguyên n: ");
		int n = Integer.parseInt(sc.nextLine());
		if (n <= 0) {
			System.err.println("Mảng không hợp lệ");
		} else {
			int[] arr = new int[n];
			System.out.println("Nhập các phần tử: ");
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(sc.nextLine());
			}

			// CODE QUICK SORT

		}

	}
}
