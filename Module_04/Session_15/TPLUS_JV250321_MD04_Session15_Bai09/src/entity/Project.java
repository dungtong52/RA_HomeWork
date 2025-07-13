package entity;

import validation.Validation;

import java.util.Scanner;

public class Project {
    private int id;
    private String name;
    private double budget;

    public Project() {
    }

    public Project(int id, String name, double budget) {
        this.id = id;
        this.name = name;
        this.budget = budget;
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

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void inputData(Scanner scanner) {
        this.name = inputName(scanner);
        this.budget = inputBudget(scanner);
    }

    private String inputName(Scanner scanner) {
        while (true) {
            System.out.print("Nhập tên dự án: ");
            String input = scanner.nextLine();
            if (Validation.isValidLength(input, 255)) {
                return input;
            }
            System.err.println("Tên dự án không hợp lệ.");
        }
    }

    private double inputBudget(Scanner scanner) {
        while (true) {
            System.out.print("Nhập ngân sách dự án: ");
            String input = scanner.nextLine();
            if (Validation.isValidDecimal(input, 10, 2)) {
                return Double.parseDouble(input);
            }
            System.err.println("Ngân sách không hợp lệ (decimal(10,2)).");
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %d - Tên: %s - Ngân sách: %.2f", id, name, budget);
    }
}
