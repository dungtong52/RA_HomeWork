import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main (String[] args) {
		List <Student> studentList = new ArrayList <>();

		studentList.add(new Student(1, "Nguyen Van A", 8.5));
		studentList.add(new Student(2, "Nguyen Van B", 7.0));
		studentList.add(new Student(3, "Nguyen Van C", 5.8));
		studentList.add(new Student(4, "Nguyen Van D", 4.2));
		studentList.add(new Student(5, "Nguyen Van E", 6.9));
		studentList.add(new Student(6, "Nguyen Van F", 3.5));
		studentList.add(new Student(7, "Nguyen Van G", 8.2));

		Map <String, List <Student>> classifyStudentMap = new HashMap <>();

		for (Student student : studentList) {
			String classifyConvert = student.classifyStudent();

			if (!classifyStudentMap.containsKey(classifyConvert)) {
				List <Student> list = new ArrayList <>();
				list.add(student);
				classifyStudentMap.put(classifyConvert, list);
			} else {
				classifyStudentMap.get(classifyConvert).add(student);
			}
		}

		System.out.println("Danh sách sinh viên theo phân loại: ");
		for(String type : classifyStudentMap.keySet()){
			System.out.printf("--- %s ---\n", type);
			for(Student student : classifyStudentMap.get(type)){
				System.out.println(student.displayData());
			}
		}

	}
}
