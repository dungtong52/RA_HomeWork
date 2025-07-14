import business.StudentBusiness;
import business.imp.StudentBusinessImp;

import java.util.Scanner;

public class Main {
    private final StudentBusiness studentBusiness;

    public Main() {
        studentBusiness = new StudentBusinessImp();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();

        while (true) {
            System.out.println("************* MENU *************");
            System.out.println("1. Thêm mới danh sách sinh viên");
            System.out.println("2. Cập nhật sinh viên theo id");
            System.out.println("3. Xóa sinh viên theo độ tuổi");
            System.out.println("4. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    main.studentBusiness.createMultipleStudent();
                    break;
                case 2:
                    main.studentBusiness.updateStudent(scanner);
                    break;
                case 3:
                    main.studentBusiness.deleteStudent(scanner);
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.err.println("Nhập số nguyên từ 1-4");
            }
        }


    }
}
