package pagination;

import business.ProductBusiness;
import business.imp.ProductBusinessImp;
import validation.Validation;

import java.util.Scanner;

public class Pagination {
    final static int PRODUCT_PER_PAGE = 10;
    private final ProductBusiness productBusiness;

    public Pagination() {
        productBusiness = new ProductBusinessImp();
    }

    public void getListPagination(Scanner scanner, int currentPage) {
        PaginationProduct paginationProduct = productBusiness.getProductPagination(currentPage, PRODUCT_PER_PAGE);
        paginationProduct.getProductList().forEach(System.out::println);

        int totalPages = paginationProduct.getTotalPages();
        System.out.printf("| PREV | %5d / %5d | NEXT | EXIT |\n", currentPage, totalPages);
        System.out.println("-----------------------------------------");

        boolean exit = false;
        while (!exit) {
            System.out.println("1. Xem trang tiếp theo");
            System.out.println("2. Xem trang trước");
            System.out.println("3. Chọn trang muốn xem");
            System.out.println("4. Thoát");
            System.out.print("Lựa chọn: ");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 4)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        currentPage = currentPage == 1 ? currentPage : currentPage - 1;
                        getListPagination(scanner, currentPage);
                        break;
                    case 2:
                        currentPage = currentPage == totalPages ? currentPage : currentPage + 1;
                        getListPagination(scanner, currentPage);
                        break;
                    case 3:
                        System.out.println("Nhập vào số trang muốn xem");
                        String input = scanner.nextLine();
                        if (Validation.isIntegerInRange(input, 1, totalPages)) {
                            currentPage = Integer.parseInt(input);
                        }
                        getListPagination(scanner, currentPage);
                        break;
                    default:
                        exit = true;
                }
            }
        }
    }
}