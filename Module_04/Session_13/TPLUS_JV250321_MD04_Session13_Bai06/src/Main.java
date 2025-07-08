import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

		List <Message> messageList = new ArrayList <>();

		boolean exit = false;
		do {
			System.out.print("Nhập tên người gửi: ");
			String sender = scanner.nextLine();
			System.out.print("Nhập nội dung tin nhắn: ");
			String content = scanner.nextLine();
			LocalDateTime timestamp = LocalDateTime.now();
			Message message = new Message(sender, content, timestamp);
			messageList.add(message);
			System.out.println("Nếu thoát thì nhập 'exit', nhập tiếp thì ấn phím bất kỳ");
			if(scanner.nextLine().equals("exit")){
				exit = true;
			}
		} while (!exit);

		do {
			System.out.println("Nhập 'history' để xem lịch sử, hoặc 'filter' " +
					"để lọc tin nhắn theo người gửi, hoặc 'date' để lọc theo ngày");
			String inputChoice = scanner.nextLine();
			switch (inputChoice) {
				case "history":
					System.out.println("Lịch sử chat:");
					messageList.stream()
							.forEach(System.out::println);
					break;
				case "filter":
					System.out.println("Nhập tên người gửi để lọc:");
					String nameSearch = scanner.nextLine();
					messageList.stream()
							.filter(mess -> mess.getSender().equalsIgnoreCase(nameSearch))
							.forEach(System.out::println);
					break;
				case "date":
					System.out.println("Nhập ngày (dd-MM-yyyy)");
					String inputDate = scanner.nextLine();
					try {
						LocalDate filterDate = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

						messageList.stream()
								.filter(mess -> mess.getTimestamp().toLocalDate().equals(filterDate))
								.forEach(System.out::println);
					} catch (Exception e) {
						System.err.println("Sai định dạng ngày!");
					}
					break;
				case "exit":
					System.out.println("Chào tạm biệt");
					System.exit(0);
				default:
					System.err.println("Nhập vào history, filter hoặc date");
			}
		} while (true);
	}
}
