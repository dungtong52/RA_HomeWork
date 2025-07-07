package presentation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("------------ MENU QUẢN LÝ ĐƠN HÀNG -------------");
            System.out.println("1. Thêm đơn hàng");
            System.out.println("2. Sửa đơn hàng");
            System.out.println("3. Xóa đơn hàng");
            System.out.println("4. Hiển thị thông tin đơn hàng");
            System.out.println("5. Thoát");
            System.out.println("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:

                    break;
                case 2:
                    System.out.print("Nhập vào mã đơn hàng muốn sửa: ");

                    System.out.println("Cập nhật thành công!");
                    break;
                case 3:
                    System.out.print("Nhập vào mã đơn hàng muốn sửa: ");

                    System.out.println("Xóa thành công!");
                    break;
                case 4:

                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Nhập số từ 1-5");

            }
        } while (true);
    }
}