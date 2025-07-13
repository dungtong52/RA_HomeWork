package entity;

import validation.Validation;

import java.util.Scanner;

public class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee() {
    }

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    public void inputData(Scanner scanner) {
        this.name = inputName(scanner);
        this.department = inputDepartment(scanner);
        this.salary = inputSalary(scanner);
    }

    private String inputName(Scanner scanner) {
        while (true) {
            System.out.print("Nhập tên nhân viên: ");
            String input = scanner.nextLine();
            if (Validation.isValidLength(input, 255)) {
                return input;
            }
            System.err.println("Tên không hợp lệ.");
        }
    }

    private String inputDepartment(Scanner scanner) {
        while (true) {
            System.out.print("Nhập phòng ban: ");
            String input = scanner.nextLine();
            if (Validation.isValidLength(input, 255)) {
                return input;
            }
            System.err.println("Phòng ban không hợp lệ.");
        }
    }

    private double inputSalary(Scanner scanner) {
        while (true) {
            System.out.print("Nhập lương: ");
            String input = scanner.nextLine();
            if (Validation.isValidDecimal(input, 10, 2)) {
                return Double.parseDouble(input);
            }
            System.err.println("Lương không hợp lệ (decimal(10,2)).");
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %d - Tên: %s - Phòng ban: %s - Lương: %.2f",
                this.id, this.name, this.department, this.salary);
    }
}
