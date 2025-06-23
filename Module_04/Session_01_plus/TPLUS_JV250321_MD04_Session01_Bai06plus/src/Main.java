import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập vào 3 số nguyên: ");
		int a = Integer.parseInt(sc.nextLine());
		int b = Integer.parseInt(sc.nextLine());
		int c = Integer.parseInt(sc.nextLine());

		int max = a;
		if (b > max) max = b;
		if (c > max) max = c;

		int min = c;
		if(b < min) min = a;
		if(a < min) min = b;
		// Có thể dùng max, min trong Math: max = Math.max(a, Math.max(b, c))

		System.out.println("Số lớn nhất: " + max);
		System.out.println("Số nhỏ nhất: " + min);
	}
}
