package presentation;

import business.MovieManager;
import entity.Movie;
import validator.Validation;

import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		MovieManager <Movie> movieManager = new MovieManager <>();

		do {
			System.out.println("\n*********** MENU ************");
			System.out.println(
					"1. Thêm mới phim\n" +
							"2. Xóa phim\n" +
							"3. Sửa phim\n" +
							"4. Hiển thị phim\n" +
							"5. Tìm kiếm phim theo tên\n" +
							"6. Tìm kiếm phim theo rating\n" +
							"7. Thoát");
			System.out.print("Lựa chọn của bạn: ");
			String choiceInput = scanner.nextLine();
			if (Validation.isIntegerInRange(choiceInput, 1, 7)) {
				int choice = Integer.parseInt(choiceInput);
				switch (choice) {
					case 1 -> movieManager.addMovie(scanner);
					case 2 -> movieManager.deleteMovieById(scanner);
					case 3 -> movieManager.updateMovieById(scanner);
					case 4 -> movieManager.displayMovieList();
					case 5 -> movieManager.findMovieByName(scanner);
					case 6 -> movieManager.filterMovieByRating(scanner);
					default -> System.exit(0);
				}
			} else {
				System.err.println("Nhập số nguyên từ 1-7");
			}
		} while (true);
	}
}
