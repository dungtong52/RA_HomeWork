import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		Category[] categoryList = new Category[10];
		int categoryCount = 0;

		do {
			System.out.println("---------- MENU ----------");
			System.out.println("1. Thêm mới danh mục");
			System.out.println("2. Hiển thị danh sách danh mục");
			System.out.println("3. Cập nhật danh mục");
			System.out.println("4. Xóa danh mục");
			System.out.println("5. Tìm kiếm danh mục theo tên");
			System.out.println("6. Thoát");
			System.out.print("Nhập lựa chọn của bạn: ");

			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
				case 1:
					if (categoryCount >= categoryList.length) {
						System.out.println("Danh sách danh mục đã đầy. Không thể thêm sản phẩm mới");
						break;
					}
					System.out.printf("Nhập ID danh mục: ");
					String id = scanner.nextLine();

					// Kiểm tra tồn tại ID này hay chưa
					boolean isDuplicate = false;
					for (int i = 0; i < categoryCount; i++) {
						if (categoryList[i].getId().equals(id)) {
							isDuplicate = true;
							break;
						}
					}
					if (isDuplicate) {
						System.err.println("ID này đã tồn tại!");
					} else {
						System.out.printf("Nhập tên danh mục: ");
						String name = scanner.nextLine();
						System.out.printf("Nhập mô tả danh mục: ");
						String description = scanner.nextLine();

						Category newCategory = new Category(id, name, description);
						categoryList[categoryCount] = newCategory;
						categoryCount++;
						System.out.println("Danh mục được thêm thành công");
					}
					break;

				case 2:
					// Hiển thị danh sách danh mục
					if (categoryCount == 0) {
						System.out.println("Chưa có danh mục nào trong danh sách.");
					} else {
						System.out.println("------ Danh sách danh mục ------");
						for (int i = 0; i < categoryCount; i++) {
							categoryList[i].display();
						}
					}
					break;

				case 3:
					// Cập nhật danh mục
					if (categoryCount == 0) {
						System.out.println("Chưa có danh mục nào để cập nhật.");
						break;
					}

					System.out.print("Nhập ID cần update: ");
					String idUpdate = scanner.nextLine();

					// Kiểm tra có tồn tại ID này không
					boolean isExistForUpdate = false;
					for (int i = 0; i < categoryCount; i++) {
						if (categoryList[i].getId().equals(idUpdate)) {
							System.out.print("Nhập tên cần mới: ");
							String nameUpdate = scanner.nextLine();
							System.out.print("Nhập mô tả cần mới: ");
							String descriptionUpdate = scanner.nextLine();

							Category categoryUpdate = new Category(idUpdate, nameUpdate, descriptionUpdate);
							categoryList[i] = categoryUpdate;
							System.out.println("Danh mục cập nhật thành công.");

							isExistForUpdate = true;
							break;
						}
					}
					if (!isExistForUpdate) {
						System.err.println("Không tìm thấy danh mục có ID " + idUpdate);
					}
					break;

				case 4:
					// Xóa danh mục
					if (categoryCount == 0) {
						System.out.println("Chưa có danh mục nào để xóa.");
						break;
					}

					System.out.print("Nhập ID cần xóa: ");
					String idDelete = scanner.nextLine();

					// Tìm index của ID cần xóa
					int deleteIndex = -1;
					for (int i = 0; i < categoryCount; i++) {
						if (categoryList[i].getId().equals(idDelete)) {
							deleteIndex = i;
							break;
						}
					}

					if (deleteIndex != -1) {
						for (int i = deleteIndex; i < categoryCount - 1; i++) {
							categoryList[i] = categoryList[i + 1];
						}
						categoryList[categoryCount - 1] = null;
						categoryCount--;
						System.out.println("Xóa danh mục thành công.");
					} else {
						System.err.println("Không tìm thấy danh mục có ID " + idDelete);
					}
					break;

				case 5:
					// Tìm kiếm danh mục theo tên
					if (categoryCount == 0) {
						System.out.println("Chưa có danh mục nào để tìm kiếm.");
						break;
					}

					System.out.print("Nhập tên danh mục cần tìm: ");
					String nameSearch = scanner.nextLine();

					boolean foundCategory = false;
					for (int i = 0; i < categoryCount; i++) {
						if (categoryList[i].getName().toLowerCase().contains(nameSearch.toLowerCase())) {
							categoryList[i].display();
							foundCategory = true;
						}
					}

					if (!foundCategory) {
						System.out.println("Không tìm thấy danh mục nào");
					}

					break;
				case 6:
					System.exit(0);
				default:
					System.err.println("Nhập các số từ 1-6");
			}
		} while (true);
	}
}
