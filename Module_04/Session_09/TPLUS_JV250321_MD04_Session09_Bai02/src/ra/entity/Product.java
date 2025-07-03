package ra.entity;

import ra.validator.Validation;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Product implements IShop {
	private String productId;
	private String productName;
	private float price;
	private String descriptions;
	private int catalogId;
	private int status;

	public Product () {
	}

	public Product (String productId, String productName, float price, String descriptions, int catalogId, int status) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.descriptions = descriptions;
		this.catalogId = catalogId;
		this.status = status;
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

	public String getDescriptions () {
		return descriptions;
	}

	public void setDescriptions (String descriptions) {
		this.descriptions = descriptions;
	}

	public int getCatalogId () {
		return catalogId;
	}

	public void setCatalogId (int catalogId) {
		this.catalogId = catalogId;
	}

	public int isStatus () {
		return status;
	}

	public void setStatus (int status) {
		this.status = status;
	}

	@Override
	public void inputData (Scanner scanner) {
		this.productId = inputProductId(scanner);
		this.productName = inputProductName(scanner);
		this.price = inputPrice(scanner);
		this.descriptions = inputDescription(scanner);
		this.catalogId = inputCatalogId(scanner);
		this.status = inputStatus(scanner);
	}

	public String inputProductId (Scanner scanner) {
		String productIdRegex = "[CSA]\\d{3}";
		System.out.print("Nhập vào mã sản phẩm: ");
		do {
			String productId = scanner.nextLine();
			if (!Validation.isEmpty(productId)) {
				if (Pattern.matches(productIdRegex, productId)) {
					boolean isExists = false;
					for (int i = 0; i < ProductManagement.currentProduct; i++) {
						if (ProductManagement.products[i].getProductId().equals(productId)) {
							isExists = true;
							break;
						}
					}
					if (!isExists) {
						return productId;
					}
					System.err.println("Mã sản phẩm đã tồn tại!");
				}
				System.err.println("Mã sản phẩm sai định dạng!");
			}
			System.err.println("Vui lòng nhập vào mã sản phẩm!");
		} while (true);
	}

	public String inputProductName (Scanner scanner) {
		System.out.print("Nhập vào tên sản phẩm: ");
		do {
			String productName = scanner.nextLine();
			if (!Validation.isEmpty(productName)) {
				if (productName.length() >= 10 && productName.length() <= 50) {
					boolean isExits = false;
					for (int i = 0; i < ProductManagement.currentProduct; i++) {
						if (ProductManagement.products[i].getProductName().equalsIgnoreCase(productName.trim())) {
							isExits = true;
							break;
						}
					}
					if (!isExits) {
						return productName;
					}
					System.err.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại");
				} else {
					System.err.println("Tên sản phẩm có độ dài từ 10-50 ký tự");
				}
			} else {
				System.err.println("Vui lòng nhập vào tên sản phẩm!");
			}
		} while (true);
	}

	public float inputPrice (Scanner scanner) {
		System.out.print("Nhập vào giá sản phẩm: ");
		do {
			if (scanner.hasNextFloat()) {
				float price = Float.parseFloat(scanner.nextLine());
				if (price > 0) {
					return price;
				} else {
					System.err.println("Gía sản phẩm phải lớn hơn 0!");
				}
			} else {
				System.err.println("Nhập vào giá sản phẩm là 1 số thực!");
			}
		} while (true);
	}

	public String inputDescription (Scanner scanner) {
		System.out.print("Nhập vào mô tả sản phẩm:");
		do {
			String description = scanner.nextLine();
			if (!Validation.isEmpty(description)) {
				return description;
			}
			System.err.println("Vui lòng nhập vào mô tả của sản phẩm!");
		} while (true);
	}

	public int inputCatalogId (Scanner scanner) {
		System.out.println("Danh sách danh mục đang hoạt động:");
		for (int i = 0; i < CategoryManagement.currentCatalog; i++) {
			System.out.printf("%d. %s\n", CategoryManagement.categories[i].getCatalogId(), CategoryManagement.categories[i].getCatalogName());
		}
		System.out.print("Nhập vào mã danh mục: ");
		int catalogChoice = Integer.parseInt(scanner.nextLine());
		return catalogChoice;
	}

	public int inputStatus (Scanner scanner) {
		System.out.println("Nhập vào trạng thái sản phẩm");
		System.out.println("1. Đang bán");
		System.out.println("2. Hết hàng");
		System.out.println("3. Không bán");
		return Integer.parseInt(scanner.nextLine()) - 1;
	}

	@Override
	public void displayData () {
		System.out.printf("Mã sản phẩm: %s - Tên sản phẩm: %s - Giá: %.1f\n",
				this.productId, this.productName, this.price);
		System.out.printf("Mô tả: %s - Tên danh mục: %d - Trạng thái: %s\n",
				this.descriptions, Categories.findCategoryNameById(this.catalogId),
				this.status == 0 ? "Đang bán" : (this.status == 1 ? "Hết hàng" : "Không bán"));
	}
}
