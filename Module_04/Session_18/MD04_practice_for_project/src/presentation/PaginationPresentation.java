package presentation;

import business.PaginationBusiness;
import entity.PaginationResult;
import validation.Validation;

import java.util.List;
import java.util.Scanner;

public class PaginationPresentation {
    final static int PRODUCT_PER_PAGE = 10;

    public static <T> void getListPagination(Scanner scanner, PaginationBusiness<T> paginationBusiness, String tableName, String key) {
        boolean exit = false;
        int currentPage = 1;

        while (!exit) {
            PaginationResult<T> result = paginationBusiness.getPaginationData(key, PRODUCT_PER_PAGE, currentPage);
            List<T> dataList = result.getDataList();
            int totalPages = result.getTotalPages();

            PaginationPresentation.printTableHeader(tableName);
            dataList.forEach(System.out::println);
            System.out.println("-".repeat(116));
            System.out.printf(" ".repeat(35) + "| PREV | %5d / %-5d | NEXT | EXIT |\n", currentPage, totalPages);

            System.out.println("1. Xem trang trước (PREV)");
            System.out.println("2. Xem trang tiếp theo (NEXT)");
            System.out.println("3. Chọn trang muốn xem");
            System.out.println("4. Thoát (EXIT)");
            System.out.println("Lựa chọn: ");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 4)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        if (currentPage == 1) {
                            System.err.println("Đây là trang đầu tiên");
                        } else {
                            currentPage--;
                        }
                        break;
                    case 2:
                        if (currentPage == totalPages) {
                            System.err.println("Đây là trang cuối cùng");
                        } else {
                            currentPage++;
                        }
                        break;
                    case 3:
                        System.out.println("Nhập vào số trang muốn xem");
                        String input = scanner.nextLine();
                        if (Validation.isIntegerInRange(input, 1, totalPages)) {
                            if (Integer.parseInt(input) == currentPage) {
                                System.err.printf("Đây đang là trang %d rồi\n", currentPage);
                            } else {
                                currentPage = Integer.parseInt(input);
                            }
                        } else {
                            System.err.println("Thông tin nhập vào không hợp lệ");
                        }
                        break;
                    default:
                        exit = true;
                }
            } else {
                System.err.println("Vui lòng chọn từ 1-4");
            }
        }
    }

    public static void printTableHeader(String tableName) {
        switch (tableName.toLowerCase()) {
            case "products":
                System.out.println("-".repeat(116));
                System.out.printf("| %-10s | %-20s | %-20s | %-10s | %-10s | %-10s | %-15s |\n",
                        "Product ID", "Product Name", "Manufacturer", "Created", "Batch", "Quantity", "Status");
                System.out.println("-".repeat(116));
                break;
            case "employees":
                System.out.println("-".repeat(116));
                System.out.printf("| %-10s | %-20s | %-15s | %-15s | %-10s | %-10s | %-15s |\n",
                        "Emp ID", "Emp Name", "Birth", "Email", "Phone", "Address", "Status");
                System.out.println("-".repeat(116));
                break;
            case "accounts":
                System.out.println("-".repeat(116));
                System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-10s | %-15s | %-10s |\n",
                        "Acc ID", "User Name", "Password", "Permission", "Emp ID", "Emp Name", "Status");
                System.out.println("-".repeat(116));
                break;
            case "bills":
                System.out.println("-".repeat(116));
                System.out.printf("| %-10s | %-10s | %-10s | %-15s | %-10s | %-15s | %-10s | %-10s |\n",
                        "Bill ID", "Bill Code", "Bill Type", "Emp ID Created", "Created", "Emp ID Auth", "Auth Date", "Status");
                System.out.println("-".repeat(116));
                break;
            case "bill details":
                System.out.println("-".repeat(116));
                System.out.printf("| %-20s | %-20s | %-20s | %-15s | %-20s |\n",
                        "Bill Detail ID", "Bill ID", "Product ID", "Quantity", "Price");
                System.out.println("-".repeat(116));
                break;
            default:
                System.out.println(" ");
        }
    }
}