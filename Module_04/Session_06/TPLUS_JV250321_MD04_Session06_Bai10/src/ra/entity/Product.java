package ra.entity;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Product {
	private String productId;
	private String productName;
	private float price;
	private String description;
	private int catalogId;
	private int productStatus;

	public Product () {
	}

	public Product (String productId, String productName, float price, String description, int catalogId, int productStatus) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.description = description;
		this.catalogId = catalogId;
		this.productStatus = productStatus;
	}

	public String getProductId () {
		return productId;
	}

	public void setProductId (String productId) {
		this.productId = productId;
	}

	public String getProductName () {
		return productName;
	}

	public void setProductName (String productName) {
		this.productName = productName;
	}

	public float getPrice () {
		return price;
	}

	public void setPrice (float price) {
		this.price = price;
	}

	public String getDescription () {
		return description;
	}

	public void setDescription (String description) {
		this.description = description;
	}

	public int getCatalogId () {
		return catalogId;
	}

	public void setCatalogId (int catalogId) {
		this.catalogId = catalogId;
	}

	public int getProductStatus () {
		return productStatus;
	}

	public void setProductStatus (int productStatus) {
		this.productStatus = productStatus;
	}

	public void inputData (Scanner scanner, ShopData data) {
		// Mã sản phẩm đồ uống, gồm 4 ký tự bắt đầu là một trong 3 ký tự (C: các đồ uống là café, S: các đồ uống là sinh tố, A: các đồ ăn nhanh),
		// không được trùng lặp
		do {
			System.out.print("Nhập vào mã sản phẩm: ");
			String productIdInput = scanner.nextLine();
			String regexId = "[ASC][0-9]{3}";
			if (Pattern.matches(regexId, productIdInput)) {
				// Kiểm tra trùng lặp
				boolean isDuplicate = false;
				for (int i = 0; i < data.currentProd; i++) {
					if (data.productList[i].getProductId().equals(productIdInput)) {
						isDuplicate = true;
						break;
					}
				}
				if (isDuplicate) {
					System.out.println("Mã sản phẩm đã tồn tại. Vui lòng nhập lại mã");
				} else {
					setProductId(productIdInput);
					break;
				}
			} else {
				System.err.println("Sai định dạng mã sản phẩm. Vui lòng nhập lại!");
			}
		} while (true);

		// Tên sản phẩm đồ uống, có từ 10-50 ký tự, không được trùng lặp
		do {
			System.out.print("Nhập vào tên sản phẩm: ");
			String productNameInput = scanner.nextLine();
			if (productNameInput.length() < 10 || productNameInput.length() > 50) {
				System.err.println("Tên sản phẩm đồ uống, có từ 10-50 ký tự. Vui lòng nhập lại!");
			} else {
				// Kiểm tra trùng lặp tên
				boolean isDuplicate = false;
				for (int i = 0; i < data.currentProd; i++) {
					if (data.productList[i].getProductName().equals(productNameInput)) {
						isDuplicate = true;
						break;
					}
				}
				if (isDuplicate) {
					System.out.println("Tên sản phẩm đã tồn tại. Vui lòng nhập tên khác");
				} else {
					setProductName(productNameInput);
					break;
				}
			}
		} while (true);

		// Giá sản phẩm có giá trị lớn hơn 0
		do {
			System.out.print("Nhập vào giá sản phẩm: ");
			float priceInput = Float.parseFloat(scanner.nextLine());
			if (priceInput <= 0) {
				System.err.println("Sản phẩm có giá lớn hơn 0. Vui lòng nhập lại");
			} else {
				setPrice(priceInput);
				break;
			}
		} while (true);

		// Mô tả sản phẩm
		System.out.print("Nhập vào mô tả sản phẩm: ");
		setDescription(scanner.nextLine());

		// Nhập ID Category
		do {
			System.out.println("Danh sách danh mục hiện có:");
			for (int i = 0; i < data.currentCate; i++) {
				System.out.printf("CatalogId: %2d, CatalogName: %10s\n",
						data.categoryList[i].getCatalogId(), data.categoryList[i].getCatalogName());
			}
			System.out.println("Nếu chưa có danh mục trong danh sách, nhập 'n'.\n Nếu không nhập ký tự bất kỳ.");
			char choice = scanner.nextLine().trim().toLowerCase().charAt(0);
			if (choice == 'n') {
				// Tạo mới category, thêm vào mảng Categories
				Categories newCategory = new Categories();
				newCategory.inputData(scanner, data);
				data.categoryList[data.currentCate] = newCategory;
				data.currentCate++;

				// Thêm catalogId cho sản phẩm
				setCatalogId(newCategory.getCatalogId());
				break;
			} else {
				System.out.print("Nhập ID danh mục cho sản phẩm: ");
				int idCategory = Integer.parseInt(scanner.nextLine());

				// Kiểm tra ID có tồn tại hay không
				boolean isFound = false;
				for (int i = 0; i < data.currentCate; i++) {
					if (data.categoryList[i].getCatalogId() == idCategory) {
						isFound = true;
						break;
					}
				}
				if (!isFound) {
					System.err.println("ID không tồn tại trong danh sách danh mục. Vui lòng nhập lại!");
					continue;
				}
			}
		} while (true);

		// Trạng thái sản phẩm, chỉ nhận 1 trong các trạng thái sau (0: Đang bán – 1: Hết hàng – 2: Không bán)
		do {
			System.out.print("Nhập vào trạng thái sản phẩm (0: Đang bán – 1: Hết hàng – 2: Không bán): ");
			int statusInput = Integer.parseInt(scanner.nextLine());
			if (statusInput == 0 || statusInput == 1 || statusInput == 2) {
				setProductStatus(statusInput);
				break;
			} else {
				System.err.println("Nhập sai ký tự. Vui lòng nhập lại!");
			}
		} while (true);
	}

	public String displayData () {
		return "productId: " + productId +
				", productName: " + productName +
				", price: " + String.format("%,.1f", price) +
				", description: " + description +
				", catalogId: " + catalogId +
				", productStatus: " + productStatus;
	}
}
