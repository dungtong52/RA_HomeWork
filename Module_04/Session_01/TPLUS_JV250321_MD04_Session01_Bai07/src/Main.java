import java.util.Scanner;

public class Main {
	public static void main (String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("age = ");
		int age = Integer.parseInt(sc.nextLine());

		if (age < 0)
			System.out.println("Tuổi người dùng ko hợp lệ");
		else {
			System.out.print("Năm hiện tại = ");
			int year = Integer.parseInt(sc.nextLine());

			int birth = year - age;
			System.out.println("Năm sinh = " + birth);
		}
	}
}
