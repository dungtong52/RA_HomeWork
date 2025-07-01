package presentation;

import entity.Category;
import entity.CategoryManagement;

import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		CategoryManagement categoryManagement = new CategoryManagement(100);

		do {
			System.out.println("-------- Menu --------");
			System.out.println("1. Thêm danh mục");
			System.out.println("2. Hiển thị danh mục");
			System.out.println("3. Cập nhật danh mục");
			System.out.println("4. Xóa danh mục");
			System.out.println("5. Thoát");
			System.out.print("Nhập lựa chọn: ");
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
				case 1:
					// Thêm danh mục
					System.out.println("--- Thêm danh mục ---");
					System.out.print("Nhập ID danh mục: ");
					int idInput = Integer.parseInt(scanner.nextLine());
					System.out.print("Nhập tên danh mục: ");
					String nameInput = scanner.nextLine();
					System.out.print("Nhập mô tả danh mục: ");
					String descriptionInput = scanner.nextLine();
					Category newCategory = new Category(idInput, nameInput, descriptionInput);

					categoryManagement.addCategory(newCategory);
					break;
				case 2:
					// Hiển thị danh mục
					Category[] allCategories = categoryManagement.findAll();
					for (int i = 0; i < allCategories.length; i++) {
						System.out.println(allCategories[i].display());
					}
					break;
				case 3:
					// Cập nhật danh mục
					System.out.println("--- Cập nhật danh mục ---");
					System.out.print("Nhập ID danh mục cần cập nhật: ");
					int idForUpdate = Integer.parseInt(scanner.nextLine());

					System.out.print("Nhập tên mới: ");
					String nameForUpdate = scanner.nextLine();
					System.out.print("Nhập mô tả mới: ");
					String descriptionForUpdate = scanner.nextLine();
					Category updateCategory = new Category(idForUpdate, nameForUpdate, descriptionForUpdate);

					categoryManagement.updateCategory(updateCategory);
					break;
				case 4:
					// Xóa danh mục
					System.out.println("--- Xóa danh mục ---");
					System.out.print("Nhập ID danh mục cần cập nhật: ");
					int idForDelete = Integer.parseInt(scanner.nextLine());

					categoryManagement.deleteById(idForDelete);
					break;
				case 5:
					System.exit(0);
				default:
					System.err.println("Nhập các số từ 1-5");
			}
		} while (true);
	}
}
