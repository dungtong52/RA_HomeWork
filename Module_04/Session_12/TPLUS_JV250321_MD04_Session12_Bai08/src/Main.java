import java.util.*;

public class Main {
	public static void main (String[] args) {
		Map <String, Double> productList = new HashMap <>();

		productList.put("Bánh mì", 15000.0);
		productList.put("Sữa", 25000.0);
		productList.put("Trứng", 18000.0);
		productList.put("Gạo", 12000.0);
		productList.put("Nước ngọt", 10000.0);

		Set <Map.Entry <String, Double>> entrySet = productList.entrySet();
		List <Map.Entry <String, Double>> entryList = new ArrayList <>(entrySet);

		entryList.sort(new Comparator <Map.Entry <String, Double>>() {
			@Override
			public int compare (Map.Entry <String, Double> o1, Map.Entry <String, Double> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});

		System.out.println("Danh sách sản phẩm sau sắp xếp tăng dần:");
		for (Map.Entry <String, Double> entry : entryList) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
}
