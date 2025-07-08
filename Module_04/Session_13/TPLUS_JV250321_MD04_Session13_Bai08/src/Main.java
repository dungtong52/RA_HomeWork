import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	public static void main (String[] args) {
		List <Student> students = new ArrayList <>();
		students.add(new Student("Quy", "IT", 8.5));
		students.add(new Student("Lan", "Business", 7.2));
		students.add(new Student("Minh", "IT", 9.0));
		students.add(new Student("Huyen", "Design", 6.8));
		students.add(new Student("Long", "IT", 7.9));
		students.add(new Student("Trang", "Business", 8.1));
		students.add(new Student("Viet", "IT", 8.0));
		students.add(new Student("Tu", "Design", 7.5));
		students.add(new Student("Phuc", "Business", 6.9));
		students.add(new Student("An", "AI", 9.5));

		students.stream()
				.collect(Collectors.groupingBy(Student::getMajor, Collectors.counting()))
				.entrySet().stream()
				.sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
				.forEach(entry ->
						System.out.printf("Chuyên ngành: %s - Số lượng: %d\n", entry.getKey(), entry.getValue()));
	}
}
