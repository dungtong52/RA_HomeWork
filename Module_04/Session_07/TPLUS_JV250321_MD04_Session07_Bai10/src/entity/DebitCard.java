package entity;

public class DebitCard extends PaymentMethod {

    @Override
    public double processPayment() {
        return super.getAmount() + calculateFee();
    }

    @Override
    public double calculateFee() {
        return getAmount() * 0.01;
    }

    @Override
    public String processPayment(String currency) {
        return String.format("%,.1f %s", processPayment(), currency);
    }
}
