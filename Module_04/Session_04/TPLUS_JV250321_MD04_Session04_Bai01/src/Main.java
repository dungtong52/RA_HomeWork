import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập chuỗi: ");
		String str = sc.nextLine();
		System.out.print("Nhập từ cần tìm: ");
		String key = sc.nextLine();

		int index = str.indexOf(key);
		if(index == -1)
			System.out.printf("Không tìm thấy từ \"%s\" trong chuỗi", key);
		else
			System.out.printf("Từ \"%s\" xuất hiện tại vị trí %d trong chuỗi.", key, index);

	}
}
