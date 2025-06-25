import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập chuỗi: ");
		String str = sc.nextLine();

		Pattern pattern = Pattern.compile("^[\\w.]+@[\\w.]+\\.[\\w]{2,6}$");
		Matcher matcher = pattern.matcher(str);

		if(matcher.find()){
			System.out.printf("%s hợp lệ!!!", str);
		} else {
			System.out.printf("%s không hợp lệ!!!", str);
		}
	}
}
