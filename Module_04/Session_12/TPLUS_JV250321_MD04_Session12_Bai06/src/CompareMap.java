import java.util.HashMap;
import java.util.Map;

public class CompareMap {
	public static void main (String[] args) {
		Map <String, Double> studentMap1 = new HashMap <>();
		Map <String, Double> studentMap2 = new HashMap <>();
		Map <String, Double> studentMap3 = new HashMap <>();

		studentMap1.put("Nguyễn Văn A", 7.5);
		studentMap1.put("Trần Thị B", 8.0);
		studentMap1.put("Lê Văn C", 6.5);

		studentMap2.put("Nguyễn Văn A", 7.5);
		studentMap2.put("Lê Văn C", 6.5);
		studentMap2.put("Trần Thị B", 8.0);

		studentMap3.put("Nguyễn Văn A", 8.1);
		studentMap3.put("Lê Văn C", 6.9);
		studentMap3.put("Trần Thị B", 8.0);

		if (studentMap1.equals(studentMap2)) {
			System.out.println("Hai Map 1 và 2 giống nhau");
		} else {
			System.out.println("Hai Map 1 và 2 không giống nhau");
		}

		if (studentMap1.equals(studentMap3)) {
			System.out.println("Hai Map 1 và 3 giống nhau");
		} else {
			System.out.println("Hai Map 1 và 3 không giống nhau");
		}
	}
}
