import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập chuỗi: ");
		String str = sc.nextLine();
		StringBuilder stringBuilder = new StringBuilder("");

		int i = 0;
		while (i < str.length()) {
			int count = 1;
			while (i + count < str.length() && str.charAt(i) == str.charAt(i + count)) {
				count++;
			}
			stringBuilder.append(str.charAt(i));
			stringBuilder.append(count);
			i += count;
		}
		System.out.println(stringBuilder);
	}
}
