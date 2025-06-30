package ra.presentation;

import ra.entity.Categories;
import ra.entity.Product;
import ra.entity.ShopData;

import java.util.Scanner;

public class ShopManagement {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		ShopData data = new ShopData();
		do {
			System.out.println(
					"\n****************** SHOP MENU *******************\n" +
							"1. Quản lý danh mục sản phẩm\n" +
							"2. Quản lý sản phẩm\n" +
							"3. Thoát");
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
				case 1:
					// Quản lý danh mục sản phẩm
					categoriesManagement(scanner, data);
					break;
				case 2:
					// Quản lý sản phẩm
					productsManagement(scanner, data);
					break;
				case 3:
					System.exit(0);
				default:
					System.err.println("Hãy nhập 1 số trong khoảng 1-3");
			}
		} while (true);
	}

	// Chức năng 1: Quản lý danh mục sản phẩm
	public static void categoriesManagement (Scanner scanner, ShopData data) {
		do {
			System.out.println(
					"\n******************** CATEGORIES MENU ***********\n" +
							"1. Nhập thông tin các danh mục\n" +
							"2. Hiển thị thông tin các danh mục\n" +
							"3. Cập nhật thông tin danh mục\n" +
							"4. Xóa danh mục\n" +
							"5. Cập nhật trạng thái danh mục\n" +
							"6. Thoát");
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
				case 1:
					// Nhập thông tin các danh mục
					System.out.print("Nhập số danh mục nhập dữ liệu muốn nhập: ");
					int n = Integer.parseInt(scanner.nextLine());
					for (int i = 0; i < n; i++) {
						System.out.printf("Nhập thông tin cho Category thứ %d\n", i + 1);
						Categories newCategory = new Categories();
						newCategory.inputData(scanner, data);
						data.categoryList[data.currentCate] = newCategory;
						data.currentCate++;
						System.out.println("--------------");
					}
					System.out.printf("Đã thêm vào mảng Categories. Mảng hiện có %d danh mục\n", data.currentCate);
					break;
				case 2:
					// Hiển thị thông tin các danh mục
					if (data.currentCate == 0) {
						System.out.println("Danh sách chưa có danh mục nào.");
					} else {
						for (int i = 0; i < data.currentCate; i++) {
							System.out.println(data.categoryList[i].displayData());
						}
					}
					break;
				case 3:
					// Cập nhật thông tin danh mục
					System.out.print("Nhập mã danh mục cần cập nhật thông tin: ");
					int catalogIdForUpdate = Integer.parseInt(scanner.nextLine());
					int indexUpdate = -1;
					for (int i = 0; i < data.currentCate; i++) {
						if (data.categoryList[i].getCatalogId() == catalogIdForUpdate) {
							indexUpdate = i;
							break;
						}
					}
					if (indexUpdate == -1) {
						System.err.println("Mã danh mục không đúng. Vui lòng nhập lại");
					} else {
						data.categoryList[indexUpdate].inputData(scanner, data);
						System.out.println("Cập nhật thành công!");
					}
					break;
				case 4:
					// Xóa danh mục
					System.out.print("Nhập mã danh mục cần xóa: ");
					int catalogIdForDelete = Integer.parseInt(scanner.nextLine());
					int indexDelete = -1;
					for (int i = 0; i < data.currentCate; i++) {
						if (data.categoryList[i].getCatalogId() == catalogIdForDelete) {
							indexDelete = i;
							break;
						}
					}
					if (indexDelete == -1) {
						System.err.println("Mã danh mục không đúng. Vui lòng nhập lại");
					} else {
						// Kiểm tra danh mục có chứa sản phẩm không
						boolean hasProduct = false;
						for (int i = 0; i < data.currentProd; i++) {
							if (data.productList[i].getCatalogId() == catalogIdForDelete) {
								hasProduct = true;
								break;
							}
						}
						if (hasProduct) {
							System.out.println("Không thể xóa danh mục này do có chứa sản phẩm");
							break;
						} else {
							for (int i = indexDelete; i < data.currentCate - 1; i++) {
								data.categoryList[i] = data.categoryList[i + 1];
							}
							data.categoryList[data.currentCate - 1] = null;
							data.currentCate--;
							System.out.println("Xóa thành công!");
						}
					}
					break;
				case 5:
					// Cập nhật trạng thái danh mục
					System.out.print("Nhập mã danh mục cần xóa: ");
					int catalogIdForStatus = Integer.parseInt(scanner.nextLine());
					int indexToggleStatus = -1;
					for (int i = 0; i < data.currentCate; i++) {
						if (data.categoryList[i].getCatalogId() == catalogIdForStatus) {
							indexToggleStatus = i;
							break;
						}
					}
					if (indexToggleStatus == -1) {
						System.err.println("Mã danh mục không đúng. Vui lòng nhập lại");
					} else {
						data.categoryList[indexToggleStatus].setCatalogStatus(!data.categoryList[indexToggleStatus].isCatalogStatus());
						System.out.printf("Cập nhật thành công. Catalog Status hiện tại của ID %d là: %b",
								catalogIdForStatus, data.categoryList[indexToggleStatus].isCatalogStatus());
					}
					break;
				case 6:
					return;
				default:
					System.err.println("Hãy nhập 1 số trong khoảng 1-6");
			}
		} while (true);
	}

	// Chức năng 2: Quản lý sản phẩm
	public static void productsManagement (Scanner scanner, ShopData data) {
		do {
			System.out.println(
					"\n******************* PRODUCT MANAGEMENT *****************\n" +
							"1. Nhập thông tin các sản phẩm\n" +
							"2. Hiển thị thông tin các sản phẩm\n" +
							"3. Sắp xếp các sản phẩm theo giá\n" +
							"4. Cập nhật thông tin sản phẩm theo mã sản phẩm\n" +
							"5. Xóa sản phẩm theo mã sản phẩm\n" +
							"6. Tìm kiếm các sản phẩm theo tên sản phẩm\n" +
							"7. Tìm kiếm sản phẩm trong khoảng giá a – b (a,b nhập từ bàn phím)\n" +
							"8. Thoát");
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
				case 1:
					// Nhập thông tin các sản phẩm
					System.out.print("Nhập số sản phẩm muốn nhập: ");
					int n = Integer.parseInt(scanner.nextLine());
					for (int i = 0; i < n; i++) {
						System.out.printf("Nhập thông tin cho sản phẩm thứ %d\n", i + 1);
						Product newProduct = new Product();
						newProduct.inputData(scanner, data);
						data.productList[data.currentProd] = newProduct;
						data.currentProd++;
						System.out.println("--------------");
					}
					System.out.printf("Đã thêm sản phẩm vào mảng Product. Mảng hiện có %d sản phẩm\n", data.currentProd);
					break;
				case 2:
					// Hiển thị thông tin các sản phẩm
					if (data.currentProd == 0) {
						System.out.println("Danh sách chưa có sản phẩm nào");
					} else {
						for (int i = 0; i < data.currentProd; i++) {
							System.out.println(data.productList[i].displayData());
						}
					}
					break;
				case 3:
					// Sắp xếp các sản phẩm theo giá tăng dần
					for (int i = 0; i < data.currentProd - 1; i++) {
						for (int j = i + 1; j < data.currentProd; j++) {
							if (data.productList[i].getPrice() > data.productList[j].getPrice()) {
								Product temp = data.productList[i];
								data.productList[i] = data.productList[j];
								data.productList[j] = temp;
							}
						}
					}
					System.out.println("Mảng sản phẩm đã được sắp xếp theo giá tăng dần");
					break;
				case 4:
					// Cập nhật thông tin sản phẩm theo mã sản phẩm
					System.out.print("Nhập mã sẩn phẩm cần cập nhật thông tin: ");
					String productIdForUpdate = scanner.nextLine();
					int indexUpdate = -1;
					for (int i = 0; i < data.currentProd; i++) {
						if (data.productList[i].getProductId().equals(productIdForUpdate)) {
							indexUpdate = i;
							break;
						}
					}
					if (indexUpdate == -1) {
						System.err.println("Mã sản phẩm không đúng. Vui lòng nhập lại");
					} else {
						data.productList[indexUpdate].inputData(scanner, data);
						System.out.println("Cập nhật thành công!");
					}
					break;
				case 5:
					// Xóa sản phẩm theo mã sản phẩm
					System.out.print("Nhập mã sản phẩm cần xóa: ");
					String productIdForDelete = scanner.nextLine();
					int indexDelete = -1;
					for (int i = 0; i < data.currentProd; i++) {
						if (data.productList[i].getProductId().equals(productIdForDelete)) {
							indexDelete = i;
							break;
						}
					}
					if (indexDelete == -1) {
						System.err.println("Mã sản phẩm không đúng. Vui lòng nhập lại");
					} else {
						for (int i = indexDelete; i < data.currentProd - 1; i++) {
							data.productList[i] = data.productList[i + 1];
						}
						data.productList[data.currentProd - 1] = null;
						data.currentProd--;
						System.out.println("Xóa thành công!");
					}
					break;
				case 6:
					// Tìm kiếm các sản phẩm theo tên sản phẩm
					System.out.print("Nhập từ khóa tên sản phẩm: ");
					String key = scanner.nextLine();
					for (int i = 0; i < data.currentProd; i++) {
						if (data.productList[i].getProductName().toLowerCase().contains(key.toLowerCase())) {
							System.out.println(data.productList[i].displayData());
						}
					}
					break;
				case 7:
					// Tìm kiếm sản phẩm trong khoảng giá a – b (a,b nhập từ bàn phím)
					System.out.print("Nhập giá sản phẩm thấp nhất muốn tìm: ");
					float minPrice = Float.parseFloat(scanner.nextLine());
					System.out.print("Nhập giá sản phẩm cao nhất muốn tìm: ");
					float maxPrice = Float.parseFloat(scanner.nextLine());
					System.out.printf("Các sản phẩm có giá trong khoảng %.1f - %.1f\n", minPrice, maxPrice);
					for (int i = 0; i < data.currentProd; i++) {
						if (data.productList[i].getPrice() >= minPrice && data.productList[i].getPrice() <= maxPrice) {
							System.out.println(data.productList[i].displayData());
						}
					}
					break;
				case 8:
					return;
				default:
					System.err.println("Hãy nhập 1 số trong khoảng 1-8");
			}
		} while (true);
	}
}
