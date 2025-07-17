package presentation;

import entity.Account;
import validation.Validation;

import java.util.Scanner;

public class WarehouseManagement {
    private final ProductManagement productManagement;
    private final EmployeeManagement employeeManagement;
    private final AccountManagement accountManagement;
    private final ReceiptManagement receiptManagement;
    private final BillManagement billManagement;
    private final ReportManagement reportManagement;

    public WarehouseManagement() {
        productManagement = new ProductManagement();
        employeeManagement = new EmployeeManagement();
        accountManagement = new AccountManagement();
        receiptManagement = new ReceiptManagement();
        billManagement = new BillManagement();
        reportManagement = new ReportManagement();
    }

    public void warehouseMenuForAdmin(Scanner scanner) {
        System.out.println("Chào ADMIN! Bắt đầu chương trình.");
        while (true) {
            System.out.println("\n**************** WAREHOUSE MANAGEMENT ***************");
            System.out.println("1. Quản lý sản phẩm");
            System.out.println("2. Quản lý nhân viên");
            System.out.println("3. Quản lý tài khoản");
            System.out.println("4. Quản lý phiếu nhập");
            System.out.println("5. Quản lý phiếu xuất");
            System.out.println("6. Quản lý báo cáo");
            System.out.println("7. Thoát");
            System.out.print("Lựa chọn: ");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 7)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        productManagement.productMenu(scanner);
                        break;
                    case 2:
                        employeeManagement.employeeMenu(scanner);
                        break;
                    case 3:
                        accountManagement.accountMenu(scanner);
                        break;
                    case 4:
                        receiptManagement.receiptMenu(scanner);
                        break;
                    case 5:
                        billManagement.billMenu(scanner);
                        break;
                    case 6:
                        reportManagement.reportMenu(scanner);
                        break;
                    default:
                        System.exit(0);
                }
            } else {
                System.err.println("Nhập vào số nguyên trong phạm vi 1-7");
            }
        }
    }

    public void warehouseMenuForUser(Scanner scanner) {
        System.out.println("Chào USER! Bắt đầu chương trình.");

        while (true) {
            System.out.println("\n**************** WAREHOUSE MANAGEMENT ***************");
            System.out.println("1. Danh sách phiếu nhập theo trạng thái");
            System.out.println("2. Tạo phiếu nhập");
            System.out.println("3. Cập nhật phiếu nhập");
            System.out.println("4. Tìm kiếm phiếu nhập");
            System.out.println("5. Danh sách phiếu xuất theo trạng thái");
            System.out.println("6. Tạo phiếu xuất");
            System.out.println("7. Cập nhật phiếu xuất");
            System.out.println("8. Tìm kiếm phiếu xuất");
            System.out.println("9. Thoát");
            System.out.print("Lựa chọn: ");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 9)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
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
                        System.exit(0);
                }
            } else {
                System.err.println("Nhập vào số nguyên trong phạm vi 1-9");
            }
        }
    }
}
