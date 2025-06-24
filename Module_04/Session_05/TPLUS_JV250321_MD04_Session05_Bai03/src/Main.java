import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập vào số hàng: ");
		int rows = Integer.parseInt(sc.nextLine());
		System.out.println("Nhập vào số cột: ");
		int cols = Integer.parseInt(sc.nextLine());

		if(rows <= 0)
			System.err.println("Số hàng không hợp lệ");
		else {
			int[][] arr = new int[rows][cols];
			int sum = 0;

			System.out.print("Nhập hàng các phần tử: ");
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					arr[i][j] = Integer.parseInt(sc.nextLine());
				}
			}
			// In mang
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					System.out.print(arr[i][j]);
				}
				System.out.println("");
			}
			System.out.print("Nhập hàng k bạn muốn tính tổng: ");
			int k = Integer.parseInt(sc.nextLine());
			if (k > rows || k <= 0)
				System.out.println("Hàng cần tính không tồn tại");
			else {
				for(int i = 0; i < cols; i++){
					sum += arr[k-1][i];
				}
			}
			System.out.println("sum = " + sum);
		}
	}
}
