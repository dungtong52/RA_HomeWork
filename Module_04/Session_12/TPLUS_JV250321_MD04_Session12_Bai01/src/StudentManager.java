import java.util.HashMap;
import java.util.Map;

public class StudentManager {
	public static void main (String[] args) {
		Map <String, Double> students = new HashMap <>();

		students.put("Nguyen Van A", 8.9);
		students.put("Tran Thi B", 7.8);
		students.put("Le Van C", 6.9);
		students.put("Pham Van D", 8.1);
		students.put("Vu Van E", 7.5);

		String studentnameSearch = "Nguyen Van A";

		System.out.println("Danh sách:");
		for (Map.Entry <String, Double> entry : students.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

		boolean isExist = false;
		System.out.printf("\nTìm sinh viên '%s'\n", studentnameSearch);
		for (Map.Entry <String, Double> entry : students.entrySet()) {
			if (entry.getKey().equals(studentnameSearch)) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
				isExist = true;
			}
		}
		if (!isExist) {
			System.out.println("Không tồn tại!");
		}
	}
}
