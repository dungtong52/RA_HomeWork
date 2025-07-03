package ra.entity;

import java.util.Scanner;

public class ProductManagement {
	public static Product[] products = new Product[100];
	public static int currentProduct = 0;

	public void displayProductMenu (Scanner scanner) {
		boolean isExit = false;
		do {
			System.out.println("******************* PRODUCT MANAGEMENT **********************");
			System.out.println("1. Nhập thông tin các sản phẩm");
			System.out.println("2. Hiển thị thông tin các sản phẩm");
			System.out.println("3. Sắp xếp các sản phẩm theo giá");
			System.out.println("4. Cập nhật thông tin sản phẩm theo mã sản phẩm");
			System.out.println("5. Xóa sản phẩm theo mã sản phẩm");
			System.out.println("6. Tìm kiếm các sản phẩm theo tên sản phẩm");
			System.out.println("7. Tìm kiếm sản phẩm trong khoảng giá a - b (a, b nhập từ bàn phím)");
			System.out.println("8. Thoát");
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
				case 1:
					addProduct(scanner);
					break;
				case 2:
					displayProductList();
					break;
				case 3:
					sortProductByPrice();
					break;
				case 4:
					updateProduct(scanner);
					break;
				case 5:
					deleteProductById(scanner);
					break;
				case 6:
					findProductByName(scanner);
					break;
				case 7:
					findProductBetweenPrice(scanner);
					break;
				case 8:
					isExit = true;
				default:
					System.err.println("Nhập vào số trong khoảng 1-8");
			}
		} while (!isExit);
	}

	public void addProduct (Scanner scanner) {
		System.out.print("Nhập vào số lượng sản phẩm muốn thêm: ");
		int countProduct = Integer.parseInt(scanner.nextLine());

		for (int i = 0; i < countProduct; i++) {
			products[currentProduct] = new Product();
			products[currentProduct].inputData(scanner);
			currentProduct++;
		}
		System.out.println("Đã thêm thành công");
	}

	public void displayProductList () {
		for (int i = 0; i < currentProduct; i++) {
			products[i].displayData();
		}
	}

	public void sortProductByPrice () {
		for (int i = 0; i < currentProduct - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < currentProduct; j++) {
				if (products[minIndex].getPrice() > products[j].getPrice()) {
					minIndex = j;
				}
			}

			if (minIndex != i) {
				Product temp = products[minIndex];
				products[minIndex] = products[i];
				products[i] = temp;
			}
		}
		System.out.println("Đã sắp xếp theo giá");
	}

	public void updateProduct (Scanner scanner) {
		System.out.print("Nhập vào mã sản phẩm muốn cập nhật: ");
		String productIdForUpdate = scanner.nextLine();
		int indexIdUpdate = findIndexById(productIdForUpdate);

		if (indexIdUpdate != -1) {
			boolean isExit = false;
			do {
				System.out.println("1. Cập nhật tên sản phẩm");
				System.out.println("2. Cập nhật giá sản phẩm");
				System.out.println("3. Cập nhật mô tả sản phẩm");
				System.out.println("4. Cập nhật mã danh mục mà sản phẩm thuộc về");
				System.out.println("5. Cập nhật trạng thái sản phẩm");
				System.out.println("6. Thoát cập nhật");
				int choice = Integer.parseInt(scanner.nextLine());
				switch (choice) {
					case 1:
						products[indexIdUpdate].inputProductName(scanner);
						break;
					case 2:
						products[indexIdUpdate].inputPrice(scanner);
						break;
					case 3:
						products[indexIdUpdate].inputDescription(scanner);
						break;
					case 4:
						products[indexIdUpdate].inputCatalogId(scanner);
						break;
					case 5:
						products[indexIdUpdate].inputStatus(scanner);
						break;
					case 6:
						isExit = true;
						break;
					default:
						System.err.println("Nhập vào số trong khoảng 1-6");
				}
			} while (!isExit);
		} else {
			System.err.println("Mã danh mục không tồn tại");
		}
	}

	public int findIndexById (String id) {
		for (int i = 0; i < currentProduct; i++) {
			if (products[i].getProductId().equalsIgnoreCase(id)) {
				return i;
			}
		}
		return -1;
	}

	public void deleteProductById (Scanner scanner) {
		System.out.printf("Nhập vào mã sản phẩm muốn xóa: ");
		String productIdForDelete = scanner.nextLine();
		int indexIdDelete = findIndexById(productIdForDelete);
		if (indexIdDelete != -1) {
			for (int i = indexIdDelete; i < currentProduct - 1; i++) {
				products[i] = products[i + 1];
			}
			products[currentProduct - 1] = null;
			currentProduct--;
			System.out.printf("Xóa thành công!");
		} else {
			System.err.println("Mã sản phẩm không tồn tại");
		}
	}

	public void findProductByName (Scanner scanner) {
		int count = 0;
		System.out.print("Nhập tên sản phẩm muốn tìm kiếm: ");
		String nameSearch = scanner.nextLine();

		for (int i = 0; i < currentProduct; i++) {
			if (products[i].getProductName().equalsIgnoreCase(nameSearch.trim())) {
				products[i].displayData();
				count++;
			}
		}
		System.out.printf("Có %d sản phẩm có tên chứa %s\n", count, nameSearch);
	}

	public void findProductBetweenPrice (Scanner scanner) {
		int count = 0;
		System.out.print("Nhập giá nhỏ nhất muốn tìm: ");
		float fromPrice = Float.parseFloat(scanner.nextLine());

		System.out.print("Nhập giá lớn nhất muốn tìm: ");
		float toPrice = Float.parseFloat(scanner.nextLine());

		System.out.println("-----------------------------------");
		for (int i = 0; i < currentProduct; i++) {
			if (products[i].getPrice() >= fromPrice && products[i].getPrice() <= toPrice) {
				products[i].displayData();
				count++;
			}
		}
		System.out.printf("Có %d sản phẩm có giá trong khoảng %.1f - %.1f\n", count, fromPrice, toPrice);
	}

}
