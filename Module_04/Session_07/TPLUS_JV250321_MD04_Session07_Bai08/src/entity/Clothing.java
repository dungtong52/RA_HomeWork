package entity;

public class Clothing extends Product {
	public Clothing (String name, double price) {
		super(name, price);
	}

	@Override
	public double getDiscount () {
		return super.getPrice() * 0.2;
	}
}
