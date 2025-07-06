package presentation;

import entity.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentManager {
	public static void main (String[] args) {
		List <Student> studentList = new ArrayList <>();
		studentList.add(new Student("Alice", 20, 8.5));
		studentList.add(new Student("Bob", 22, 6.0));
		studentList.add(new Student("Charlie", 21, 7.5));
		studentList.add(new Student("David", 23, 9.0));
		studentList.add(new Student("Eve", 20, 5.5));
		studentList.add(new Student("Frank", 22, 8.0));
		studentList.add(new Student("Grace", 21, 7.0));
		studentList.add(new Student("Heidi", 23, 8.8));
		studentList.add(new Student("Ivan", 20, 6.5));
		studentList.add(new Student("Judy", 22, 7.2));

		System.out.println("Danh sách sinh viên có điểm lớn hơn 7.0, sắp xếp theo tên");
		studentList.stream()
				.filter(s -> s.getGrade() > 7)
				.sorted(Comparator.comparing(Student::getName))
				.forEach(student -> student.displayData());
	}
}
