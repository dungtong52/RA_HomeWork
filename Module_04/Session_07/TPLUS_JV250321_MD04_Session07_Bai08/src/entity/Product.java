package entity;

public class Product {
	private String name;
	private double price;

	public Product (String name, double price) {
		this.name = name;
		this.price = price;
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

	public double getDiscount () {
		return this.price * 0;
	}

	public double getFinalPrice () {
		return this.price - getDiscount();
	}

	public double getDiscount (int quantity) {
		if (quantity > 100) {
			return this.price * 0.05;
		} else {
			return getDiscount();
		}
	}

	public double getFinalPrice (int quantity) {
		return this.price - getDiscount(quantity);
	}
}
