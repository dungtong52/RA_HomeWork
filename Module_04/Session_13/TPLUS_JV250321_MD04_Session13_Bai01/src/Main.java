import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main (String[] args) {
		List <Integer> numbers = new ArrayList <>();

		for (int i = 0; i < 10; i++) {
			numbers.add(i + 1);
		}

		System.out.print("Các số chẵn: ");
		numbers.stream()
				.filter(n -> n % 2 == 0)
				.forEach(n -> System.out.print(n + " "));

		int sum = numbers.stream()
				.reduce(0, (a, b) -> a + b);
		System.out.println("\nTổng các số trong danh sách: " + sum);
	}
}
