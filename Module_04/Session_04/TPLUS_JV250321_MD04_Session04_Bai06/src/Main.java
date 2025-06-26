import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		String password = sc.nextLine();

		String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%])[a-zA-Z\\d!@#$%]{8,}$";

		if (Pattern.matches(passwordRegex, password)) {
			System.out.println("Mật khẩu hợp lệ");
		} else {
			System.out.println("Mật khẩu không hợp lệ");
		}
	}
}
