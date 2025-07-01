package presentation;

import entity.Product;

import java.util.Scanner;

public class ProductManagement {
	public static Product[] products = new Product[100];
	public static int currentIndex = 0;

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("\n------ MENU ------");
			System.out.println("1. Add Product");
			System.out.println("2. Show All Products");
			System.out.println("3. Find Product");
			System.out.println("4. Exit");
			System.out.print("Choice: ");
			int choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
				case 1:
					addProduct(scanner);
					break;
				case 2:
					showAllProducts();
					break;
				case 3:
					searchProductByName(scanner);
					break;
				case 4:
					System.exit(0);
			}
		} while (true);
	}

	public static void addProduct (Scanner scanner) {
		System.out.println("----- Add Product -----");
		products[currentIndex] = new Product();
		products[currentIndex].inputAttributes(scanner);
		currentIndex++;
	}

	public static void showAllProducts () {
		System.out.println("----- Product List -----");
		for (int i = 0; i < currentIndex; i++) {
			products[i].displayData();
		}
	}

	public static void searchProductByName (Scanner scanner) {
		System.out.print("Enter product name to find: ");
		String nameInput = scanner.nextLine();

		boolean isExists = false;
		for (int i = 0; i < currentIndex; i++) {
			if (products[i].getName().toLowerCase().equals(nameInput.toLowerCase())) {
				products[i].displayData();
				isExists = true;
			}
		}
		if (!isExists) {
			System.out.println("Product not found");
		}
	}
}
