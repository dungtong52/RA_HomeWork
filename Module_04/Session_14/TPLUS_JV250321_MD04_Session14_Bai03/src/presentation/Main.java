package presentation;

import business.SubjectManager;
import entity.Subject;
import validator.Validation;


import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		SubjectManager <Subject> subjectManager = new SubjectManager <>();

		do {
			System.out.println("*********** QUẢN LÝ MÔN HỌC **************");
			System.out.println("1. Thêm môn học");
			System.out.println("2. Xóa môn học");
			System.out.println("3. Hiển thị danh sách môn học");
			System.out.println("4. Tìm môn thọc theo tên");
			System.out.println("5. Lọc các môn học có số tín chỉ lớn hơn 3");
			System.out.println("6. Thoát");
			System.out.print("Lựa chọn của bạn: ");
			String choiceInput = scanner.nextLine();
			if (Validation.isIntegerInRange(choiceInput, 1, 6)) {
				int choice = Integer.parseInt(choiceInput);
				switch (choice) {
					case 1 -> subjectManager.addSubject(scanner);
					case 2 -> subjectManager.deleteSubject(scanner);
					case 3 -> subjectManager.displaySubjectList();
					case 4 -> subjectManager.findSubjectByName(scanner);
					case 5 -> subjectManager.filterSubjectByCredit();
					default -> System.exit(0);
				}
			} else {
				System.err.println("Vui lòng nhập vào số nguyên từ 1-6");
			}
		} while (true);
	}
}
