import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main (String[] args) {
		Map <String, Integer> studentList = new HashMap <>();
		studentList.put("An", 9);
		studentList.put("Bình", 8);
		studentList.put("Chi", 9);
		studentList.put("Dũng", 7);
		studentList.put("Hà", 8);
		studentList.put("Linh", 7);
		studentList.put("Minh", 9);

		Map <Integer, List <String>> groupByScore = new HashMap <>();

		for (Map.Entry <String, Integer> entry : studentList.entrySet()) {
			String name = entry.getKey();
			Integer score = entry.getValue();

			if (!groupByScore.containsKey(score)) {
				List <String> nameNewList = new ArrayList <>();
				nameNewList.add(name);
				groupByScore.put(score, nameNewList);
			} else {
				List <String> nameExistList = groupByScore.get(score);
				nameExistList.add(name);
			}
		}

		System.out.println("Danh sách học sinh nhóm theo điểm");
		for (Map.Entry <Integer, List <String>> entry : groupByScore.entrySet()) {
			int score = entry.getKey();
			List <String> studentName = entry.getValue();
			System.out.printf("Điểm %d: %s\n", score, studentName);
		}

	}
}
