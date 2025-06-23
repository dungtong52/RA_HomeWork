import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		// Input: khởi tạo mảng, người dùng nhập từng giá trị cho mảng
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập kích thước mảng: ");
		int n = Integer.parseInt(sc.nextLine());
		int[] arr = new int[n];
		System.out.println("Nhập các phần tử cho mảng: ");
		for(int i = 0; i < n; i++){
			arr[i] = Integer.parseInt(sc.nextLine());
		}

		// Solution: Tính tổng các phần tử trong mảng
		int sum = 0;
		for (int number : arr){
			sum += number;
		}

		// Output: In ra giá trị tổng
		System.out.println("Tổng các phần tử trong mảng là: " + sum);
	}
}
