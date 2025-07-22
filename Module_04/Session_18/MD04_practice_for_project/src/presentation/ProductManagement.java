package presentation;

import business.ProductBusiness;
import business.imp.ProductBusinessImp;
import entity.Product;
import business.PaginationBusiness;
import validation.Validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ProductManagement {
    private final int ID_MAX_LENGTH = 5;
    private final int NAME_MAX_LENGTH = 150;
    private final int MANUFAC_MAX_LENGTH = 200;

    private final ProductBusiness productBusiness;

    public ProductManagement() {
        productBusiness = new ProductBusinessImp();
    }

    public void productMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n**************** PRODUCT MANAGEMENT ***************");
            System.out.println("1. Danh sách sản phẩm");
            System.out.println("2. Thêm mới sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Tìm kiếm sản phẩm theo tên");
            System.out.println("5. Cập nhật trạng thái sản phẩm");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 6)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        PaginationPresentation.getListPagination(scanner, productBusiness, "products", new Product());
                        break;
                    case 2:
                        addProduct(scanner);
                        break;
                    case 3:
                        updateProduct(scanner);
                        break;
                    case 4:
                        getProductByName(scanner);
                        break;
                    case 5:
                        updateProductStatus(scanner);
                        break;
                    default:
                        exit = true;
                }
            } else {
                System.err.println("Nhập vào số nguyên trong phạm vi 1-6");
            }
        }
    }

    public void addProduct(Scanner scanner) {
        Product product = new Product();
        product.setProductId(inputProductIdForCreate(scanner));
        product.setProductName(inputProductName(scanner));
        product.setManufacturer(inputManufacturer(scanner));
        product.setBatch(inputBatch(scanner));
        boolean success = productBusiness.createProduct(product);
        if (success) {
            System.out.println("Thêm mới sản phẩm thành công.");
        } else {
            System.err.println("Có lỗi trong quá trình thêm mới sản phẩm.");
        }
    }

    public void updateProduct(Scanner scanner) {
        String productId = inputProductIdForUpdate(scanner);
        Product updateProduct = productBusiness.getProductById(productId);
        PaginationPresentation.printTableHeader("products");
        System.out.printf("| %-5s %s\n", 1, updateProduct);
        PaginationPresentation.printDivider();

        boolean exit = false;
        while (!exit) {
            System.out.println("1. Cập nhật tên sản phẩm");
            System.out.println("2. Cập nhật nhà sản xuất");
            System.out.println("3. Cập nhật ngày tạo");
            System.out.println("4. Cập nhật lô chứa sản phẩm");
            System.out.println("5. Thoát cập nhật");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 5)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        updateProduct.setProductName(inputProductName(scanner));
                        break;
                    case 2:
                        updateProduct.setManufacturer(inputManufacturer(scanner));
                        break;
                    case 3:
                        updateProduct.setCreated(inputCreated(scanner));
                        break;
                    case 4:
                        updateProduct.setBatch(inputBatch(scanner));
                        break;
                    default:
                        exit = true;
                }
            }
        }
        if (productBusiness.updateProduct(updateProduct)) {
            System.out.println("Cập nhật thành công");
        } else {
            System.err.println("Cập nhật thất bại!");
        }
    }

    public void getProductByName(Scanner scanner) {
        while (true) {
            Product product = new Product();
            System.out.println("Nhập vào tên tương đối để tìm sản phẩm: ");
            String productName = scanner.nextLine();
            if (Validation.isNotEmpty(productName)) {
                product.setProductName(productName);
                PaginationPresentation.getListPagination(scanner, productBusiness, "products", product);
                break;
            }
            System.err.println("Không được để trống!");
        }
    }

    public void updateProductStatus(Scanner scanner) {
        String productId = inputProductIdForUpdate(scanner);
        Product updateProduct = productBusiness.getProductById(productId);
        System.out.printf("Trạng thái hiện tại của sản phẩm có ID %s: %s\n",
                updateProduct.getProductId(),
                updateProduct.isProductStatus() ? "Hoạt động" : "Không hoạt động");

        while (true) {
            System.out.print("Cập nhật trạng thái mới (1. Hoạt động | 2. Không hoạt động): ");
            String statusInput = scanner.nextLine();
            if (Validation.isIntegerInRange(statusInput, 1, 2)) {
                boolean status = Integer.parseInt(statusInput) == 1;
                if (productBusiness.updateProductStatus(productId, status)) {
                    System.out.println("Cập nhật trạng thái thành công");
                    PaginationPresentation.printTableHeader("products");
                    System.out.printf("| %-5s %s\n", 1, updateProduct);
                    PaginationPresentation.printDivider();
                    break;
                } else {
                    System.err.println("Cập nhật trạng thái thất bại!");
                }
            } else {
                System.err.println("Trạng thái nhập vào không đúng!");
            }
        }
    }

    public String inputProductIdForCreate(Scanner scanner) {
        while (true) {
            System.out.println("Nhập mã sản phẩm: ");
            String productId = scanner.nextLine();
            if (Validation.isValidLength(productId, ID_MAX_LENGTH)) {
                if (productBusiness.getProductById(productId) == null) {
                    return productId;
                } else {
                    System.err.println("Mã sản phẩm này đã tồn tại. Vui lòng nhập mã khác!");
                }
            } else {
                System.err.printf("Thông tin nhập không được để trống hoặc có độ dài quá %d ký tự. Vui lòng nhập lại!\n", ID_MAX_LENGTH);
            }
        }
    }

    public String inputProductIdForUpdate(Scanner scanner) {
        while (true) {
            System.out.println("Nhập mã sản phẩm: ");
            String productId = scanner.nextLine();
            if (Validation.isValidLength(productId, ID_MAX_LENGTH)) {
                if (productBusiness.getProductById(productId) != null) {
                    return productId;
                } else {
                    System.err.println("Mã sản phẩm này KHÔNG tồn tại. Vui lòng nhập mã khác!");
                }
            } else {
                System.err.printf("Thông tin nhập không được để trống hoặc có độ dài quá %d ký tự. Vui lòng nhập lại!\n", ID_MAX_LENGTH);
            }
        }
    }

    public String inputProductName(Scanner scanner) {
        while (true) {
            System.out.println("Nhập tên sản phẩm: ");
            String productName = scanner.nextLine();
            if (Validation.isValidLength(productName, NAME_MAX_LENGTH)) {
                if (!productBusiness.checkExistProductName(productName)) {
                    return productName;
                } else {
                    System.err.println("Tên sản phẩm đã tồn tại. Vui lòng nhập nhập lại!");
                }
            } else {
                System.err.printf("Thông tin nhập không được để trống hoặc có độ dài quá %d ký tự. Vui lòng nhập lại!\n", NAME_MAX_LENGTH);
            }
        }
    }

    public String inputManufacturer(Scanner scanner) {
        while (true) {
            System.out.println("Nhập tên nhà sản xuất: ");
            String manufacturer = scanner.nextLine();
            if (Validation.isValidLength(manufacturer, MANUFAC_MAX_LENGTH)) {
                return manufacturer;
            } else {
                System.err.printf("Thông tin nhập không được để trống hoặc có độ dài quá %d ký tự. Vui lòng nhập lại!\n", MANUFAC_MAX_LENGTH);
            }
        }
    }

    public LocalDate inputCreated(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.println("Nhập vào ngày tạo (yyyy-MM-dd): ");
            String date = scanner.nextLine();
            if (Validation.isValidDate(date, "yyyy-MM-dd")) {
                return LocalDate.parse(date, formatter);
            } else {
                System.err.println("Thông tin nhập vào không đúng định dạng. Vui lòng nhập lại!");
            }
        }
    }

    public short inputBatch(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào lô chứa sản phẩm: ");
            String batch = scanner.nextLine();
            if (Validation.isValidType(batch, "Short")) {
                return Short.parseShort(batch);
            } else {
                System.err.println("Thông tin nhập vào không đúng định dạng. Vui lòng nhập lại!");
            }
        }
    }

}
