package presentation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MovieManager<Movie> movieManager = new MovieManager<>();

        do {
            System.out.println("\n*********** MENU ************");
            System.out.println(
                    "1. Thêm mới phim\n" +
                            "2. Sửa phim\n" +
                            "3. Xóa phim\n" +
                            "4. Hiển thị phim\n" +
                            "5. Tìm kiếm phim theo rating và tìm kiếm phim theo tên\n" +
                            "6. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> movieManager.addMovie(scanner);
                case 2 -> movieManager.deleteMovieById(scanner);
                case 3 -> movieManager.updateMovieById(scanner);
                case 4 -> movieManager.displayMovieList();
                case 5 -> movieManager.findMovieByName(scanner);
                case 6 -> movieManager.filterMovieByRating(scanner);
                case 7 -> System.exit(0);
                default -> System.err.println("Nhập từ 1-7");
            }
        } while (true);
    }
}
