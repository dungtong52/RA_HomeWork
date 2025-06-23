import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		// Input: Scanner --> integer number
		Scanner scanner = new Scanner(System.in);
		System.out.print("Nhập vào 1 số nguyên: ");
		int number = Integer.parseInt(scanner.nextLine());

		// Solution: int result = number * number or Math.pow(number, 2)
		int result = (int)Math.pow(number, 2);

		// Output: print result
		System.out.println("Kết quả: " + result);
	}
}
