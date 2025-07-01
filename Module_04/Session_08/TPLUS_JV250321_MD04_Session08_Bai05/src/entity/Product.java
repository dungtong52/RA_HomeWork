package entity;

import java.util.Scanner;

public class Product {
	private int id;
	private String name;
	private double price;

	public Product () {
	}

	public Product (int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public int getId () {
		return id;
	}

	public void setId (int id) {
		this.id = id;
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

	public void inputAttributes (Scanner scanner) {
		System.out.print("Enter product ID: ");
		int idInput = Integer.parseInt(scanner.nextLine());
		setId(idInput);

		System.out.print("Enter product name: ");
		String nameInput = scanner.nextLine();
		setName(nameInput);

		System.out.print("Enter product price: ");
		double priceInput = Double.parseDouble(scanner.nextLine());
		setPrice(priceInput);
	}

	public void displayData () {
		System.out.printf("ID: %d - Name: %s - Price: %,.1f\n",
				getId(), getName(), getPrice());
	}
}
