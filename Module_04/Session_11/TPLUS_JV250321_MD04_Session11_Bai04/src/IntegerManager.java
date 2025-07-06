import java.util.HashSet;
import java.util.Set;

public class IntegerManager {
	public static void main (String[] args) {
		Set <Integer> integerSet = new HashSet <>();

		for (int i = 1; i <= 5; i++) {
			integerSet.add(i * 10);
		}

		System.out.println(integerSet);
	}
}
