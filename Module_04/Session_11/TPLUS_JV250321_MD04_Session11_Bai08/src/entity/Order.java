package entity;

import validator.Validation;

import java.util.Scanner;

public class Order {
	private String orderId;
	private String customerName;

	public Order () {
	}

	public Order (String orderId, String customerName) {
		this.orderId = orderId;
		this.customerName = customerName;
	}

	public String getOrderId () {
		return orderId;
	}

	public void setOrderId (String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName () {
		return customerName;
	}

	public void setCustomerName (String customerName) {
		this.customerName = customerName;
	}

	public void inputData (Scanner scanner) {
		this.orderId = inputId(scanner);
		this.customerName = inputCustomerName(scanner);
	}

	public String inputId (Scanner scanner) {
		System.out.print("Nhập mã đơn hàng: ");
		do {
			String orderId = scanner.nextLine();
			if (Validation.isEmpty(orderId)) {
				System.err.println("Vui lòng không để trống!");
			} else {
				return orderId;
			}
		} while (true);
	}

	public String inputCustomerName (Scanner scanner) {
		System.out.print("Nhập tên khách hàng: ");
		do {
			String customerName = scanner.nextLine();
			if (Validation.isEmpty(customerName)) {
				System.err.println("Vui lòng không để trống!");
			} else {
				return customerName;
			}
		} while (true);
	}

	public void displayData () {
		System.out.printf("Order ID: %s - Customer Name: %s\n", this.orderId, this.customerName);
	}
}
