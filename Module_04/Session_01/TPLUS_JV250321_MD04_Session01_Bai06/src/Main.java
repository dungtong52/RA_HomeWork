import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("width = ");
		float width = Float.parseFloat(sc.nextLine());

		System.out.print("height = ");
		float height = Float.parseFloat(sc.nextLine());

		float area = width * height;
		float circumference = (width + height) * 2;

		System.out.printf("Diện tích: %.2f\n", area);
		System.out.printf("Chu vi: %.2f", circumference);

	}
}
