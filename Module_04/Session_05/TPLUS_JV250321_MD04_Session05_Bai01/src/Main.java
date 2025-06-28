import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str = new String();
		String strReverse = new String();
		do {
			System.out.println("\n***************************** MENU *****************************");
			System.out.println("1. Nhập chuỗi");
			System.out.println("2. Đếm số ký tự thường, hoa, số, đặc biệt");
			System.out.println("3. Đảo ngược chuỗi");
			System.out.println("4. Kiểm tra Palindrome");
			System.out.println("5. Chuẩn hóa chuỗi (xóa khoảng trắng dư thừa, viết hoa chữ cái đầu)");
			System.out.println("6. Thoát");
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
				case 1:
					// Nhập chuỗi
					System.out.println("Hãy nhập chuỗi: ");
					str = scanner.nextLine();
					System.out.println("Chuỗi được thêm thành công!");
					break;
				case 2:
					// Đếm số ký tự thường, hoa, số, đặc biệt
					String lowerCase = "[a-z]";
					String upperCase = "[A-Z]";
					String numberChar = "[0-9]";
					String specialChar = "[^a-zA-Z0-9\\s]";
					int countLowerCase = 0;
					int countUpperCase = 0;
					int countNumberChar = 0;
					int countSpecialChar = 0;

					for (int i = 0; i < str.length(); i++) {
						String charNeedCount = str.charAt(i) + "";          //Có thể dùng valueOf
						if (Pattern.matches(lowerCase, charNeedCount)) {
							countLowerCase++;
						} else if (Pattern.matches(upperCase, charNeedCount)) {
							countUpperCase++;
						} else if (Pattern.matches(numberChar, charNeedCount)) {
							countNumberChar++;
						} else if (Pattern.matches(specialChar, charNeedCount)) {
							countSpecialChar++;
						}
					}

					// In ra số ký tự mỗi loại
					System.out.printf("Trong chuỗi đã nhập có %d ký tự thường, %d ký tự cữ hoa, %d ký tự chữ số và %d ký tự đặc biệt", countLowerCase, countUpperCase, countNumberChar, countSpecialChar);
					break;
				case 3:
					// Đảo ngược chuỗi
					StringBuilder stringBuilder = new StringBuilder(str);
					strReverse = stringBuilder.reverse().toString();
					System.out.println("Chuỗi ban đầu: " + str);
					System.out.println("Chuỗi sau khi đảo ngược: " + strReverse);
					break;
				case 4:
					// Kiểm tra Palindrome
					if (strReverse.equals(str)) {
						System.out.printf("Chuỗi %s là một chuỗi Palindrome", str);
					} else {
						System.out.printf("Chuỗi %s là KHÔNG PHẢI một chuỗi Palindrome", str);
					}
					break;
				case 5:
					// Chuẩn hóa chuỗi (xóa khoảng trắng dư thừa, viết hoa chữ cái đầu)
					String strSort = str.trim().replaceAll("\\s+", " ");
					String[] strArr = strSort.split(" ");
					StringBuilder strStandard = new StringBuilder();
					for (int i = 0; i < strArr.length; i++) {
						strStandard.append(Character.toUpperCase(strArr[i].charAt(0)))
								.append(strArr[i].substring(1).toLowerCase())
								.append(" ");
					}
					System.out.println("Chuỗi sau khi chuẩn hóa: " + strStandard.toString().trim());
					break;
				case 6:
					System.exit(0);
				default:
					System.err.println("Hãy nhập các số từ 1-6");
			}
		} while (true);
	}
}
