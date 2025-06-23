import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập vào năm sinh: ");
		int birth = Integer.parseInt(sc.nextLine());

		int currentYear = 2025;
		int age = currentYear - birth;
		if(age % 2 == 0){
			System.out.printf("Năm sinh: %d, năm nay %d tuổi, là số chẵn", birth, age);
		} else
			System.out.printf("Năm sinh: %d, năm nay %d tuổi, là số lẻ", birth, age);
	}
}
