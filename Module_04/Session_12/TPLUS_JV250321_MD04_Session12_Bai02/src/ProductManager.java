import java.util.HashMap;
import java.util.Map;

public class ProductManager {
	public static void main (String[] args) {
		Map <String, Double> products = new HashMap <>();

		products.put("Laptop", 1500.0);
		products.put("Desktop", 700.0);
		products.put("Mouse", 50.0);
		products.put("Keyboard", 80.0);
		products.put("Display Card", 1000.0);

		String productNameSearch = "Laptop";
		double productPriceSearch = 1200.0;

		boolean isExistsProductName = false;
		System.out.printf("Sản phẩm có tên '%s'\n", productNameSearch);
		for (Map.Entry <String, Double> entry : products.entrySet()) {
			if (entry.getKey().equalsIgnoreCase(productNameSearch)) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
				isExistsProductName = true;
			}
		}
		if (!isExistsProductName) {
			System.out.printf("Không tồn tại sản phẩm '%s'\n", productNameSearch);
		}

		System.out.println("-----------");

		boolean isExixtsProductPrice = false;
		System.out.printf("Sản phẩm có giá: %.1f\n", productPriceSearch);
		for (Map.Entry <String, Double> entry : products.entrySet()) {
			if (entry.getValue() == productPriceSearch) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
				isExixtsProductPrice = true;
			} else {

			}
		}

		if (!isExixtsProductPrice) {
			System.out.printf("Không tồn tại sản phẩm có giá %.1f\n", productPriceSearch);
		}
	}
}
