package presenttation;

import entity.Customer;

public class PromotionChecker {
	public static void main (String[] args) {
		Customer customer1 = new Customer("Dũng", 1, 1000, 0);
		Customer customer2 = new Customer("Hùng", 2, 0, 0);
		Customer customer3 = new Customer("Tiến", 1, 0, 0);
		Customer customer4 = new Customer("Lợi", 1, 1000, 1000000);

		customer1.displayCustomerPromotion();
		customer2.displayCustomerPromotion();
		customer3.displayCustomerPromotion();
		customer4.displayCustomerPromotion();
	}
}
