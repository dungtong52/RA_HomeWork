import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentManager {
	public static void main (String[] args) {
		List <String> students = new ArrayList <>();

		students.add("Nguyễn Văn A");
		students.add("Trần Thị B");
		students.add("Lê Văn C");
		students.add("Đỗ Thị D");
		students.add("Phạm Văn E");

		System.out.println("-----Duyệt bằng vòng lặp for-----");
		for (int i = 0; i < students.size(); i++) {
			System.out.println(students.get(i));
		}

		System.out.println("-----Duyệt bằng vòng lặp foreach-----");
		for (String name : students) {
			System.out.println(name);
		}

		System.out.println("-----Duyệt bằng Iterator-----");
		Iterator <String> iterator = students.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
