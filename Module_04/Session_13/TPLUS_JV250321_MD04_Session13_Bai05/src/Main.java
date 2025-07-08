import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        Scanner scanner = new Scanner(System.in);
        List<Event> eventList = new ArrayList<>();

        do {
            System.out.println("Nhập tên sự kiện");
            String eventName = scanner.nextLine();

            try {
                System.out.println("Nhập thời gian bắt đầu (dd-MM-yyyy HH:mm)");
                String startDateTime = scanner.nextLine();
                LocalDateTime inputStartDateTime = LocalDateTime.parse(startDateTime, dateTimeFormatter);

                System.out.println("Nhập thời gian kết thúc (dd-MM-yyyy HH:mm)");
                String endDateTime = scanner.nextLine();
                LocalDateTime inputEndDateTime = LocalDateTime.parse(endDateTime, dateTimeFormatter);

                if (inputStartDateTime.isAfter(inputEndDateTime)) {
                    System.err.println("Thời gian bắt đầu phải trước thời gian kết thúc");
                } else {
                    Event newEvent = new Event(eventName, inputStartDateTime, inputEndDateTime);
                    eventList.add(newEvent);
                    System.out.println("Nhập thành công!");
                    break;
                }

            } catch (DateTimeParseException e) {
                System.err.println("Nhập sai định dạng!");
            }
        } while (true);

        for (Event event : eventList) {
            System.out.println(event.toString());
        }
    }
}
