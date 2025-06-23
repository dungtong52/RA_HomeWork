import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		// Input: Scanner --> weight, height
		Scanner sc = new Scanner(System.in);
		System.out.print("Cân nặng = ");
		float weight = Float.parseFloat(sc.nextLine());

		System.out.print("Chiều cao = ");
		float height = Float.parseFloat(sc.nextLine());

		// Solution: calculate BMI = weight / height^2
		float bmi = weight / (height * height);

		// Output: BMI
		System.out.printf("Chỉ số BMI = %.2f", bmi);
	}
}
