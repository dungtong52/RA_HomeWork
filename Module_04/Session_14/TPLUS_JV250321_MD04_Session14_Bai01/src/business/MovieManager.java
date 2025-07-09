package business;

import entity.Movie;
import validator.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieManager<T extends Movie> {
	private final List <T> movieList = new ArrayList <>();

	public void addMovie (Scanner scanner) {
		T movie = (T) new Movie();
		movie.inputData(scanner, this);
		movieList.add(movie);
		System.out.println("Thêm phim thành công!");
	}

	public void deleteMovieById (Scanner scanner) {
		System.out.println("Nhập ID cần xóa:");
		String idInput = scanner.nextLine();
		if (Validation.isPositiveInteger(idInput)) {
			int idDelete = Integer.parseInt(idInput);
			movieList.stream()
					.filter(item -> item.getId() == idDelete).findFirst()
					.ifPresentOrElse(item -> {
						System.out.printf("Bạn có chắc chắn muốn xóa bộ phim có ID %d ('Y')\n", idDelete);
						if (scanner.nextLine().equalsIgnoreCase("Y")) {
							movieList.remove(item);
							System.out.println("Xóa thành công phim có ID " + idDelete);
						}
					}, () -> System.out.printf("Không tìm thấy ID %d của phim\n", idDelete));
		} else {
			System.err.println("ID nhập vào không đúng định dạng!");
		}
	}

	public void updateMovieById (Scanner scanner) {
		System.out.println("Nhập ID phim bạn muốn cập nhật:");
		String idInput = scanner.nextLine();
		if (Validation.isPositiveInteger(idInput)) {
			int idUpdate = Integer.parseInt(idInput);
			movieList.stream()
					.filter(item -> item.getId() == idUpdate).findFirst()
					.ifPresentOrElse(item -> {
						boolean exit = false;
						do {
							System.out.println("1. Cập nhật tên phim");
							System.out.println("2. Cập nhật đạo diễn");
							System.out.println("3. Cập nhật ngày phát hành");
							System.out.println("4. Cập nhật rating");
							System.out.println("5. Thoát cập nhật");
							System.out.print("Nhập lựa chọn: ");
							String choiceInput = scanner.nextLine();
							if (Validation.isIntegerInRange(choiceInput, 1, 5)) {
								int choice = Integer.parseInt(choiceInput);
								switch (choice) {
									case 1 -> item.inputTitle(scanner);
									case 2 -> item.inputDirector(scanner);
									case 3 -> item.inputReleaseDate(scanner);
									case 4 -> item.inputRating(scanner);
									default -> exit = true;
								}
							} else {
								System.err.println("Nhập số nguyên 1-5");
							}
						} while (!exit);
					}, () -> System.err.println("ID này không tồn tại!"));
		} else {
			System.err.println("Vui lòng nhập đúng định dạng ID!");
		}
	}

	public void findMovieByName (Scanner scanner) {
		System.out.println("Nhập vào tên bộ phim muốn tìm: ");
		String movieName = scanner.nextLine();
		if (!Validation.isEmpty(movieName)) {
			List <T> movieSearch = movieList.stream()
					.filter(item -> item.getTitle().toLowerCase().contains(movieName.toLowerCase()))
					.toList();
			if (movieSearch.isEmpty()) {
				System.out.println("Không tìm thấy bộ phim này");
			} else {
				movieSearch.forEach(System.out::println);
			}
		} else {
			System.err.println("Vui lòng không để trống!");
		}
	}

	public void displayMovieList () {
		if (movieList.isEmpty()) {
			System.out.println("Danh sách phim trống.");
		} else {
			movieList.forEach(System.out::println);
		}
	}

	public void filterMovieByRating (Scanner scanner) {
		System.out.println("Nhập mức rating thấp nhất cho các bộ phim mà bạn muốn lọc:");
		String ratingInput = scanner.nextLine();
		if (Validation.isDouble(ratingInput)) {
			double ratingSearch = Double.parseDouble(ratingInput);
			List <T> movieSearchList = movieList.stream()
					.filter(item -> item.getRating() > ratingSearch)
					.toList();
			if (movieSearchList.isEmpty()) {
				System.out.println("Không tìm thấy phim nào có rating cao hơn " + ratingSearch);
			} else {
				movieSearchList.forEach(System.out::println);
			}
		} else {
			System.out.println("Vui lòng nhập đúng định dạng của ID!");
		}
	}

	public boolean isIdExists (int id) {
		return movieList.stream().anyMatch(movie -> movie.getId() == id);
	}
}
