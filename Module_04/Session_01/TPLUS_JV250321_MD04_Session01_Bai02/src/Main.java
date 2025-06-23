import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập tên: ");
		String name = sc.nextLine();

		System.out.print("Nhập tuổi: ");
		int age = Integer.parseInt(sc.nextLine());

		System.out.print("Nhập địa chỉ: ");
		String address = sc.nextLine();

		System.out.printf("Tên tôi là %s, tôi %d tuổi, hiện tại đang học tại PTIT-HCM. Quê ở %s", name, age, address);
	}
}
