import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập vào n: ");
		int n = Integer.parseInt(sc.nextLine());

		if (n <= 0 || n > 1000) {
			System.err.println("N nằm trong giới hạn 1-1000");
		} else {
			String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
			StringBuilder strBuilder = new StringBuilder("");

			for (int i = 0; i < n; i++) {
				int inxRandom = (int)(Math.random() * str.length());
				strBuilder.append(str.charAt(inxRandom));
			}

			System.out.println(strBuilder);
		}
	}
}
