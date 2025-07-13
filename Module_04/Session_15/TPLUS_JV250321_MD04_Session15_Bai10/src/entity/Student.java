package entity;

import validation.Validation;

import java.util.Scanner;

public class Student {
    private int id;
    private String name;
    private String email;

    public Student() {
    }

    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void inputData(Scanner scanner) {
        this.name = inputName(scanner);
        this.email = inputEmail(scanner);
    }

    private String inputName(Scanner scanner) {
        final int MAX_LENGTH = 255;
        while (true) {
            System.out.print("Nhập tên sinh viên: ");
            String input = scanner.nextLine();
            if (Validation.isValidLength(input, MAX_LENGTH)) {
                return input;
            }
            System.err.println("Tên không được để trống hoặc quá dài.");
        }
    }

    private String inputEmail(Scanner scanner) {
        final int MAX_LENGTH = 255;
        while (true) {
            System.out.print("Nhập email sinh viên: ");
            String input = scanner.nextLine();
            if (Validation.isValidEmail(input) && Validation.isValidLength(input, MAX_LENGTH)) {
                return input;
            }
            System.err.println("Email không hợp lệ hoặc quá dài.");
        }
    }
    @Override
    public String toString() {
        return String.format("ID: %d - Tên: %s - Email: %s", id, name, email);
    }
}
