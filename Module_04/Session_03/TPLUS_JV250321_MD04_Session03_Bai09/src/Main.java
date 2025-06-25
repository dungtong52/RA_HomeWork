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

			// Lưu tổng của mảng con liên tục hiện tại
			// Lưu tổng lớn nhất tìm được cho đến nay, khởi tạo bằng giá trị số nguyên nhỏ nhất có thể
			int sumCurrent = 0;
			int sumRecord = Integer.MIN_VALUE;

			// Lặp qua mảng để tìm tổng mảng con lớn nhất
			for (int i = 0; i < n; i++) {
				sumCurrent += arr[i];

				// Cập nhật sumRecord nếu sumCurrent lớn hơn
				sumRecord = Math.max(sumCurrent, sumRecord);

				if (sumCurrent < 0) {
					sumCurrent = 0;
				}
			}

			// Nếu sumCurrent bằng 0, điều đó có nghĩa là tất cả các phần tử đều âm hoặc bằng không,
			// chọn phần tử lớn nhất làm kết quả
			// Ngược lại, sumRecord giữ tổng lớn nhất của một mảng con liên tục.
			if (sumCurrent == 0) {
				int max = arr[0];

				for (int number : arr) {
					if (number > max)
						max = number;
				}
				System.out.println("Phần tử lớn nhất: " + max);
			} else {
				System.out.println("Tổng lớn nhất: " + sumRecord);
			}
		}
	}
}
