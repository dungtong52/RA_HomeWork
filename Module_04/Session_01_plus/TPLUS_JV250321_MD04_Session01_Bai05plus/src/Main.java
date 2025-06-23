import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Điểm Toán: ");
		float mathScore = Float.parseFloat(sc.nextLine());
		System.out.print("Điểm Văn: ");
		float literatureScore = Float.parseFloat(sc.nextLine());
		System.out.print("Điểm Anh: ");
		float englishScore = Float.parseFloat(sc.nextLine());

		float totalScore = mathScore + literatureScore + englishScore;
		float scoreAvg = totalScore / 3;

		System.out.printf("Tổng điểm: %.2f\n", totalScore);
		System.out.printf("Điểm trung bình: %.2f", scoreAvg);
	}
}
