import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		// Input: Nhập kích thước mảng: row và col
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập số hàng: ");
		int row = Integer.parseInt(sc.nextLine());
		System.out.print("Nhập số cột: ");
		int col = Integer.parseInt(sc.nextLine());
		int[][] arr = new int[row][col];

		// Input: Nhập các phần tử theo hàng
		System.out.println("Nhập các phần tử cho mảng theo từng hàng:");
		for (int i = 0; i < row; i++) {
			System.out.printf("Hàng %d: ", i + 1);
			for (int j = 0; j < col; j++) {
				arr[i][j] = Integer.parseInt(sc.next());
			}
		}

		// Solution: Tinh tong
		int sumOdd = 0;
		int sumEven = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (arr[i][j] % 2 == 0) {
					sumEven += arr[i][j];
				} else
					sumOdd += arr[i][j];
			}
		}

		// Output: In sumOdd, sumEven
		System.out.println("Tổng các số chẵn là: " + sumEven);
		System.out.println("Tổng các số lẻ là: " + sumOdd);
	}
}
