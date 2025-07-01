package entity;

public class CreditCard extends PaymentMethod {

    @Override
    public double processPayment() {
        return super.getAmount() + calculateFee();
    }

    @Override
    public double calculateFee() {
        return super.getAmount() * 0.02;
    }

    @Override
    public String processPayment(String currency) {
        return String.format("%,.1f %s", processPayment(), currency);
    }
}
