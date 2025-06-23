import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("bán kính: ");
		float radius = sc.nextFloat();

		double area = Math.PI * radius * radius;
		System.out.printf("Diện tích: %.2f", area);
	}
}
