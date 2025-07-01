package entity;

public abstract class Car {
	private String model;
	private int year;
	private double price;

	public String getModel () {
		return model;
	}

	public void setModel (String model) {
		this.model = model;
	}

	public int getYear () {
		return year;
	}

	public void setYear (int year) {
		this.year = year;
	}

	public double getPrice () {
		return price;
	}

	public void setPrice (double price) {
		this.price = price;
	}

	public abstract String start();
	public abstract String stop();

}
