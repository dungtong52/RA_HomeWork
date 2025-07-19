package presentation;

import business.BillReceiptBusiness;
import business.PaginationBusiness;
import business.imp.BillBusinessImp;
import entity.Bill;
import validation.Validation;

import java.util.Scanner;

public class BillManagement {
    private final BillReceiptBusiness billReceiptBusiness;
    private final PaginationBusiness<Bill> billPaginationBusiness;

    public BillManagement() {
        billReceiptBusiness = new BillBusinessImp();
        billPaginationBusiness = new BillBusinessImp();
    }

    public void billMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n**************** BILL MANAGEMENT ***************");
            System.out.println("1. Danh sách phiếu xuất");
            System.out.println("2. Tạo phiếu xuất");
            System.out.println("3. Cập nhật thông tin phiếu xuất");
            System.out.println("4. Chi tiết phiếu xuất");
            System.out.println("5. Duyệt phiếu xuất");
            System.out.println("6. Tìm kiếm phiếu xuất");
            System.out.println("7. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 7)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        PaginationPresentation.getListPagination(scanner, billPaginationBusiness, "bills", null);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    default:
                        exit = true;
                }
            } else {
                System.err.println("Nhập vào số nguyên trong phạm vi 1-7");
            }
        }
    }
}
