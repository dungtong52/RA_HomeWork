import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
	public static void main (String[] args) {
		List <User> userList = new ArrayList <>();
		User u1 = new User(1, "Quy", Optional.of("quy@example.com"));
		User u2 = new User(2, "Lan", Optional.empty());
		User u3 = new User(3, "Huyen", Optional.of("huyen@example.com"));
		userList.add(u1);
		userList.add(u2);
		userList.add(u3);

		List <Task> taskList = new ArrayList <>();
		taskList.add(new Task(1, "Viet bao cao", u1, LocalDate.of(2025, 3, 20), false));
		taskList.add(new Task(2, "Thiet ke slide", u1, LocalDate.of(2025, 3, 25), true));
		taskList.add(new Task(3, "Hop nhom", u2, LocalDate.of(2025, 3, 18), false));
		taskList.add(new Task(4, "Nop tai lieu", u3, LocalDate.of(2025, 3, 22), true));
		taskList.add(new Task(5, "Chuan bi thuyet trinh", u2, LocalDate.of(2025, 3, 19), true));

		// In danh sach
		System.out.println("Danh sách  người dùng:");
		userList.forEach(System.out::println);

		// Thong ke cong viec da hoan thanh
		long completedCount = taskList.stream()
				.filter(Task::isCompleted)
				.count();
		System.out.println("Số công việc đã hoàn thành: " + completedCount);

		// Liet ke cac cong viec qua han chua hoan thanh
		System.out.println("Các công việc quá hạn chưa hoàn thành:");
		taskList.stream()
				.filter(task -> !task.isCompleted())
				.filter(task -> task.getDueDate().isBefore(LocalDate.now()))
				.forEach(System.out::println);
	}
}
