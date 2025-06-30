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

    public Product() {
    }

    public Product(String productId, String productName, float price, String description, int catalogId, int productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public void inputData(Scanner scanner, Product[] arrProduct, int currentProd, Categories[] arrCategories, int currentCate) {
        // Mã sản phẩm đồ uống, gồm 4 ký tự bắt đầu là một trong 3 ký tự (C: các đồ uống là café, S: các đồ uống là sinh tố, A: các đồ ăn nhanh),
        // không được trùng lặp
        do{
            System.out.println("Nhập vào mã sản phẩm:");
            String productNameInput = scanner.nextLine();
            String regexName = "[ASC][0-9]{3}";
            if(Pattern.matches(regexName, productNameInput)){
                // Kiểm tra trùng lặp
                boolean isDuplicate = false;
                for (int i = 0; i < currentProd; i++) {
                    if(arrProduct[i].getProductName().equals(productNameInput)){
                        isDuplicate = true;
                        break;
                    }
                }
                if (isDuplicate) {
                    System.out.println("Tên danh mục đã tồn tại. Vui lòng nhập tên khác");
                } else {
                    arrProduct[currentProd].setProductId(productNameInput);
                    break;
                }
            }
        } while (true);

        // tên sản phẩm đồ uống, có từ 10-50 ký tự, không được trùng lặp
        System.out.println("Nhập vào tên sản phẩm");
        arrProduct[currentProd].setProductName(scanner.nextLine());

        // giá sản phẩm có giá trị lớn hơn 0
        System.out.println("Nhập vào giá: ");
        arrProduct[currentProd].setPrice(Float.parseFloat(scanner.nextLine()));

        // mô tả sản phẩm
        System.out.println("Nhập vào mô tả sản phẩm:");
        arrProduct[currentProd].setDescription(scanner.nextLine());

        // Nhập ID Category
        System.out.println("Danh sách danh mục hiện có:");
        for (int i = 0; i < currentCate; i++) {
            arrCategories[i].displayData();
        }
        System.out.println("Nếu chưa có danh mục trong danh sách, nhập 'y'\n. Nếu đã có rồi, nhập Id trong danh sách");
        char choice = scanner.nextLine().trim().toLowerCase().charAt(0);
        if (choice == 'y') {
            // Tạo mới category
            Categories newCategory = new Categories();
            newCategory.inputData(scanner, arrCategories, currentCate);
            arrCategories[currentCate] = newCategory;
            currentCate++;
            // Thêm catalogId cho sản phẩm
            arrProduct[currentProd].setCatalogId(newCategory.getCatalogId());

        } else {
            System.out.println("Chọn Id danh mục cho sản phẩm:");
            int idCategory = Integer.parseInt(scanner.nextLine());
            // Nếu ID vượt quá phạm vi thì đặt mặc định
            if (idCategory < 0 || idCategory > currentCate) {
                System.err.println("Id Category vượt quá phạm vi Id đã có. Đặt mặc định ID = 0");
                idCategory = 0;
            } else {
                arrProduct[currentProd].setCatalogId(idCategory);
            }
        }

        // Trạng thái sản phẩm, chỉ nhận 1 trong các trạng thái sau (0: Đang bán – 1: Hết hàng – 2: Không bán)
        System.out.println("Nhập vào trạng thái sản phẩm (0: Đang bán – 1: Hết hàng – 2: Không bán):");
        arrProduct[currentProd].setProductStatus(Integer.parseInt(scanner.nextLine()));
    }

    public String displayData() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", catalogId=" + catalogId +
                ", productStatus=" + productStatus +
                '}';
    }
}
