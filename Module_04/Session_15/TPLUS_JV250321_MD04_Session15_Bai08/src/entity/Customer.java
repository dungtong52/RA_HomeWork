package entity;

import validation.Validation;

import java.util.Scanner;

public class Customer {
    private int id;
    private String name;
    private String email;

    public Customer() {}

    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getter & Setter
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
            System.out.print("Nhập tên khách hàng: ");
            String input = scanner.nextLine().trim();
            if (Validation.isValidLength(input, MAX_LENGTH)) {
                return input;
            }
            System.err.println("Tên không được để trống hoặc vượt quá 255 ký tự.");
        }
    }

    private String inputEmail(Scanner scanner) {
        while (true) {
            System.out.print("Nhập email khách hàng: ");
            String input = scanner.nextLine().trim();
            if (Validation.isValidEmail(input)) {
                return input;
            }
            System.err.println("Email không hợp lệ.");
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %d - Name: %s - Email: %s", id, name, email);
    }
}
