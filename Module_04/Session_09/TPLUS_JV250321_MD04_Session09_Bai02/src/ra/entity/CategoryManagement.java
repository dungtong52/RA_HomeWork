package ra.entity;

import java.util.Scanner;

public class CategoryManagement {
	public static Categories[] categories = new Categories[100];
	public static int currentCatalog = 0;

	public void displayCategoryMenu (Scanner scanner) {
		boolean isExit = false;
		do {
			System.out.println("********************** CATEGORY MENU ************************");
			System.out.println("1. Nhập thông tin các danh mục");
			System.out.println("2. Hiển thị thông tin các danh mục");
			System.out.println("3. Cập nhật thông tin danh mục");
			System.out.println("4. Xóa danh mục");
			System.out.println("5. Cập nhật trạng thái danh mục");
			System.out.println("6. Thoát");
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
				case 1:
					addCategories(scanner);
					break;
				case 2:
					displayCategoriesList();
					break;
				case 3:
					updateCategories(scanner);
					break;
				case 4:
					deleteCatalog(scanner);
					break;
				case 5:
					updateStatus(scanner);
					break;
				case 6:
					isExit = true;
					break;
				default:
					System.err.println("Nhập vào số trong khoảng 1-6");
			}
		} while (!isExit);
	}

	public void addCategories (Scanner scanner) {
		System.out.print("Nhập vào số lượng danh mục muốn thêm: ");
		int countCategories = Integer.parseInt(scanner.nextLine());

		for (int i = 0; i < countCategories; i++) {
			categories[currentCatalog] = new Categories();
			categories[currentCatalog].inputData(scanner);
			currentCatalog++;
		}
		System.out.println("Đã thêm thành công");
	}

	public void displayCategoriesList () {
		for (int i = 0; i < currentCatalog; i++) {
			categories[i].displayData();
		}
	}

	public int findIndexById (int id) {
		for (int i = 0; i < currentCatalog; i++) {
			if (categories[i].getCatalogId() == id) {
				return i;
			}
		}
		return -1;
	}

	public void updateCategories (Scanner scanner) {
		System.out.print("Nhâp vào mã danh mục cần cập nhật: ");

		int catalogId = Integer.parseInt(scanner.nextLine());
		int indexUpdate = findIndexById(catalogId);
		if (indexUpdate != -1) {
			boolean isExit = false;
			do {
				System.out.println("1. Cập nhật tên danh mục");
				System.out.println("2. Cập nhật mô tả danh mục");
				System.out.println("3. Cập nhật trạng thái danh mục");
				System.out.println("4. Thoát");
				System.out.print("Lựa chọn: ");
				int choice = Integer.parseInt(scanner.nextLine());
				switch (choice) {
					case 1:
						categories[indexUpdate].setCatalogName(categories[indexUpdate].inputCatalogName(scanner));
						break;
					case 2:
						categories[indexUpdate].setDescription(categories[indexUpdate].inputDescription(scanner));
						break;
					case 3:
						categories[indexUpdate].setCatalogStatus(categories[indexUpdate].inputStatus(scanner));
						break;
					case 4:
						isExit = true;
						break;
					default:
						System.err.println("Nhập vào số trong khoảng 1-4");
				}
			} while (!isExit);
		} else {
			System.err.println("Mã danh mục không tồn tại");
		}
	}

	public void deleteCatalog (Scanner scanner) {
		System.out.print("Nhập vào mã danh mục muốn xóa: ");
		int idDelete = Integer.parseInt(scanner.nextLine());
		int indexDelete = findIndexById(idDelete);
		if (indexDelete != -1) {
			boolean hasProduct = false;
			for (int i = 0; i < ProductManagement.currentProduct; i++) {
				if (ProductManagement.products[i].getCatalogId() == idDelete) {
					hasProduct = true;
					break;
				}
			}
			if (!hasProduct) {
				for (int i = indexDelete; i < currentCatalog - 1; i++) {
					categories[i] = categories[i + 1];
				}
				categories[currentCatalog - 1] = null;
				currentCatalog--;
				System.out.println("Xóa thành công");
			} else {
				System.err.println("Danh mục đang chứa sản phẩm, không thể xóa được");
			}
		} else {
			System.err.println("Mã danh mục không tồn tại");
		}
	}

	public void updateStatus (Scanner scanner) {
		System.out.print("Nhập vào mã danh mục muốn cập nhật trạng thái: ");
		int idUpdateStatus = Integer.parseInt(scanner.nextLine());
		int indexUpdatestatus = findIndexById(idUpdateStatus);
		if (indexUpdatestatus != -1) {
			categories[indexUpdatestatus].setCatalogStatus(!categories[indexUpdatestatus].isCatalogStatus());
			System.out.println("Cập nhật thành công");
		} else {
			System.err.println("Mã danh mục không tồn tại");
		}
	}
}
