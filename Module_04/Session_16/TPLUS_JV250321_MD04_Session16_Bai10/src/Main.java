import business.CourseBusiness;
import business.EnrollmentBusiness;
import business.StudentBusiness;
import business.imp.CourseBusinessImp;
import business.imp.EnrollmentBusinessImp;
import business.imp.StudentBusinessImp;
import entity.Course;
import entity.Enrollment;
import entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final StudentBusiness studentBusiness = new StudentBusinessImp();
    private static final CourseBusiness courseBusiness = new CourseBusinessImp();
    private static final EnrollmentBusiness enrollmentBusiness = new EnrollmentBusinessImp();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Thêm sinh viên mới");
            System.out.println("2. Hiển thị danh sách sinh viên");
            System.out.println("3. Thêm khóa học mới");
            System.out.println("4. Cập nhật điểm cho sinh viên");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addStudentMenu(scanner);
                    break;
                case 2:
                    listStudentsMenu(scanner);
                    break;
                case 3:
                    addCourseMenu(scanner);
                    break;
                case 4:
                    updateGradesMenu(scanner);
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (true);
    }

    private static void addStudentMenu(Scanner scanner) {
        System.out.println("\n-- Thêm sinh viên --");
        System.out.print("Tên sinh viên: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Student student = new Student(0, name, email);
        boolean success = studentBusiness.addStudent(student);

        System.out.println(success ? "Thêm thành công!" : "Email đã tồn tại hoặc lỗi khi thêm.");
    }

    private static void listStudentsMenu(Scanner scanner) {
        System.out.println("Danh sách sinh viên:");
        List<Student> students = studentBusiness.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("Không có sinh viên nào.");
        } else {
            students.forEach(System.out::println);
        }
    }

    private static void addCourseMenu(Scanner scanner) {
        System.out.print("Tiêu đề khóa học: ");
        String title = scanner.nextLine();

        Course course = new Course(0, title);
        boolean success = courseBusiness.addCourse(course);

        System.out.println(success ? "Thêm thành công!" : "Khóa học đã tồn tại hoặc lỗi khi thêm.");
    }

    private static void updateGradesMenu(Scanner scanner) {
        System.out.println("Cập nhật điểm sinh viên");
        System.out.print("ID sinh viên: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập số lượng môn cần cập nhật điểm: ");
        int n = Integer.parseInt(scanner.nextLine());

        List<Enrollment> enrollments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Môn #" + (i + 1));
            System.out.print("ID khóa học: ");
            int courseId = Integer.parseInt(scanner.nextLine());
            System.out.print("Điểm: ");
            double grade = Double.parseDouble(scanner.nextLine());

            enrollments.add(new Enrollment(studentId, courseId, grade));
        }

        boolean success = enrollmentBusiness.updateEnrollment(studentId, enrollments);
        System.out.println(success ? "Cập nhật điểm thành công!" : "Lỗi khi cập nhật điểm.");
    }
}

