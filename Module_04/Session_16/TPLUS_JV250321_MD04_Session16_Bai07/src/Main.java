import business.BookingBusiness;
import business.imp.BookingBusinessImp;
import entity.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final BookingBusiness bookingBusiness;

    public Main() {
        bookingBusiness = new BookingBusinessImp();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        while (true) {
            System.out.println("************** ROOM MANAGEMENT **************** ");
            System.out.println("1. Đặt phòng");
            System.out.println("2. Hủy đặt phòng");
            System.out.println("3. Hiển thị danh sách phòng còn trống");
            System.out.println("4. Thoát");
            System.out.print("Lựa chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    bookRoom(main.bookingBusiness);
                    break;
                case 2:
                    cancelBooking(main.bookingBusiness);
                    break;
                case 3:
                    listAvailableRooms(main.bookingBusiness);
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.err.println("Nhập vào số nguyên 1-4");
            }
        }
    }

    public static void bookRoom(BookingBusiness bookingBusiness) {
        boolean success = bookingBusiness.bookRoom(1, "Nguyen Van A",
                LocalDate.parse("2025-07-14"), LocalDate.parse("2025-07-17"));
        if (success) {
            System.out.println("Đặt phòng thành công");
        } else {
            System.err.println("Đặt phòng thất bại!");
        }
    }

    public static void cancelBooking(BookingBusiness bookingBusiness) {
        boolean success = bookingBusiness.cancelBooking(1);
        if (success) {
            System.out.println("Đặt phòng thành công");
        } else {
            System.err.println("Đặt phòng thất bại!");
        }
    }

    public static void listAvailableRooms(BookingBusiness bookingBusiness) {
        List<Room> roomList = bookingBusiness.listAvailableRooms();
        if (!roomList.isEmpty()) {
            roomList.forEach(System.out::println);
        } else {
            System.err.println("Không còn phòng trống!");
        }
    }
}
