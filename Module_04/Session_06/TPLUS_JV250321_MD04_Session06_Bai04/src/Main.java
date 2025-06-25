import java.util.Scanner;

public class Main {
	public static void main (String[] args) {

		// Tạo mảng có sẵn các sinh viên
		Student[] students = new Student[5];
		students[0] = new Student("Bob", 21, "A1", 7.5);
		students[1] = new Student("John", 21, "B1", 6.8);
		students[2] = new Student("Stark", 19, "A2", 8.2);
		students[3] = new Student("Charlie", 22, "C1", 5.9);
		students[4]  = new Student("Eve", 20, "A1", 7.1);

		// Nhập điểm min và max từ người dùng
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập điểm min: ");
		double min = Double.parseDouble(sc.nextLine());
		System.out.print("Nhập điểm max: ");
		double max = Double.parseDouble(sc.nextLine());

		// Hiển thị sinh viên có avgScore trong khoảng min - max
		boolean found = false;
		System.out.printf("Sinh viên có điểm trung bình từ %.1f đến %.1f:\n", min, max);
		for (int i = 0 ; i < students.length; i++){
			double avg = students[i].getAvgScore();
			if(avg >= min && avg <= max){
				students[i].display();
				found = true;
			}
		}
		if (!found){
			System.out.printf("Không có sinh viên nào có điểm trung bình nằm trong khoảng %.1f - %.1f", min, max);
		}
	}
}
