package presentation;

import business.ProductBusiness;
import business.imp.ProductBusinessImp;
import entity.Product;
import pagination.PaginationBusiness;
import validation.Validation;
import pagination.PaginationPresentation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ProductManagement {
    private final int ID_MAX_LENGTH = 5;
    private final int NAME_MAX_LENGTH = 150;
    private final int MANUFAC_MAX_LENGTH = 200;

    private final ProductBusiness productBusiness;
    private final PaginationBusiness<Product> paginationBusiness;

    public ProductManagement() {
        productBusiness = new ProductBusinessImp();
        paginationBusiness = new ProductBusinessImp();
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
                        PaginationPresentation.getListPagination(scanner, paginationBusiness, "products", "");
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
        product.setProductId(inputProductID(scanner));
        product.setProductName(inputProductName(scanner));
        product.setManufacturer(inputManufacturer(scanner));
        product.setCreated(inputCreated(scanner));
        product.setBatch(inputBatch(scanner));
        product.setQuantity(inputQuantity(scanner));
        boolean success = productBusiness.createProduct(product);
        if (success) {
            System.out.println("Thêm mới sản phẩm thành công.");
        } else {
            System.err.println("Có lỗi trong quá trình thêm mới sản phẩm.");
        }
    }

    public void updateProduct(Scanner scanner) {
        String productId = inputProductID(scanner);
        Product updateProduct = productBusiness.getProductById(productId);
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Cập nhật tên sản phẩm");
            System.out.println("2. Cập nhật nhà sản xuất");
            System.out.println("3. Cập nhật ngày tạo");
            System.out.println("4. Cập nhật lô chứa sản phẩm");
            System.out.println("5. Cập nhật số lượng sản phẩm");
            System.out.println("6. Thoát cập nhật");
            System.out.print("Lựa chọn: ");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 6)) {
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
                    case 5:
                        updateProduct.setQuantity(inputQuantity(scanner));
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
            System.out.print("Nhập vào tên tương đối để tìm sản phẩm: ");
            String productName = scanner.nextLine();
            if (Validation.isNotEmpty(productName)) {
                PaginationPresentation.getListPagination(scanner, paginationBusiness, "products", productName);
                break;
            }
            System.err.println("Không được để trống!");
        }
    }

    public void updateProductStatus(Scanner scanner) {
        String productId = inputProductID(scanner);
        Product updateProduct = productBusiness.getProductById(productId);
        System.out.printf("Trạng thái hiện tại của sản phẩm có ID %s: %s\n",
                updateProduct.getProductId(),
                updateProduct.isProductStatus() ? "Hoạt động" : "Không hoạt động");
        System.out.print("Cập nhật trạng thái mới (true | false): ");
        String statusInput = scanner.nextLine();
        if (Validation.isValidType(statusInput, "Boolean")) {
            boolean status = Boolean.parseBoolean(statusInput);
            if (productBusiness.updateProductStatus(productId, status)) {
                System.out.println("Cập nhật trạng thái thành công");
            } else {
                System.err.println("Cập nhật trạng thái thất bại!");
            }
        } else {
            System.err.println("Trạng thái nhập vào không đúng!");
        }

    }

    public String inputProductID(Scanner scanner) {
        while (true) {
            System.out.print("Nhập mã sản phẩm: ");
            String productID = scanner.nextLine();
            if (Validation.isValidLength(productID, ID_MAX_LENGTH)) {
                if (productBusiness.getProductById(productID) == null) {
                    return productID;
                } else {
                    System.err.println("Mã sản phẩm này đã tồn tại. Vui lòng nhập mã khác!");
                }
            } else {
                System.err.printf("Thông tin nhập không được để trống hoặc có độ dài quá %d ký tự. Vui lòng nhập lại!\n", ID_MAX_LENGTH);
            }
        }
    }

    public String inputProductName(Scanner scanner) {
        while (true) {
            System.out.print("Nhập tên sản phẩm: ");
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
            System.out.print("Nhập tên nhà sản xuất: ");
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
            System.out.print("Nhập vào ngày tạo (yyyy-MM-dd): ");
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
            System.out.print("Nhập vào lô chứa sản phẩm: ");
            String batch = scanner.nextLine();
            if (Validation.isValidType(batch, "Short")) {
                return Short.parseShort(batch);
            } else {
                System.err.println("Thông tin nhập vào không đúng định dạng. Vui lòng nhập lại!");
            }
        }
    }

    public int inputQuantity(Scanner scanner) {
        while (true) {
            System.out.print("Nhập vào số lượng sản phẩm: ");
            String quantity = scanner.nextLine();
            if (Validation.isValidType(quantity, "Integer")) {
                return Integer.parseInt(quantity);
            } else {
                System.err.println("Thông tin nhập vào không đúng định dạng. Vui lòng nhập lại!");
            }
        }
    }


}
