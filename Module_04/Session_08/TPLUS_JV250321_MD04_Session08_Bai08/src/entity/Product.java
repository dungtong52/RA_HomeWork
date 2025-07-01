package entity;

import java.util.Scanner;

public class Product {
	private String name;
	private double price;
	private int quantity;

	public Product () {
	}

	public Product (String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public double getPrice () {
		return price;
	}

	public void setPrice (double price) {
		this.price = price;
	}

	public int getQuantity () {
		return quantity;
	}

	public void setQuantity (int quantity) {
		this.quantity = quantity;
	}

	public void inputAttributes (Scanner scanner) {
		System.out.print("Enter product's name: ");
		String nameInput = scanner.nextLine();
		setName(nameInput);

		System.out.print("Enter product's price: ");
		double priceInput = Double.parseDouble(scanner.nextLine());
		setPrice(priceInput);

		System.out.print("Enter product's quantity: ");
		int quantityInput = Integer.parseInt(scanner.nextLine());
		setQuantity(quantityInput);
	}

	public void displayData () {
		System.out.printf("|%-15s|%,10.1f|%10d|\n",
				getName(), getPrice(), getQuantity());
	}
}
