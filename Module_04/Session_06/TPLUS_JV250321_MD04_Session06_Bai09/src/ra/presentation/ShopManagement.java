package ra.presentation;

import ra.entity.Categories;
import ra.entity.Product;

import java.util.Scanner;

public class ShopManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Categories[] categoryList = new Categories[100];
        Product[] productList = new Product[100];

        do {
            System.out.println(
                    "\n******************SHOP MENU*******************\n" +
                            "1. Quản lý danh mục sản phẩm\n" +
                            "2. Quản lý sản phẩm\n" +
                            "3. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    // Quản lý danh mục sản phẩm

                    break;
                case 2:
                    // Quản lý sản phẩm
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Hãy nhập 1 số trong khoảng 1-3");
            }
        } while (true);
    }

    // Chức năng 1: Quản lý danh mục sản phẩm
    public static void productsManagement() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(
                    "\n********************CATEGORIES MENU***********\n" +
                            "1. Nhập thông tin các danh mục\n" +
                            "2. Hiển thị thông tin các danh mục\n" +
                            "3. Cập nhật thông tin danh mục\n" +
                            "4. Xóa danh mục\n" +
                            "5. Cập nhật trạng thái danh mục\n" +
                            "6. Thoát");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    // Nhập thông tin các danh mục
                    break;
                case 2:
                    // Hiển thị thông tin các danh mục
                    break;
                case 3:
                    // Cập nhật thông tin danh mục
                    break;
                case 4:
                    // Xóa danh mục
                    break;
                case 5:
                    // Cập nhật trạng thái danh mục
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.err.println("Hãy nhập 1 số trong khoảng 1-6");
            }
        } while (true);
    }

    // Chức năng 2: Quản lý sản phẩm
    public static void categoriesManagement() {

    }
}
