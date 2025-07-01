package presentation;

import entity.Book;
import entity.BookManager;

import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		BookManager bookManager = new BookManager(100);
		do {
			System.out.println("------ Menu ------");
			System.out.println("1. Thêm sách");
			System.out.println("2. Hiển thị sách");
			System.out.println("3. Xóa sách");
			System.out.println("4. Thoát");
			System.out.printf("Chọn chức năng: ");
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
				case 1:
					// Thêm sách
					System.out.println("--- Thêm sách ---");
					System.out.print("Nhập tiêu đề: ");
					String titleInput = scanner.nextLine();
					System.out.print("Nhập tác giả: ");
					String authorInput = scanner.nextLine();
					System.out.print("Nhập ISBN: ");
					String isbnInput = scanner.nextLine();
					System.out.print("Nhập năm xuất bản: ");
					int yearInput = Integer.parseInt(scanner.nextLine());
					Book newbook = new Book(titleInput, authorInput, isbnInput, yearInput);
					bookManager.addBook(newbook);
					System.out.println("Đã thêm sách: " + newbook.getDetails());
					break;
				case 2:
					// Hiển thị sách
					System.out.println("--- Danh sách sách ---");
					bookManager.displayBooks();
					break;
				case 3:
					// Xóa sách
					System.out.println("--- Xóa sách ---");
					System.out.print("Nhập ISBN sách muốn xóa: ");
					String isbnForDelete = scanner.nextLine();
					bookManager.removeBook(isbnForDelete);
					break;
				case 4:
					System.exit(0);
				default:
					System.err.println("Nhập vào số 1-4");
			}
		} while (true);
	}
}
