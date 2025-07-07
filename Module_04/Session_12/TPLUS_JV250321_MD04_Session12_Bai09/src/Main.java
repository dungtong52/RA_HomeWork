import java.util.*;

public class Main {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Nhập vào 1 chuỗi: ");
		String inputStr = scanner.nextLine();

		Map <Character, Integer> timesChar = new HashMap <>();

		for (int i = 0; i < inputStr.length(); i++) {
			char character = inputStr.charAt(i);
			if (timesChar.containsKey(character)) {
				timesChar.put(character, timesChar.get(character) + 1);
			} else {
				timesChar.put(character, 1);
			}
		}

		// Sắp xếp giảm dần
		List <Map.Entry <Character, Integer>> entryList = new ArrayList <>(timesChar.entrySet());

		entryList.sort(new Comparator <Map.Entry <Character, Integer>>() {
			@Override
			public int compare (Map.Entry <Character, Integer> o1, Map.Entry <Character, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		// Print
		System.out.println("Tần suất xuất hiện của các ký tự sắp xếp giảm dần:");
		for (Map.Entry <Character, Integer> entry : entryList) {
			System.out.printf("'%s': %d\n", entry.getKey(), entry.getValue());
		}
	}
}
