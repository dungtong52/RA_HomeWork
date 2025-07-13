package presentation;

import business.CourseBusiness;
import business.EnrollmentBusiness;
import business.StudentBusiness;
import business.imp.CourseBusinessImp;
import business.imp.EnrollmentBusinessImp;
import business.imp.StudentBusinessImp;
import validation.Validation;

import java.util.Scanner;

public class Main {
    private final StudentBusiness studentBusiness;
    private final CourseBusiness courseBusiness;
    private final EnrollmentBusiness enrollmentBusiness;

    public Main() {
        studentBusiness = new StudentBusinessImp();
        courseBusiness = new CourseBusinessImp();
        enrollmentBusiness = new EnrollmentBusinessImp();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();

        while (true) {
            System.out.println("\n========= MENU =========");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Thêm khóa học");
            System.out.println("3. Ghi danh sinh viên");
            System.out.println("4. Cập nhật điểm sinh viên");
            System.out.println("5. Hiển thị danh sách sinh viên");
            System.out.println("6. Hiển thị danh sách khóa học");
            System.out.println("7. Hiển thị danh sách ghi danh và điểm");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn: ");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 0, 7)) {
                switch (Integer.parseInt(choice)) {
                    case 1 -> main.studentBusiness.addStudent(scanner);
                    case 2 -> main.courseBusiness.addCourse(scanner);
                    case 3 -> main.enrollmentBusiness.enrollStudent(scanner);
                    case 4 -> main.enrollmentBusiness.updateGrade(scanner);
                    case 5 -> main.studentBusiness.showAllStudents();
                    case 6 -> main.courseBusiness.showAllCourses();
                    case 7 -> main.enrollmentBusiness.showAllEnrollments();
                    case 0 -> System.exit(0);
                }
            } else {
                System.err.println("Vui lòng chọn số từ 0 - 7.");
            }

        }
    }
}
