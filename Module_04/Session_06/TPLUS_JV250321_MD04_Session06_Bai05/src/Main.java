import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		Product[] products = new Product[10];
		int productCount = 0;

		// MENU
		do {
			System.out.println("------ MENU ------");
			System.out.println("1. Thêm mới sản phẩm");
			System.out.println("2. Hiển thị danh sách sản phẩm");
			System.out.println("3. Cập nhật sản phẩm");
			System.out.println("4. Xóa sản phẩm");
			System.out.println("5. Thoát");
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
				case 1:
					// Thêm mới sản phẩm
					// kiểm tra productCount > products.length
					if (productCount >= products.length) {
						System.out.println("Danh sách sản phẩm đã đầy. Không thể thêm sản phẩm mới.");
						break;
					}
					System.out.println("--- Thêm sản phẩm mới ---");
					System.out.print("Nhập ID sản phẩm: ");
					String id = scanner.nextLine();

					// Kiểm tra ID trùng lặp
					boolean idDuplicate = false;
					for (int i = 0; i < productCount; i++) {
						if (products[i].getId().equals(id)) {
							idDuplicate = true;
							break;
						}
					}
					if (idDuplicate) {
						System.err.println("ID này đã tồn tại");
						break;
					}

					System.out.print("Nhập tên sản phẩm: ");
					String name = scanner.nextLine();

					System.out.print("Nhập giá sản phẩm: ");
					double price = Double.parseDouble(scanner.nextLine());  // Cần kiểm tra nhập vào đúng định dạng

					Product newProduct = new Product(id, name, price);
					products[productCount] = newProduct;
					productCount++;

					System.out.println("Sản phẩm đã được thêm thành công!");
					break;

				case 2:
					// Hiển thị danh sách sản phẩm
					if (productCount == 0)
						System.out.println("Chưa có sản phẩm nào trong danh sách");
					else {
						System.out.println("--- Danh sách sản phẩm ---");
						for (int i = 0; i < productCount; i++) {
							products[i].display();
							System.out.println("----------");
						}
					}
					break;

				case 3:
					// Cập nhật sản phẩm
					if (productCount == 0) {
						System.out.println("Chưa có sản phẩm nào để cập nhật.");
						break;
					}

					System.out.print("Nhập ID sản phẩm cần cập nhật: ");
					String idUpdate = scanner.nextLine();

					boolean foundForUpdate = false;
					for (int i = 0; i < productCount; i++) {
						if (products[i].getId().equals(idUpdate)) {
							System.out.print("Nhập tên mới: ");
							String nameUpdate = scanner.nextLine();

							System.out.print("Nhập giá mới: ");
							double priceUpdate = Double.parseDouble(scanner.nextLine()); // Cần kiểm tra nhập vào

							Product updateProduct = new Product(idUpdate, nameUpdate, priceUpdate);
							products[i] = updateProduct;

							System.out.println("Sản phẩm cập nhật thành công");
							foundForUpdate = true;
							break;
						}
					}

					if (!foundForUpdate) {
						System.err.println("Không tìm thấy sản phẩm có ID '" + idUpdate);
					}
					break;

				case 4:
					// Xóa sản phẩm
					if (productCount == 0) {
						System.out.println("Danh sách rỗng. Không có gì để xóa");
						break;
					}

					System.out.print("Nhập ID sản phẩm cần xóa:");
					String idDelete = scanner.nextLine();

					// Tìm chỉ số cho sản phẩm cần xóa
					int foundIndex = -1;
					for (int i = 0; i < productCount; i++) {
						if (products[i].getId().equals(idDelete)) {
							foundIndex = i;
							break;
						}
					}

					if (foundIndex != -1) {
						for (int i = foundIndex; i < productCount - 1; i++) {
							products[i] = products[i + 1];
						}
						products[productCount - 1] = null;
						productCount--;
						System.out.printf("Xóa sản phẩm có ID %s thành công\n", idDelete);
					} else {
						System.err.println("Không tìm thấy sản phẩm có ID '" + idDelete);
					}
					break;
				case 5:
					System.exit(0);
				default:
					System.err.println("Nhập vào số từ 1 đến 5!");
			}
		} while (true);

	}
}
