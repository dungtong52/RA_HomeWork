import java.util.HashMap;
import java.util.Map;

public class ProductManager {
	public static void main (String[] args) {
		Map <String, Double> products = new HashMap <>();

		products.put("Laptop", 1600.0);
		products.put("Desktop", 700.0);
		products.put("Mouse", 50.0);
		products.put("Keyboard", 80.0);
		products.put("Display Card", 1000.0);

		final double MIN_PRICE_SEARCH = 500.0;
		final double MAX_PRICE_SEARCH = 1500.0;

		System.out.printf("Danh sách sản phẩm có giá %.1f-%.1f:\n", MIN_PRICE_SEARCH, MAX_PRICE_SEARCH);
		for (Map.Entry <String, Double> entry : products.entrySet()) {
			String name = entry.getKey();
			Double price = entry.getValue();
			if (price >= MIN_PRICE_SEARCH && price <= MAX_PRICE_SEARCH) {
				System.out.println(name + ": " + price);
			}
		}

	}
}
