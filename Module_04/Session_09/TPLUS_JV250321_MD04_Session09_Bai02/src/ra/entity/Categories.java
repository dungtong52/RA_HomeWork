package ra.entity;

import java.util.Scanner;

import ra.validator.Validation;

public class Categories implements IShop {
	private int catalogId;
	private String catalogName;
	private String description;
	private boolean catalogStatus;

	public Categories () {
	}

	public Categories (int catalogId, String catalogName, String description, boolean catalogStatus) {
		this.catalogId = catalogId;
		this.catalogName = catalogName;
		this.description = description;
		this.catalogStatus = catalogStatus;
	}

	public int getCatalogId () {
		return catalogId;
	}

	public void setCatalogId (int catalogId) {
		this.catalogId = catalogId;
	}

	public String getCatalogName () {
		return catalogName;
	}

	public void setCatalogName (String catalogName) {
		this.catalogName = catalogName;
	}

	public String getDescription () {
		return description;
	}

	public void setDescription (String description) {
		this.description = description;
	}

	public boolean isCatalogStatus () {
		return catalogStatus;
	}

	public void setCatalogStatus (boolean catalogStatus) {
		this.catalogStatus = catalogStatus;
	}

	@Override
	public void inputData (Scanner scanner) {
		this.catalogId = inputCatalogId(scanner);
		this.catalogName = inputCatalogName(scanner);
		this.description = inputDescription(scanner);
		this.catalogStatus = inputStatus(scanner);
	}

	public int inputCatalogId (Scanner scanner) {
		// Mã danh mục tự tăng --> max catalogID + 1
		if (CategoryManagement.currentCatalog == 0) {
			return 1;
		}
		int maxCatalogId = CategoryManagement.categories[0].getCatalogId();
		for (int i = 0; i < CategoryManagement.currentCatalog; i++) {
			if (CategoryManagement.categories[i].getCatalogId() > maxCatalogId) {
				maxCatalogId = CategoryManagement.categories[i].getCatalogId();
			}
		}
		return maxCatalogId + 1;
	}

	public String inputCatalogName (Scanner scanner) {
		System.out.print("Nhập vào tên danh mục: ");
		do {
			String catalogName = scanner.nextLine();
			if (!Validation.isEmpty(catalogName)) {
				if (catalogName.length() <= 50) {
					boolean isExists = false;
					for (int i = 0; i < CategoryManagement.currentCatalog; i++) {
						if (CategoryManagement.categories[i].getCatalogName().equalsIgnoreCase(catalogName)) {
							isExists = true;
							break;
						}
					}
					if (!isExists) {
						return catalogName;
					}
					System.err.println("Tên danh mục đã tồn tại!");
				}
				System.err.println("Nhập vào tên danh mục có độ dài tối đa 50 ký tự");
			}
			System.err.println("Vui lòng nhập vào tên của danh mục!");
		} while (true);
	}

	public String inputDescription (Scanner scanner) {
		System.out.print("Nhập vào mô tả danh mục: ");
		do {
			String description = scanner.nextLine();
			if (!Validation.isEmpty(description)) {
				return description;
			}
			System.err.println("Vui lòng nhập vào mô tả danh mục!");
		} while (true);
	}

	public boolean inputStatus (Scanner scanner) {
		System.out.print("Nhập vào trạng thái danh mục (true | false): ");
		do {
			String status = scanner.nextLine();
			if (!Validation.isEmpty(status)) {
				if (status.equalsIgnoreCase("true") || status.equalsIgnoreCase("false")) {
					return Boolean.parseBoolean(status);
				}
				System.err.println("Trạng thái nhập vào không hợp lệ (true | false)");
			}
			System.err.println("Vui lòng nhập vào trạng thái!");
		} while (true);
	}

	@Override
	public void displayData () {
		System.out.printf("Mã danh mục: %d - Tên danh mục: %s - Mô tả: %s - Trạng thái: %b\n",
				this.catalogId, this.catalogName, this.description, this.catalogStatus ? "Hoạt động" : "Không hoạt động");
	}

	public static String findCategoryNameById (int id) {
		for (int i = 0; i < CategoryManagement.currentCatalog; i++) {
			if (CategoryManagement.categories[i].getCatalogId() == id) {
				return CategoryManagement.categories[i].getCatalogName();
			}
		}
		return null;
	}

}
