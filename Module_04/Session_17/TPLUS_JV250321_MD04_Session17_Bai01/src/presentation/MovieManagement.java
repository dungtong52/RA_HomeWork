package presentation;

import business.MovieBusiness;
import business.imp.MovieBusinessImp;
import entity.Movie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MovieManagement {
    private final MovieBusiness movieBusiness = new MovieBusinessImp();

    public void showMenu(Scanner scanner) {
        boolean exit = false;
        do {
            System.out.println("********** Movie Management **********");
            System.out.println("1. Thêm phim mới");
            System.out.println("2. Cập nhật thông tin phim");
            System.out.println("3. Xóa phim");
            System.out.println("4. Lấy danh sách phim");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addMovie(scanner);
                    break;
                case 2:
                    updateMovie(scanner);
                    break;
                case 3:
                    deleteMovie(scanner);
                    break;
                case 4:
                    getAllMovie();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.err.println("Nhập vào số nguyên 1-5");
            }
        } while (!exit);
    }

    public void addMovie(Scanner scanner) {
        Movie movie = new Movie();
        System.out.print("Nhập vào tiêu đề: ");
        String title = scanner.nextLine();
        movie.setTitle(title);

        System.out.print("Nhập vào tên đạo diễn: ");
        String director = scanner.nextLine();
        movie.setDirector(director);

        System.out.print("Nhập vào thời gian: ");
        int duration = Integer.parseInt(scanner.nextLine());
        movie.setDuration(duration);

        System.out.println("Nhập vào thời gian phát hành: ");
        LocalDate releaseDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        movie.setRelease_date(releaseDate);

        movie.setStatus(true);

        if (movieBusiness.addMovie(movie)) {
            System.out.println("Thêm thành công");
        } else {
            System.err.println("Có lỗi trong quá trình thêm!");
        }
    }

    public void updateMovie(Scanner scanner) {
        System.out.print("Nhập vào ID phim muốn cập nhật: ");
        int movieId = Integer.parseInt(scanner.nextLine());
        boolean isExistMovieId = movieBusiness.checkExistMovie(movieId);
        if (isExistMovieId) {
            Movie movieUpdate = new Movie();
            movieUpdate.setMovieId(movieId);
            boolean exit = false;
            do {
                System.out.println("1. Cập nhật tên phim");
                System.out.println("2. Cập nhật đạo diễn");
                System.out.println("3. Cập nhật thời gian phim");
                System.out.println("4. Cập nhật ngày phát hành");
                System.out.println("5. Cập nhật trạng thái");
                System.out.println("6. Thoát");
                System.out.print("Lựa chọn: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Nhập vào tên mới của phim: ");
                        movieUpdate.setTitle(scanner.nextLine());
                        break;
                    case 2:
                        System.out.print("Nhập vào đạo diễn mới: ");
                        movieUpdate.setDirector(scanner.nextLine());
                        break;
                    case 3:
                        System.out.print("Nhập vào thời gian phim: ");
                        movieUpdate.setDuration(Integer.parseInt(scanner.nextLine()));
                        break;
                    case 4:
                        System.out.print("Nhập vào ngày phát hành mới của phim: ");
                        movieUpdate.setRelease_date(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                        break;
                    case 5:
                        System.out.print("Nhập trạng thái mới: ");
                        movieUpdate.setStatus(Boolean.parseBoolean(scanner.nextLine()));
                        break;
                    case 6:
                        exit = true;
                        break;
                    default:
                        System.err.println("Nhập vào số nguyên 1-6");
                }
                boolean success = movieBusiness.updateMovie(movieUpdate);
                if (success) {
                    System.out.println("Cập nhật phim thành công");
                } else {
                    System.err.println("Cập nhật thất bại!");
                }
            } while (!exit);
        } else {
            System.err.println("Mã phim không tồn tại!");
        }
    }

    public void deleteMovie(Scanner scanner) {
        System.out.print("Nhập mã ID của phim muốn xóa: ");
        int movieId = Integer.parseInt(scanner.nextLine());
        boolean isExistMovieId = movieBusiness.checkExistMovie(movieId);
        if (isExistMovieId) {
            boolean success = movieBusiness.deleteMovie(movieId);
            if (success) {
                System.out.println("Xóa phim thành công");
            } else {
                System.err.println("Xóa phim thất bại!");
            }
        } else {
            System.err.println("Mã phim không tồn tại!");
        }
    }

    public void getAllMovie() {
        List<Movie> movieList = movieBusiness.getAllMovie();
        if (!movieList.isEmpty()) {
            System.out.println("Danh sách phim hiện có:");
            movieList.forEach(System.out::println);
        } else {
            System.out.println("Chưa có phim nào trong danh sách");
        }
    }
}
