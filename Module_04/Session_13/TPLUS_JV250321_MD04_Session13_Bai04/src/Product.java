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

	@Override
	public String toString () {
		return String.format("ID: %d - Name: %s - Price: %.1f", this.id, this.name, this.price);
	}

	public void inputData (Scanner scanner) {
		this.id = inputId(scanner);
		this.name = inputName(scanner);
		this.price = inputPrice(scanner);
	}

	public int inputId (Scanner scanner) {
		System.out.print("Enter Product ID: ");
		return Integer.parseInt(scanner.nextLine());
	}

	public String inputName (Scanner scanner) {
		System.out.print("Enter Product Name: ");
		return scanner.nextLine();
	}

	public double inputPrice (Scanner scanner) {
		System.out.print("Enter Product Price: ");
		return Double.parseDouble(scanner.nextLine());
	}
}
