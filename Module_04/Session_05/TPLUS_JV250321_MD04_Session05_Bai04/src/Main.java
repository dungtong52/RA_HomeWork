import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] strList = new String[100];
		int currentIndex = 0;
		do {
			System.out.println(
					"\n========= XỬ LÝ DANH SÁCH CHUỖI =========\n" +
							"1. Nhập danh sách chuỗi\n" +
							"2. Hiển thị danh sách\n" +
							"3. Tìm và in ra chuỗi có độ dài lớn nhất\n" +
							"4. Đếm chuỗi có chứa ký tự số\n" +
							"5. Tìm và in ra chuỗi Palindrome (đọc xuôi ngược giống nhau)\n" +
							"6. Sắp xếp chuỗi theo độ dài tăng dần\n" +
							"7. Thoát\n" +
							"========================================"
			);
			System.out.print("Chọn: ");
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
				case 1:
					// Nhập danh sách chuỗi
					System.out.print("Nhập số lượng chuỗi mới muốn thêm vào: ");
					int n = Integer.parseInt(sc.nextLine());
					for (int i = 0; i < n; i++) {
						System.out.printf("Nhập vào chuỗi thứ %d: ", i + 1);
						strList[currentIndex] = sc.nextLine();
						currentIndex++;
					}
					System.out.printf("Đã thêm thành công. Danh sách hiện có %d chuỗi", currentIndex);
					break;

				case 2:
					// Hiển thị danh sách
					System.out.println("Danh sách chuỗi: ");
					for (int i = 0; i < currentIndex; i++) {
						System.out.printf("%2d. %s\n", i + 1, strList[i]);
					}
					break;

				case 3:
					// Tìm và in ra chuỗi có độ dài lớn nhất
					String maxLengthStr = strList[0];
					for (int i = 1; i < currentIndex; i++) {
						if (strList[i].length() > maxLengthStr.length()) {
							maxLengthStr = strList[i];
						}
					}
					System.out.println("Chuỗi có độ dài lớn nhất: " + maxLengthStr);
					break;

				case 4:
					// Đếm chuỗi có chứa ký tự số
					int countStrHasNumber = 0;
					String strRegexForNumber = "(?=.*[0-9]).+";
					for (int i = 1; i < currentIndex; i++) {
						if (Pattern.matches(strRegexForNumber, strList[i])) {
							countStrHasNumber++;
						}
					}
					// Có thể dùng với matcher
					// Pattern pattern = Pattern.compile("[0-9]");
					// Matcher matcher = pattern.matcher(strList[i]);
					// while(matcher.find()){
					//  countStrHasNumber++;}

					System.out.printf("Có %d chuỗi có chứa ký tự số", countStrHasNumber);
					break;

				case 5:
					// Tìm và in ra chuỗi Palindrome
					System.out.println("Danh sách chuỗi Palindrome:");
					for (int i = 1; i < currentIndex; i++) {
						StringBuilder sb = new StringBuilder(strList[i]);
						sb = sb.reverse();
						if (strList[i].equals(sb.toString())) {
							System.out.printf("%2d. %s\n", i + 1, strList[i]);
						}
					}
					break;
				case 6:
					// Sắp xếp chuỗi theo độ dài tăng dần
					for (int i = 0; i < currentIndex - 1; i++) {
						int minIndex = i;
						for (int j = i + 1; j < currentIndex; j++) {
							if (strList[minIndex].length() > strList[j].length()) {
								minIndex = j;
							}
						}
						if (minIndex != i) {
							String temp = strList[minIndex];
							strList[minIndex] = strList[i];
							strList[i] = temp;
						}
					}
					System.out.println("Danh sách đã được sắp xếp theo độ dài chuỗi tăng dần");
					break;
				case 7:
					System.exit(0);
				default:
					System.out.println("Nhập vào số trong khoảng 1-7");
			}
		} while (true);
	}
}
