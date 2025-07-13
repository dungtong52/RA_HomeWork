package entity;

import validation.Validation;

import java.util.Scanner;

public class Product {
    private int id;
    private String name;
    private double price;

    public Product() {}

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void inputData(Scanner scanner) {
        this.name = inputName(scanner);
        this.price = inputPrice(scanner);
    }

    private String inputName(Scanner scanner) {
        final int MAX_LENGTH = 255;
        while (true) {
            System.out.print("Nhập tên sản phẩm: ");
            String input = scanner.nextLine().trim();
            if (Validation.isValidLength(input, MAX_LENGTH)) {
                return input;
            }
            System.err.println("Tên không được để trống hoặc vượt quá 255 ký tự.");
        }
    }

    private double inputPrice(Scanner scanner) {
        while (true) {
            System.out.print("Nhập giá sản phẩm: ");
            String input = scanner.nextLine().trim();
            if (Validation.isValidDecimal(input, 10, 2)) {
                return Double.parseDouble(input);
            }
            System.err.println("Giá không hợp lệ. Vui lòng nhập số thập phân (tối đa 10 chữ số, 2 chữ số sau dấu phẩy).");
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %d - Name: %s - Price: %.2f", id, name, price);
    }
}
