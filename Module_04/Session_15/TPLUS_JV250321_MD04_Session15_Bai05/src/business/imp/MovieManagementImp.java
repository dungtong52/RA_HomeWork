package business.imp;

import business.MovieManagement;
import dao.MovieDAO;
import dao.imp.MovieDAOImp;
import entity.Movie;
import validator.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieManagementImp implements MovieManagement {
    private final MovieDAO movieDAO;

    public MovieManagementImp() {
        movieDAO = new MovieDAOImp();
    }

    @Override
    public void createMovie(Scanner scanner) {
        Movie movie = new Movie();
        movie.inputData(scanner);

        if (movieDAO.createMovie(movie)) {
            System.out.println("Thêm thành công!");
        } else {
            System.err.println("Có lỗi khi thêm phim mới!");
        }
    }

    @Override
    public void displayAllMovie() {
        List<Movie> movieList = new ArrayList<>();
        movieList = movieDAO.showAll();
        if (!movieList.isEmpty()) {
            System.out.println("Danh sách phim hiện có");
            movieList.forEach(System.out::println);
        } else {
            System.err.println("Hiện chưa có phim nào!");
        }
    }

    @Override
    public void updateMovie(Scanner scanner) {
        System.out.print("Nhập vào mã phim muốn cập nhật: ");
        String movieId = scanner.nextLine();
        if (Validation.isPositiveInteger(movieId)) {
            Movie movieUpdate = movieDAO.searchMovieById(Integer.parseInt(movieId));
            if (movieUpdate != null) {
                boolean notExit = true;
                do {
                    System.out.println("1. Cập nhật tiêu đề phim");
                    System.out.println("2. Cập nhật đạo diễn");
                    System.out.println("3. Cập nhật năm phát hành");
                    System.out.println("4. Thoát");
                    System.out.print("Lựa chọn: ");
                    String choice = scanner.nextLine();
                    if (Validation.isIntegerInRange(choice, 1, 4)) {
                        switch (Integer.parseInt(choice)) {
                            case 1:
                                movieUpdate.setTitle(movieUpdate.inputTitle(scanner));
                                break;
                            case 2:
                                movieUpdate.setDirector(movieUpdate.inputDirector(scanner));
                                break;
                            case 3:
                                movieUpdate.setYear(movieUpdate.inputYear(scanner));
                                break;
                            default:
                                notExit = false;
                        }
                    } else {
                        System.err.println("Nhập vào số nguyên dương 1-4");
                    }
                } while (notExit);

                if (movieDAO.updateMovie(movieUpdate)) {
                    System.out.println("Cập nhật thành công!");
                } else {
                    System.err.println("Có lỗi trong quá trình cập nhật!");
                }

            } else {
                System.err.println("Không tìm thấy mã phim đã nhập " + movieId);
            }
        } else {
            System.err.println("Mã phim không hợp lệ!");
        }
    }

    @Override
    public void deleteMovie(Scanner scanner) {
        System.out.print("Nhập vào mã phim muốn xóa: ");
        String movieId = scanner.nextLine();
        if (Validation.isPositiveInteger(movieId)) {
            Movie movieDelete = movieDAO.searchMovieById(Integer.parseInt(movieId));
            if (movieDelete != null) {
                if (movieDAO.deleteMovie(Integer.parseInt(movieId))) {
                    System.out.println("Xóa thành công!");
                } else {
                    System.err.println("Có lỗi khi xóa!");
                }
            } else {
                System.err.println("Không tìm thấy mã phim đã nhập " + movieId);
            }
        } else {
            System.err.println("Mã phim không hợp lệ!");
        }
    }
}
