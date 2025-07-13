package entity;

import validation.Validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Order {
    private int id;
    private int customerId;
    private LocalDate orderDate;
    private double totalAmount;

    public Order() {
    }

    public Order(int id, int customerId, LocalDate orderDate, double totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public Order(int customerId, LocalDate orderDate, double totalAmount) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void inputData(Scanner scanner) {
        this.customerId = inputCustomerId(scanner);
        this.orderDate = inputOrderDate(scanner);
        this.totalAmount = inputTotalAmount(scanner);
    }

    private int inputCustomerId(Scanner scanner) {
        while (true) {
            System.out.print("Nhập ID khách hàng: ");
            String input = scanner.nextLine();
            if (Validation.isPositiveInteger(input)) {
                return Integer.parseInt(input);
            }
            System.err.println("ID phải là số nguyên dương.");
        }
    }

    private LocalDate inputOrderDate(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.print("Nhập ngày đặt hàng (yyyy-MM-dd): ");
            String input = scanner.nextLine();
            if (Validation.isValidDate(input, formatter)) {
                return LocalDate.parse(input, formatter);
            }
            System.err.println("Ngày không hợp lệ. Định dạng đúng: yyyy-MM-dd.");
        }
    }

    private double inputTotalAmount(Scanner scanner) {
        while (true) {
            System.out.print("Nhập tổng tiền đơn hàng (decimal 10,2): ");
            String input = scanner.nextLine();
            if (Validation.isValidDecimal(input, 10, 2)) {
                return Double.parseDouble(input);
            }
            System.err.println("Giá trị không hợp lệ. Nhập lại!");
        }
    }

    @Override
    public String toString() {
        return String.format("OrderID: %d - CustomerID: %d - Date: %s - Total: %.2f",
                id, customerId, orderDate.toString(), totalAmount);
    }
}
