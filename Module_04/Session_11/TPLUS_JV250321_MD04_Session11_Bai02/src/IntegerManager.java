import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IntegerManager {
	public static void main (String[] args) {
		List <Integer> integerList = new ArrayList <>();

		for (int i = 0; i < 10; i++) {
			integerList.add(i);
		}

		System.out.println("--- Danh sách trước khi xóa ---");
		for (int number : integerList) {
			System.out.print(number + " ");
		}

		Iterator <Integer> iterator = integerList.iterator();
		while (iterator.hasNext()) {
			if (iterator.next() % 3 == 0) {
				iterator.remove();
			}
		}

		System.out.println("\n--- Danh sách sau khi xóa ---");
		for (int number : integerList) {
			System.out.print(number + " ");
		}
	}
}
