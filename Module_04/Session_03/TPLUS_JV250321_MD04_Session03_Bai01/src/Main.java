import java.util.Scanner;

public class Main {
	public static void selectionSort(int[] arr) {
		int n = arr.length;

		for (int i = 0; i < n - 1; i++){
			int maxIndex = i;

			for (int j = i + 1; j < n; j++){
				if(arr[j] > arr[maxIndex]){
					maxIndex = j;
				}
			}

			int temp = arr[maxIndex];
			arr[maxIndex] = arr[i];
			arr[i] = temp;
		}
	}
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);

		// Nhập mảng
		System.out.print("Nhập kích thước mảng: ");
		int n = Integer.parseInt(sc.nextLine());
		int[] arr = new int[n];
		System.out.println("Nhập các phần tử cho mảng: ");
		for (int i = 0; i < n; i++){
			arr[i] = Integer.parseInt(sc.nextLine());
		}

		// Sắp xếp mảng
		selectionSort(arr);
		int max = arr[0];

		// Output: in mảng, số lớn nhất
		System.out.print("Mảng sau khi sắp xếp theo thứ tự giảm dần: ");
		for(int num : arr){
			System.out.print(num + " ");
		}
		System.out.println("\nSố lớn nhất: " + max);
	}
}
