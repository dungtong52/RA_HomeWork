package entity;

public class Electronics extends Product {
	public Electronics (String name, double price) {
		super(name, price);
	}

	@Override
	public double getDiscount () {
		return super.getPrice() * 0.1;
	}
}
