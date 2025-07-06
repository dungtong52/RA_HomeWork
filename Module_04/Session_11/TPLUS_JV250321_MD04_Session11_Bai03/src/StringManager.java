import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class StringManager {
	public static void main (String[] args) {
		Set <String> stringSet = new HashSet <>();
		Scanner scanner = new Scanner(System.in);

		stringSet.add("Java");
		stringSet.add("Python");
		stringSet.add("C++");
		stringSet.add("JavaScript");

		System.out.print("Nhập vào chuỗi để kiểm tra tồn tại: ");
		String stringSearch = scanner.nextLine();

		if (stringSet.contains(stringSearch)) {
			System.out.println("Chuỗi có tồn tại");
		} else {
			System.out.println("Chuỗi không tồn tại!");
		}
	}
}
