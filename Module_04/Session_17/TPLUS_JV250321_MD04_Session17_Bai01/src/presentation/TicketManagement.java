package presentation;

import business.TicketBusiness;
import business.imp.TicketBusinessImp;
import dao.MovieDAO;
import dao.ShowtimeDAO;
import dao.imp.MovieDAOImp;
import dao.imp.ShowtimeDAOImp;
import entity.Showtime;
import entity.Ticket;

import java.util.List;
import java.util.Scanner;

public class TicketManagement {
    private final TicketBusiness ticketBusiness = new TicketBusinessImp();
    private final ShowtimeDAO showtimeDAO = new ShowtimeDAOImp();

    public void showMenu(Scanner scanner) {
        boolean exit = false;
        do {
            System.out.println("********** Ticket Management **********");
            System.out.println("1. Đặt vé xem phim");
            System.out.println("2. Hiển thị vé xem phim đã đặt");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    createTicket(scanner);
                    break;
                case 2:
                    getTicketByShowtime(scanner);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.err.println("Nhập vào số nguyên 1-3");
            }
        } while (!exit);
    }

    public void createTicket(Scanner scanner) {
        Ticket ticket = new Ticket();
        ticket.setShowtimeId(inputShowtimeId(scanner));

        System.out.print("Nhập vào tên khách hàng: ");
        ticket.setCustomerName(scanner.nextLine());

        System.out.print("Nhập vào số ghế: ");
        ticket.setSeatNumber(scanner.nextLine());
        try {
            ticketBusiness.createTicket(ticket);
            System.out.println("Thêm thành công");
        } catch (Exception e) {
            System.err.println("Thêm thất bại: " + e.getMessage());
        }
        // Cần thêm logic kiểm tra có trùng ghế hay không :(
    }

    public int inputShowtimeId(Scanner scanner) {
        do {
            System.out.print("Nhập vào mã lịch chiếu phim: ");
            int showtimeId = Integer.parseInt(scanner.nextLine());
            if (showtimeDAO.checkExistShowtime(showtimeId)) {
                return showtimeId;
            } else {
                System.err.println("Không tồn tại mã lịch chiếu phim này");
            }
        } while (true);
    }

    public void getTicketByShowtime(Scanner scanner) {
        int showtimeId = inputShowtimeId(scanner);
        List<Ticket> ticketList = ticketBusiness.getTicketByShowtime(showtimeId);
        if (!ticketList.isEmpty()) {
            System.out.println("Danh sách vé đã đặt phim:");
            ticketList.forEach(System.out::println);
        } else {
            System.out.println("Chưa có vé nào cho phim này");
        }
    }
}
