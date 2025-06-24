import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập vào chuỗi: ");
		String str = sc.nextLine().trim();

		Pattern pattern = Pattern.compile("^[\\w.]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,6}$");
		Matcher matcher = pattern.matcher(str);

		if(matcher.find()){
			System.out.println("Email hợp lệ");
		} else
			System.out.println("Email không hợp lệ");
	}
}
