import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Message> messageList = new ArrayList<>();
        System.out.print("Nhập tên người gửi: ");
        String sender = scanner.nextLine();
        System.out.print("Nhập nội dung tin nhắn: ");
        String content = scanner.nextLine();
        LocalDateTime
        Message message = new Message(sender, content, );
        do {
            System.out.println("Nhập 'history' để xem lịch sử, hoặc 'filter' " +
                    "để lọc tin nhắn theo người gửi, hoặc 'date' để lọc theo ngày");
            String inputChoice = scanner.nextLine();
            switch (inputChoice) {
                case "history":
                    break;
                case "filter":
                    break;
                case "date":
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
