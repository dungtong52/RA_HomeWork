import java.util.Arrays;
import java.util.Scanner;

public class Main {
	// Thuật toán sắp xếp chọn
	public static void selectionSort (int[] arr) {
		int n = arr.length;

		for (int i = 0; i < n - 1; i++) {
			int maxIndex = i;

			for (int j = i + 1; j < n; j++) {
				if (arr[j] > arr[maxIndex]) {
					maxIndex = j;
				}
			}

			int temp = arr[i];
			arr[i] = arr[maxIndex];
			arr[maxIndex] = temp;
		}

	}
	// Thuật toán tìm kiếm tuyến tính
	public static int linearSearch (int[] arr, int key){
		for (int i = 0; i < arr.length; i++){
			if (arr[i] == key){
				return i;
			}
		}
		return -1;
	}
	// Thuật toán tìm kiếm nhị phân
	public static int binarySearch(int[] arr, int key){
		int left = 0;
		int right = arr.length - 1;

		while (left <= right){
			int mid = (left + right) / 2;

			if (arr[mid] == key){
				return mid;
			} else if (key > arr[mid]) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return -1;
	}

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập kích thước mảng: ");
		int n = Integer.parseInt(sc.nextLine());

		int[] arr = new int[n];
		System.out.println("Nhập các phần tử cho mảng:");
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(sc.nextLine());
		}
		System.out.print("Nhập số cần tìm: ");
		int key = Integer.parseInt(sc.nextLine());

		// Sắp xếp bảng
		selectionSort(arr);
		System.out.println("Mảng sau khi sắp xếp theo thứ tự giảm dần: " + Arrays.toString(arr));

		// Tìm kiếm tuyến tính
		System.out.printf("Tìm kiếm tuyến tính: Phần tử %d tìm thấy tại chỉ số: %d\n", key, linearSearch(arr, key));

		// Tìm kiếm nhị phân
		System.out.printf("Tìm kiếm nhị phân: Phần tử %d tìm thấy tại chỉ số: %d", key, binarySearch(arr, key));

	}
}
