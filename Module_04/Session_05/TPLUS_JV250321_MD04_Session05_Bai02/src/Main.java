import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập vào số nguyên N: ");
		int n = Integer.parseInt(sc.nextLine());

		if (n <= 0 || n > 100) {
			System.err.println("Số lượng phần tử không hợp lệ");
		} else {
			int[] arr = new int[n];
			int countOdd = 0;
			int countEven = 0;

			System.out.println("Nhập các phần tử của mảng:");
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(sc.nextLine());
			}

			for (int i = 0; i < n; i++) {
				if (arr[i] % 2 == 0 && arr[i] % 3 == 0)
					countEven++;
				if (arr[i] % 2 == 1 && arr[i] % 3 == 0)
					countOdd++;
			}

			System.out.println("countEven = " + countEven);
			System.out.println("countOdd = " + countOdd);

		}

	}
}
