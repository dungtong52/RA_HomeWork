import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		// Input: Nhập kich thuoc mảng
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập kích thước mảng: ");
		int n = Integer.parseInt(sc.nextLine());

		if (n == 0) System.out.println("Mảng không hợp lệ");
		else {
			// Nhap cac phan tu cho mang
			int[] originArr = new int[n];
			System.out.println("Nhập các phần tử cho mảng: ");
			for (int i = 0; i < n; i++) {
				originArr[i] = Integer.parseInt(sc.nextLine());
			}

			// Tạo mảng mới để lưu trữ số lần lặp của mỗi phần tử tương ứng trên originArr
			int[] frequencyArr = new int[n];
			for (int i = 0; i < n - 1; i++) {
				int count = 1;
				for (int j = 1; j < n; j++) {
					if (originArr[i] == originArr[j]) {
						count++;
					}
				}
				frequencyArr[i] = count;
			}

			// Đếm số phần tử trong mảng mới có giá trị = 1 --> tạo ra mảng mới khác có số phần tử = uniqueCount
			int uniqueCount = 0;
			for (int i = 0; i < n; i++) {
				if (frequencyArr[i] == 1)
					uniqueCount++;
			}
			if (uniqueCount == 0)
				System.out.println("Không có phần tử nào duy nhất trong mảng");
			else {
				int[] outputArr = new int[uniqueCount];

				// Gán các phần tử có số lần lặp = 1 từ mảng gốc originArr
				int outputIndex = 0;
				for (int i = 0; i < n; i++) {
					if (frequencyArr[i] == 1) {
						outputArr[outputIndex] = originArr[i];
						outputIndex++;
					}
				}

				// Output: In ra mảng sau khi loại bỏ trùng lặp
				System.out.println(Arrays.toString(outputArr));
			}
		}
		sc.close();
	}
}
