package entity;

import validation.Validation;

import java.util.Scanner;

public class Course {
    private int id;
    private String title;
    private int credits;

    public Course() {
    }

    public Course(int id, String title, int credits) {
        this.id = id;
        this.title = title;
        this.credits = credits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void inputData(Scanner scanner) {
        this.title = inputTitle(scanner);
        this.credits = inputCredits(scanner);
    }

    private String inputTitle(Scanner scanner) {
        final int MAX_LENGTH = 255;
        while (true) {
            System.out.print("Nhập tên khóa học: ");
            String input = scanner.nextLine();
            if (Validation.isValidLength(input, MAX_LENGTH)) {
                return input;
            }
            System.err.println("Tiêu đề không được để trống hoặc quá dài.");
        }
    }

    private int inputCredits(Scanner scanner) {
        while (true) {
            System.out.print("Nhập số tín chỉ: ");
            String input = scanner.nextLine();
            if (Validation.isIntegerInRange(input, 1, 100)) {
                return Integer.parseInt(input);
            }
            System.err.println("Tín chỉ không hợp lệ (1-100).");
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %d - Tên khóa học: %s - Tín chỉ: %d", id, title, credits);
    }
}
