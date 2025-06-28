import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		float[] scoreList = new float[100];
		int currentindex = 0;
		do {
			System.out.println(
					"\n======== THỐNG KÊ ĐIỂM SINH VIÊN ========\n" +
							"1. Nhập danh sách điểm\n" +
							"2. Hiển thị danh sách điểm\n" +
							"3. Đếm số sinh viên theo loại:\n" +
							"4. Tìm và in danh sách sinh viên đạt điểm cao nhất\n" +
							"5. Sắp xếp điểm tăng dần\n" +
							"6. Thoát\n" +
							"=========================================");
			System.out.print("Chọn: ");
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
				case 1:
					// Nhập danh sách điểm
					System.out.print("Nhập n số điểm cần thêm: ");
					int n = Integer.parseInt(sc.nextLine());
					for (int i = 0; i < n; i++) {
						System.out.printf("Nhập điểm thứ %d: ", i + 1);
						scoreList[currentindex] = Float.parseFloat(sc.nextLine());
						currentindex++;
					}
					System.out.printf("Đã thêm %d điểm thành công vào danh sách. Danh sách hiện có %d điểm", n, currentindex);
					break;

				case 2:
					// Hiển thị danh sách điểm
					System.out.println("Danh sách điểm: ");
					for (int i = 0; i < currentindex; i++) {
						System.out.printf("%2d. %5.1f\n", i + 1, scoreList[i]);
					}
					break;
				case 3:
					// Đếm số sinh viên theo loại:
					int countLowScore = 0;
					int countMidScore = 0;
					int countAboveMidScore = 0;
					int countGoodScore = 0;
					int countExcellentScore = 0;
					for (int i = 0; i < currentindex; i++) {
						if (scoreList[i] < 5) {
							countLowScore++;
						} else if (scoreList[i] < 6.5) {
							countMidScore++;
						} else if (scoreList[i] < 8) {
							countAboveMidScore++;
						} else if (scoreList[i] < 9) {
							countGoodScore++;
						} else if (scoreList[i] < 10) {
							countExcellentScore++;
						}
					}
					System.out.printf("Số sinh viên loại Yếu: %d, Trung bình: %d, " +
							"Khá: %d, Giỏi: %d, Xuất sắc: %d", countLowScore, countMidScore, countAboveMidScore, countGoodScore, countExcellentScore);
					break;
				case 4:
					// Tìm và in danh sách sinh viên đạt điểm cao nhất
					float max = scoreList[0];
					for (int i = 1; i < currentindex; i++) {
						if (scoreList[i] > max) {
							max = scoreList[i];
						}
					}
					System.out.printf("Sinh viên có điểm cao nhất là %.1f điểm", max);
					break;
				case 5:
					// Sắp xếp điểm tăng dần
					for (int i = 0; i < currentindex; i++) {
						float key = scoreList[i];
						int j = i - 1;
						while (j >= 0 && scoreList[j] > key) {
							scoreList[j + 1] = scoreList[j];
							j--;
						}
						scoreList[j + 1] = key;
					}
					System.out.println("Danh sách đã sắp xếp tăng dần xong");
					break;
				case 6:
					System.exit(0);
				default:
					System.out.println("Vui lòng nhập vào số trong khoảng 1-7");
			}
		} while (true);
	}
}
