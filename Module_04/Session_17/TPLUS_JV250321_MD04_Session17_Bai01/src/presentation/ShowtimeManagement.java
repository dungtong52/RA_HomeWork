package presentation;

import dao.MovieDAO;
import dao.ShowtimeDAO;
import dao.imp.MovieDAOImp;
import dao.imp.ShowtimeDAOImp;
import entity.Showtime;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ShowtimeManagement {
    private final ShowtimeDAO showtimeDAO = new ShowtimeDAOImp();
    private final MovieDAO movieDAO = new MovieDAOImp();

    public void showMenu(Scanner scanner) {
        boolean exit = false;
        do {
            System.out.println("********** Showtime Management **********");
            System.out.println("1. Thêm lịch chiếu mới");
            System.out.println("2. Cập nhật lịch chiếu");
            System.out.println("3. Xóa lịch chiếu");
            System.out.println("4. Lấy danh sách lịch chiếu phim");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addShowtime(scanner);
                    break;
                case 2:
                    updateShowtime(scanner);
                    break;
                case 3:
                    deleteShowtime(scanner);
                    break;
                case 4:
                    getShowtimeByMovie(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.err.println("Nhập vào số nguyên 1-5");
            }
        } while (!exit);
    }

    public void addShowtime(Scanner scanner) {
        Showtime showtime = new Showtime();
        showtime.setMovieId(inputMovieId(scanner));

        System.out.print("Nhập vào thời gian chiếu (yyyy-MM-dd): ");
        LocalDateTime showTime = LocalDateTime.parse(scanner.nextLine());
        showtime.setShowTime(showTime);

        System.out.print("Nhập vào tổng số ghế: ");
        int totalSeats = Integer.parseInt(scanner.nextLine());
        showtime.setTotalSeats(totalSeats);
        showtime.setAvailableSeats(totalSeats);
        showtime.setStatus(true);
        if (showtimeDAO.addShowtime(showtime)) {
            System.out.println("Thêm thành công");
        } else {
            System.err.println("Thêm thất bại!");
        }
    }

    public int inputMovieId(Scanner scanner) {
        do {
            System.out.print("Nhập vào mã phim: ");
            int movieId = Integer.parseInt(scanner.nextLine());
            if (movieDAO.checkExistMovie(movieId)) {
                return movieId;
            } else {
                System.err.println("Không tồn tại mã phim này");
            }
        } while (true);
    }

    public void updateShowtime(Scanner scanner) {
        System.out.print("Nhập vào ID lịch chiếu phim muốn cập nhật: ");
        int showtimeId = Integer.parseInt(scanner.nextLine());
        boolean isExistShowtimeId = showtimeDAO.checkExistShowtime(showtimeId);
        if (isExistShowtimeId) {
            Showtime showtimeUpdate = new Showtime();
            showtimeUpdate.setShowtimeId(showtimeId);
            boolean exit = false;
            do {
                System.out.println("1. Cập nhật mã phim");
                System.out.println("2. Cập nhật thời gian");
                System.out.println("3. Cập nhật tổng số ghế");
                System.out.println("4. Thoát");
                System.out.print("Lựa chọn: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        showtimeUpdate.setMovieId(inputMovieId(scanner));
                        break;
                    case 2:
                        System.out.print("Nhập vào thời gian chiếu mới: ");
                        showtimeUpdate.setShowTime(LocalDateTime.parse(scanner.nextLine()));
                        break;
                    case 3:
                        System.out.print("Nhập vào tổng số ghế mới: ");
                        showtimeUpdate.setTotalSeats(Integer.parseInt(scanner.nextLine()));
                        break;
                    case 4:
                        exit = true;
                        break;
                    default:
                        System.err.println("Nhập vào số nguyên 1-4");
                }
                boolean success = showtimeDAO.updateShowtime(showtimeUpdate);
                if (success) {
                    System.out.println("Cập nhật lịch chiếu phim thành công");
                } else {
                    System.err.println("Cập nhật thất bại!");
                }
            } while (!exit);
        } else {
            System.err.println("Mã lịch chiếu phim không tồn tại!");
        }
    }

    public void deleteShowtime(Scanner scanner) {
        System.out.print("Nhập mã ID của lịch chiếu muốn xóa: ");
        int showtimeId = Integer.parseInt(scanner.nextLine());
        boolean isExistShowtimeId = showtimeDAO.checkExistShowtime(showtimeId);
        if (isExistShowtimeId) {
            boolean success = showtimeDAO.deleteShowtime(showtimeId);
            if (success) {
                System.out.println("Xóa lịch chiếu phim thành công");
            } else {
                System.err.println("Xóa lịch chiếu phim thất bại!");
            }
        } else {
            System.err.println("Mã lịch chiếu phim không tồn tại!");
        }
    }

    public void getShowtimeByMovie(Scanner scanner) {
        while (true) {
            System.out.print("Nhập vào mã phim muốn tìm: ");
            int movieID = Integer.parseInt(scanner.nextLine());
            if (movieDAO.checkExistMovie(movieID)) {
                List<Showtime> showtimeList = showtimeDAO.getShowtimeByMovie(movieID);
                if (!showtimeList.isEmpty()) {
                    System.out.println("Danh sách lịch chiếu phim:");
                    showtimeList.forEach(System.out::println);
                    break;
                } else {
                    System.out.println("Chưa có lịch chiếu phim nào trong danh sách");
                }
            } else {
                System.err.println("Không tồn tại phim này");
            }
        }
    }

}