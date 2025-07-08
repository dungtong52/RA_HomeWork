import java.util.List;

public interface IProductProcessor {
	double calculateTotalValue (List <Product> products);

	static void printProductList (List <Product> products) {
		System.out.println("Danh sách sản phẩm:");
		products.stream()
				.forEach(System.out::println);
	}

	default boolean hasExpensiveProduct (List <Product> products) {
		return products.stream().anyMatch(p -> p.getPrice() > 100);
	}
}
