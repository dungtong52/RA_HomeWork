package presentation;

import entity.Cash;
import entity.CreditCard;
import entity.DebitCard;
import entity.PaymentMethod;

public class Main {
    public static void main(String[] args) {
        CreditCard creditCard = new CreditCard();
        creditCard.setAmount(1000);
        System.out.printf("Fee: %,.1f\n", creditCard.calculateFee());
        System.out.printf("Total payment: %,.1f\n", creditCard.processPayment());
        System.out.println("Payment with currency: " + creditCard.processPayment("VND"));

        DebitCard debitCard = new DebitCard();
        debitCard.setAmount(1000);
        System.out.printf("Fee: %,.1f\n", debitCard.calculateFee());
        System.out.printf("Total payment: %,.1f\n", debitCard.processPayment());
        System.out.println("Payment with currency: " + debitCard.processPayment("USD"));

        Cash cash = new Cash();
        cash.setAmount(1000);
        System.out.printf("Fee: %,.1f\n", cash.calculateFee());
        System.out.printf("Total payment: %,.1f\n", cash.processPayment());
        System.out.println("Payment with currency: " + cash.processPayment("EUR"));
    }
}
