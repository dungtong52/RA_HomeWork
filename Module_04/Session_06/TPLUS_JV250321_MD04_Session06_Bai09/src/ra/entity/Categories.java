package ra.entity;

import java.util.Scanner;

public class Categories {
    private static int AUTO_ID = 1; // Tạo 1 biến đếm tự động tăng mỗi khi tạo constructor

    private int catalogId;
    private String catalogName;
    private String descriptions;
    private boolean catalogStatus;

    public Categories() {
        this.catalogId = AUTO_ID++;
    }

    public Categories(String catalogName, String descriptions, boolean catalogStatus) {
        this.catalogId = AUTO_ID++;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public void inputData(Scanner scanner, Categories[] arrCategories, int currentCate) {
        // Nhập tên danh mục, có độ dài tối đa 50 ký tự, không trùng lặp
        do {
            System.out.println("Nhập tên danh mục:");
            String categoryName = scanner.nextLine();
            if (categoryName.length() <= 0 || categoryName.length() >= 50) {
                System.err.println("Phải nhập tên có độ dài tối đa 50 ký tự!");
                continue;
            }
            // Kiểm tra trùng lặp
            boolean isDuplicate = false;
            for (int i = 0; i < currentCate; i++) {
                if (arrCategories[i].getCatalogName().equals(categoryName)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (isDuplicate) {
                System.out.println("Tên danh mục đã tồn tại. Vui lòng nhập tên khác");
            } else {
                arrCategories[currentCate].setCatalogName(categoryName);
                break;
            }

        } while (true);

        // Nhập mô tả danh mục
        System.out.println("Nhập mô tả danh mục:");
        arrCategories[currentCate].setDescriptions(scanner.nextLine());

        // Nhập status chỉ nhận khi nhập 1 trong 2 giá trị true hoặc false (true – hoạt động, false – không hoạt động)
        System.out.println("Nhập vào (true/false):");
        arrCategories[currentCate].setCatalogStatus(Boolean.parseBoolean(scanner.nextLine())); // Cần kiểm tra có đúng true false không
    }

    public String displayData() {
        return "Categories{" +
                "catalogId=" + catalogId +
                ", catalogName='" + catalogName + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", catalogStatus=" + catalogStatus +
                '}';
    }
}
