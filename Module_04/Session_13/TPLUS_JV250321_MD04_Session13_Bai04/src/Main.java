import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Map <Integer, Product> productMap = new HashMap <>();
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("----- Product Management System -----");
			System.out.println("1. Add Product");
			System.out.println("2. Edit Product");
			System.out.println("3. Delete Product");
			System.out.println("4. Display Product");
			System.out.println("5. Filter Products (Price > 100)");
			System.out.println("6. Total Value of Products");
			System.out.println("0. Exit");
			System.out.print("Enter your choice: ");
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
				case 1:
					Product newProduct = new Product();
					newProduct.inputData(scanner);
					productMap.put(newProduct.getId(), newProduct);
					System.out.println("Product added successfully.");
					break;
				case 2:
					System.out.print("Enter Product ID to edit: ");
					int idUpdate = Integer.parseInt(scanner.nextLine());
					productMap.entrySet().stream()
							.filter(entry -> entry.getKey() == idUpdate)
							.findFirst()
							.ifPresent(entry -> {
								Product prod = entry.getValue();
								prod.inputName(scanner);
								prod.inputPrice(scanner);
								System.out.println("Product updated successfully");
							});
					break;
				case 3:
					System.out.print("Enter Product ID to edit: ");
					int idDelete = Integer.parseInt(scanner.nextLine());
					Product removeProduct = productMap.remove(idDelete);
					if (removeProduct != null) {
						System.out.println("Product deleted successfully");
					} else {
						System.out.println("Product does not exist");
					}
					break;
				case 4:
					System.out.println("Products List:");
					productMap.values().stream()
							.forEach(System.out::println);
					break;
				case 5:
					System.out.println("Products with price greater than 100:");
					productMap.entrySet().stream()
							.filter(entry -> entry.getValue().getPrice() > 100)
							.forEach(System.out::println);
					break;
				case 6:

					double total = productMap.values().stream()
							.mapToDouble(Product::getPrice)
							.sum();
					System.out.printf("Total Value of Products: %.1f\n", total);
					break;
				case 0:
					System.exit(0);
				default:
					System.err.println("Enter number between 0-6");
			}
		} while (true);
	}
}
