import java.util.HashMap;
import java.util.Map;

public class StudentManager {
	public static void main (String[] args) {
		Map <String, Double> studentMap1 = new HashMap <>();
		Map <String, Double> studentMap2 = new HashMap <>();

		studentMap1.put("Nguyễn Văn A", 7.5);
		studentMap1.put("Trần Thị B", 8.0);
		studentMap1.put("Lê Văn C", 6.5);

		studentMap2.put("Nguyễn Văn A", 8.0);
		studentMap2.put("Phạm Thị D", 8.0);
		studentMap2.put("Lê Văn C", 7.8);

		Map <String, Double> mergeStudent = new HashMap <>(studentMap1);

		for (Map.Entry <String, Double> entry : studentMap2.entrySet()) {
			if (mergeStudent.containsKey(entry.getKey())) {
				mergeStudent.put(entry.getKey(), entry.getValue() + mergeStudent.get(entry.getKey()));
			} else {
				mergeStudent.put(entry.getKey(), entry.getValue());
			}
		}

		System.out.println("Danh sách sinh viên sau khi gộp:");
		for (Map.Entry <String, Double> entry : mergeStudent.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
}
