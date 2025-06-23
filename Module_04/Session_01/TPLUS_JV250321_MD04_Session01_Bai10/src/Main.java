import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		// Input: Scanner --> speed, time
		Scanner sc = new Scanner(System.in);
		System.out.print("Vận tốc = ");
		float speed = Float.parseFloat(sc.nextLine());

		System.out.print("Thời gian = ");
		float time = Float.parseFloat(sc.nextLine());

		// Solution: if speed & time > 0 --> distance = speed * time
		// if speed || time < 0 --> print "Vận tốc hoặc thời gian không hợp lệ"
		if(speed < 0 || time < 0)
			System.out.println("Vận tốc hoặc thời gian không hợp lệ");
		else {
			float distance = speed * time;
			System.out.printf("Quãng đường = %.0f (km)", distance);
		}
	}
}
