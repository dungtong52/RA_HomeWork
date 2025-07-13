package entity;

import validation.Validation;

import java.util.Scanner;

public class Enrollment {
    private int studentId;
    private int courseId;
    private Double grade;

    public Enrollment() {
    }

    public Enrollment(int studentId, int courseId, Double grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public void inputEnrollInfo(Scanner scanner) {
        this.studentId = inputStudentId(scanner);
        this.courseId = inputCourseId(scanner);
    }

    public void inputGrade(Scanner scanner) {
        while (true) {
            System.out.print("Nhập điểm (0.00 - 100.00): ");
            String input = scanner.nextLine();
            if (Validation.isValidDecimal(input, 5, 2)) {
                double g = Double.parseDouble(input);
                if (g >= 0 && g <= 100) {
                    this.grade = g;
                    return;
                }
            }
            System.err.println("Điểm không hợp lệ.");
        }
    }

    private int inputStudentId(Scanner scanner) {
        while (true) {
            System.out.print("Nhập ID sinh viên: ");
            String input = scanner.nextLine();
            if (Validation.isPositiveInteger(input)) {
                return Integer.parseInt(input);
            }
            System.err.println("ID không hợp lệ.");
        }
    }

    private int inputCourseId(Scanner scanner) {
        while (true) {
            System.out.print("Nhập ID khóa học: ");
            String input = scanner.nextLine();
            if (Validation.isPositiveInteger(input)) {
                return Integer.parseInt(input);
            }
            System.err.println("ID không hợp lệ.");
        }
    }

    @Override
    public String toString() {
        return String.format("StudentID: %d - CourseID: %d - Grade: %.2f",
                studentId, courseId, grade != null ? grade : 0.0);
    }
}
