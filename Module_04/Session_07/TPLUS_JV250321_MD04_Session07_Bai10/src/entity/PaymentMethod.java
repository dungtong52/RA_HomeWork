package entity;

public abstract class PaymentMethod {
    private double amount;

    public abstract double processPayment();
    public abstract double calculateFee();
    public abstract String processPayment(String currency);

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
