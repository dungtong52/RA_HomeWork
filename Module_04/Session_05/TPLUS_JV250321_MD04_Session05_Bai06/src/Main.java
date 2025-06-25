import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập chuỗi: ");
		String str = sc.nextLine();
		System.out.print("Nhập ký tự cần kiểm tra: ");
		String key = sc.nextLine();

		int count = 0;
		for (int i = 0; i < str.length(); i++){
			if(str.charAt(i) == key.charAt(0)){
				count++;
			}
		}
		System.out.println("countCharacter = " + count);
	}
}
