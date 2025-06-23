import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		int age;

		while (true){
			System.out.print("Nhập vào tuổi của bạn: ");
			if(sc.hasNextInt()) {
				age = sc.nextInt();
				if(age > 0) {
					System.out.printf("Tuổi của bạn là %d !%n", age);
					break;
				}
			} else {
				sc.next();
			}
			System.out.println("Vui lòng nhập vào một số nguyên và lớn hơn 0");
		}

		sc.close();
	}
}
