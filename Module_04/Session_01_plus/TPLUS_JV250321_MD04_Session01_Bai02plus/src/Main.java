import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập chiều dài: ");
		float height = Float.parseFloat(sc.nextLine());
		System.out.print("Nhập chiều rộng: ");
		float width = Float.parseFloat(sc.nextLine());

		float circumference = (width + height) * 2;
		float area = width * height;

		System.out.println("Chu vi = " + circumference);
		System.out.println("Diện tích = " + area);
	}
}
