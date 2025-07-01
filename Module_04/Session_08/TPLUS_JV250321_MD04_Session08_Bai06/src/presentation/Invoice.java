package presentation;

import entity.Product;

public class Invoice {
	public static Product[] products = new Product[100];
	public static int currentIndex = 0;

	public static void main (String[] args) {
		products[currentIndex++] = new Product("book", 100);
		products[currentIndex++] = new Product("bag", 200);
		products[currentIndex++] = new Product("calculation", 300);

		double total = totalAmount();
		double tax = taxAmount(total);
		double amountAfterTax = finalAmount(total, tax);

		System.out.println("------------ INVOICE ------------");
		System.out.printf("|%-15s|%15s|\n", "Name", "Price");
		System.out.println("---------------------------------");
		for (int i = 0; i < currentIndex; i++) {
			products[i].displayDataProduct();
			System.out.println("---------------------------------");
		}
	}

	public static double totalAmount () {
		double resultTotal = 0;
		for (int i = 0; i < currentIndex; i++) {
			resultTotal += products[i].getPrice();
		}
		return resultTotal;
	}

	public static double taxAmount (double amount) {
		return amount * 0.1;
	}

	public static double finalAmount (double amount, double tax) {
		return amount + tax;
	}
}
