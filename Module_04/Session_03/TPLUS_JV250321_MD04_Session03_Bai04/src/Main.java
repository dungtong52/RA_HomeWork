import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		// Input: khởi tạo mảng, người dùng nhập từng giá trị cho mảng
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập kích thước mảng: ");
		int n = Integer.parseInt(sc.nextLine());

		if (n == 0) System.out.println("Kích thước rỗng");
		else {
			int[] arr = new int[n];
			System.out.println("Nhập các phần tử cho mảng: ");
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(sc.nextLine());
			}

			// Output: Hiển thị mảng ban đầu:
			System.out.println("Mảng ban đầu: " + Arrays.toString(arr));

			// dùng for lặp từ đầu đến n/2
			// cần thêm biến temp để đổi giá trị 2 số
			for (int i = 0; i < n / 2; i++) {
				int temp = arr[i];
				arr[i] = arr[n - 1 - i];
				arr[n - 1 - i] = temp;
			}

			// Output: Hiển thị mảng sau xử lý
			System.out.println("Mảng sau khi đảo ngược: " + Arrays.toString(arr));
		}


	}
}
