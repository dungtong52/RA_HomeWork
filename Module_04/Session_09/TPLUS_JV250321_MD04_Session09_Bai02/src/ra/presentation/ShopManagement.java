package ra.presentation;

import ra.entity.CategoryManagement;
import ra.entity.ProductManagement;

import java.util.Scanner;

public class ShopManagement {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		CategoryManagement categoryManagement = new CategoryManagement();
		ProductManagement productManagement = new ProductManagement();

		do {
			System.out.println("*************** SHOP MANAGEMENT **************");
			System.out.println("1. Quản lý danh mục sản phẩm");
			System.out.println("2. Quản lý sản phẩm");
			System.out.println("3. Thoát");
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
				case 1:
					categoryManagement.displayCategoryMenu(scanner);
					break;
				case 2:
					productManagement.displayProductMenu(scanner);
					break;
				case 3:
					System.exit(0);
				default:
					System.err.println("Nhập vào số trong khoảng 1-3");
			}
		} while (true);
	}

}
