import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập chuỗi từ bàn phím: ");
		String str = sc.nextLine();

		String newStr = str.replaceAll("\\d", "*");
		System.out.println(newStr);
	}
}
