import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main (String[] args) {
		ProductProcessorImpl productProcessor = new ProductProcessorImpl();
		List <Product> productList = new ArrayList <>();
		productList.add(new Product("Keyboard", 45.0));
		productList.add(new Product("Desktop", 120.0));
		productList.add(new Product("Cable USB", 10.0));

		// In danh sach san pham
		IProductProcessor.printProductList(productList);

		// Kiem tra co san pham nao > 100 khong
		boolean hasExpensive = productProcessor.hasExpensiveProduct(productList);
		if (hasExpensive) {
			System.out.println("Sản phẩm có giá > 100:");
			productList.stream()
					.filter(p -> p.getPrice() > 100)
					.forEach(System.out::println);
		} else {
			System.out.println("Không có sản phẩm đắt tiền");
		}

		// Tinh tong
		double total = productProcessor.calculateTotalValue(productList);
		System.out.println("Tổng giá trị các sản phẩm: " + total);
	}
}
