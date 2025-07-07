package entity;

import validator.Validation;

import java.util.Scanner;

public class Order {
    private String orderId;
    private String customerName;

    public Order() {
    }

    public Order(String orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void inputData(Scanner scanner) {
        this.orderId = inputId(scanner);
        this.customerName = inputCustomerName(scanner);
    }

    public String inputId(Scanner scanner) {
        System.out.print("Nhập mã đơn hàng: ");
        do {
            if (Validation.isEmpty(scanner.nextLine())) {
                System.err.println("Vui lòng không để trống!");
            } else {
                return scanner.nextLine();
            }
        } while (true);
    }

    public String inputCustomerName(Scanner scanner) {
        System.out.print("Nhập tên khách hàng: ");
        do {
            if (Validation.isEmpty(scanner.nextLine())) {
                System.err.println("Vui lòng không để trống!");
            } else {
                return scanner.nextLine();
            }
        } while (true);
    }
}
