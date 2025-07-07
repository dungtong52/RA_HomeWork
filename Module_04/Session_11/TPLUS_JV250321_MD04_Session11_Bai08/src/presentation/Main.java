package presentation;

import business.OrderManager;
import entity.Order;

import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		OrderManager orderManager = new OrderManager();

		do {
			System.out.println("\n------------ MENU QUẢN LÝ ĐƠN HÀNG -------------");
			System.out.println("1. Thêm đơn hàng");
			System.out.println("2. Sửa đơn hàng");
			System.out.println("3. Xóa đơn hàng");
			System.out.println("4. Hiển thị thông tin đơn hàng");
			System.out.println("5. Thoát");
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
				case 1:
					Order newOrder = new Order();
					newOrder.inputData(scanner);
					orderManager.add(newOrder);
					break;
				case 2:
					orderManager.display();
					System.out.print("Nhập vào mã đơn hàng muốn sửa: ");
					String idUpdate = scanner.nextLine();
					int indexUpdate = orderManager.findIndexById(idUpdate);

					Order orderUpdate = orderManager.orderList.get(indexUpdate);
					orderUpdate.setCustomerName(orderUpdate.inputCustomerName(scanner));
					orderManager.update(indexUpdate, orderUpdate);
					break;
				case 3:
					System.out.print("Nhập vào mã đơn hàng muốn sửa: ");
					String idDelete = scanner.nextLine();
					int indexDelete = orderManager.findIndexById(idDelete);

					orderManager.delete(indexDelete);
					break;
				case 4:
					orderManager.display();
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