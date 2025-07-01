package entity;

public class Cash extends PaymentMethod {

    @Override
    public double processPayment() {
        return super.getAmount() + calculateFee();
    }

    @Override
    public double calculateFee() {
        return 0;
    }

    @Override
    public String processPayment(String currency) {
        return String.format("%,.1f %s", processPayment(), currency);
    }

}
