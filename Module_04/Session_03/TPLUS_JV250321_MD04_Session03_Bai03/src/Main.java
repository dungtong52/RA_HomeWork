import java.util.Scanner;

public class Main {
	// Sắp xếp nổi bọt Bubble Sort
	public static void bubbleSort (int[] arr) {
		int n = arr.length;
		boolean swapped;

		for (int i = 0; i < n - 1; i++) {
			swapped = false;

			for (int j = 0; j < n - 1 - i; j++) {
				if (arr[j] < arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					swapped = true;
				}
			}

			if (!swapped) break;
		}
	}

	public static void main (String[] args) {
		// Input: khởi tạo mảng, người dùng nhập từng giá trị cho mảng
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập kích thước mảng: ");
		int n = Integer.parseInt(sc.nextLine());
		int[] arr = new int[n];
		System.out.println("Nhập các phần tử cho mảng: ");
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(sc.nextLine());
		}

		// Solution: Sắp xếp nổi bọt để sắp xếp mảng giảm dần
		bubbleSort(arr);

		// Output: In mảng
		System.out.print("Mảng sau khi sắp xếp theo thứ tự giảm dần: ");
		for(int number : arr){
			System.out.print(number + " ");
		}
	}
}
