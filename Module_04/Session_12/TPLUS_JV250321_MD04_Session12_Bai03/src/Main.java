import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main (String[] args) {
		Map <String, Double> products = new HashMap <>();

		products.put("Laptop", 1500.0);
		products.put("Desktop", 700.0);
		products.put("Mouse", 50.0);
		products.put("Keyboard", 80.0);
		products.put("Display Card", 1000.0);

		double maxPrice = -1;
		String maxProductName = null;
		for (Map.Entry <String, Double> entry : products.entrySet()) {
			if (entry.getValue() > maxPrice) {
				maxPrice = entry.getValue();
				maxProductName = entry.getKey();
			}
		}
		if (maxPrice != -1) {
			System.out.printf("Sản phẩm có giá cao nhất:\n%s - %.1f$\n", maxProductName, maxPrice);
		} else {
			System.out.println("Không có sản phẩm nào");
		}
	}
}
