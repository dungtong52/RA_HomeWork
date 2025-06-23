import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Bán kính: ");
		float radius = Float.parseFloat(sc.nextLine());

		float circumference = 2 * (float)Math.PI * radius;
		float area = (float)(Math.PI * Math.pow(radius, 2));

		System.out.printf("Chu vi = %.2f\n", circumference);
		System.out.printf("Diện tích = %.2f", area);
	}
}
