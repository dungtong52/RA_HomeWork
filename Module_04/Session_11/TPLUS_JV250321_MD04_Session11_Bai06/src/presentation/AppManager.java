package presentation;

import entity.AttendanceManager;
import entity.Student;

import java.util.Scanner;

public class AppManager {
	public static void main (String[] args) {
		AttendanceManager attendanceManager = new AttendanceManager();
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("\n************ MENU QUẢN LÝ ĐIỂM DANH **************");
			System.out.println("1. Thêm sinh viên");
			System.out.println("2. Sửa sinh viên");
			System.out.println("3. Xóa sinh viên");
			System.out.println("4. Hiển thị danh sách sinh viên");
			System.out.println("5. Thoát");
			System.out.print("Lựa chọn: ");
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
				case 1:
					Student newStudent = new Student();
					newStudent.inputData(scanner);
					attendanceManager.add(newStudent);
					break;
				case 2:
					attendanceManager.display();
					System.out.print("Nhập vào id sinh viên muốn sửa: ");
					int idUpdate = Integer.parseInt(scanner.nextLine());
					int indexUpdate = attendanceManager.findIndexById(idUpdate);

					Student updateStudent = attendanceManager.studentList.get(indexUpdate);
					updateStudent.setName(updateStudent.inputName(scanner));

					attendanceManager.update(indexUpdate, updateStudent);
					break;
				case 3:
					System.out.print("Nhập vào id sinh viên muốn xóa: ");
					int idDelete = Integer.parseInt(scanner.nextLine());
					int indexDelete = attendanceManager.findIndexById(idDelete);

					attendanceManager.delete(indexDelete);
					break;
				case 4:
					attendanceManager.display();
					break;
				case 5:
					System.exit(0);
				default:
					System.err.println("Nhập vào số 1-5");
			}
		} while (true);
	}
}
