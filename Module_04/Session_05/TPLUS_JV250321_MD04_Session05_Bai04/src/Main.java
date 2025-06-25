import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập vào chuỗi");
		String str = sc.nextLine();

		str = str.trim().replaceAll("[\s]+", " ");
		System.out.println("Chuỗi sau khi bỏ khoảng trắng không cần thiết: " + str);
	}
}
