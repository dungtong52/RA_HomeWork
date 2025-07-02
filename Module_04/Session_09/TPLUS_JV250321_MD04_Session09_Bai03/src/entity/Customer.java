package entity;

public class Customer {
	private String customerName;
	private int customerType;
	private int loyaltyPoints;
	private double debit;

	public Customer () {
	}

	public Customer (String customerName, int customerType, int loyaltyPoints, double debit) {
		this.customerName = customerName;
		this.customerType = customerType;
		this.loyaltyPoints = loyaltyPoints;
		this.debit = debit;
	}

	public String getCustomerName () {
		return customerName;
	}

	public void setCustomerName (String customerName) {
		this.customerName = customerName;
	}

	public int getCustomerType () {
		return customerType;
	}

	public void setCustomerType (int customerType) {
		this.customerType = customerType;
	}

	public int getLoyaltyPoints () {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints (int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	public double getDebit () {
		return debit;
	}

	public void setDebit (double debit) {
		this.debit = debit;
	}

	public boolean isVIP () {
		final int CUSTOMER_VIP_TYPE = 1;
		return this.customerType == CUSTOMER_VIP_TYPE;
	}

	public void displayCustomerPromotion () {
		if (isVIP() && this.loyaltyPoints > 0 && this.debit == 0) {
			System.out.printf("Khách %s là VIP và đủ điều kiện nhận khuyến mãi\n", this.customerName);
		} else {
			System.out.printf("Khách %s là khách thường hoặc không đủ điều kiện\n", this.customerName);
		}
	}
}
