package entity;

import validation.Validation;

import java.util.Scanner;

public class Assignment {
    private int employeeId;
    private int projectId;
    private String role;

    public Assignment() {
    }

    public Assignment(int employeeId, int projectId, String role) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.role = role;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public void inputData(Scanner scanner) {
        this.employeeId = inputEmployeeId(scanner);
        this.projectId = inputProjectId(scanner);
        this.role = inputRole(scanner);
    }

    private int inputEmployeeId(Scanner scanner) {
        while (true) {
            System.out.print("Nhập ID nhân viên: ");
            String input = scanner.nextLine();
            if (Validation.isPositiveInteger(input)) {
                return Integer.parseInt(input);
            }
            System.err.println("ID không hợp lệ.");
        }
    }

    private int inputProjectId(Scanner scanner) {
        while (true) {
            System.out.print("Nhập ID dự án: ");
            String input = scanner.nextLine();
            if (Validation.isPositiveInteger(input)) {
                return Integer.parseInt(input);
            }
            System.err.println("ID không hợp lệ.");
        }
    }

    private String inputRole(Scanner scanner) {
        while (true) {
            System.out.print("Nhập vai trò: ");
            String input = scanner.nextLine();
            if (Validation.isValidLength(input, 255)) {
                return input;
            }
            System.err.println("Vai trò không hợp lệ.");
        }
    }

    @Override
    public String toString() {
        return String.format("EmployeeID: %d - ProjectID: %d - Vai trò: %s",
                employeeId, projectId, role);
    }
}
