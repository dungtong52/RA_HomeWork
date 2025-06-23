import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập số nguyên có 4 chữ số: ");
		int number = Integer.parseInt(sc.nextLine());

		int sum = 0;
		// Tách số thành từng chữ số, lưu vào array (có thể dùng biến)
		int[] numberArr = new int[4];
		for(int i = 0; i < numberArr.length; i++){
			numberArr[i] = number % 10;
			number /= 10;
			sum += numberArr[i];   // Tính tổng các chữ số = tính tổng các phần tử trong arr
		}
		String reverseNumber = "";
		for(int i = 0; i < numberArr.length; i++){
			reverseNumber += numberArr[i];
		}

		// In ra số nghịch đảo + sum
		System.out.println("Tổng các chữ số = " + sum);
		System.out.println("Số nghịch đảo: " + reverseNumber);
	}
}
