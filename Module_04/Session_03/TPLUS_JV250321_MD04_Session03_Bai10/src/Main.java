import java.util.Scanner;

public class Main {
    // Hàm chính để sắp xếp mảng sử dụng Quick Sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // pi là chỉ số phân vùng, arr[pi] hiện tại đã ở đúng vị trí
            int pi = partition(arr, low, high);

            // Sắp xếp riêng các phần tử trước và sau phân vùng
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Hàm này nhận phần tử cuối cùng làm pivot,
    // đặt phần tử pivot vào đúng vị trí của nó trong mảng đã sắp xếp,
    // và đặt tất cả các phần tử nhỏ hơn (nhỏ hơn pivot) về bên trái của pivot
    // và tất cả các phần tử lớn hơn về bên phải của pivot.
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Chọn phần tử cuối cùng làm pivot
        int i = (low - 1); // Chỉ số của phần tử nhỏ hơn

        for (int j = low; j < high; j++) {
            // Nếu phần tử hiện tại nhỏ hơn hoặc bằng pivot
            if (arr[j] <= pivot) {
                i++;

                // Hoán đổi arr[i] và arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Hoán đổi arr[i+1] và arr[high] (hoặc pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // Hàm tiện ích để in mảng
    static void printArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
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

			quickSort(arr, 0, n-1);
			printArray(arr);
        }

    }
}
