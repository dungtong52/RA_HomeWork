package presentation;

import business.BillReceiptBusiness;
import business.UserBusiness;
import business.imp.BillBusinessImp;
import business.imp.ReceiptBusinessImp;
import business.imp.UserBusinessImp;
import entity.Bill;
import validation.Validation;

import java.util.Scanner;

public class WarehouseManagement {
    private final int CODE_MAX_LENGTH = 10;

    private final ProductManagement productManagement;
    private final EmployeeManagement employeeManagement;
    private final AccountManagement accountManagement;
    private final ReceiptManagement receiptManagement;
    private final BillManagement billManagement;
    private final ReportManagement reportManagement;
    private final UserBusiness userBusiness;


    public WarehouseManagement() {
        productManagement = new ProductManagement();
        employeeManagement = new EmployeeManagement();
        accountManagement = new AccountManagement();
        receiptManagement = new ReceiptManagement();
        billManagement = new BillManagement();
        reportManagement = new ReportManagement();
        userBusiness = new UserBusinessImp();
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
            System.out.println("Lựa chọn của bạn: ");
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
            System.out.println("Lựa chọn của bạn: ");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 9)) {
                String billCode;
                switch (Integer.parseInt(choice)) {
                    case 1:
                        getReceiptByStatus(scanner);
                        break;
                    case 2:
                        receiptManagement.createReceipt(scanner);
                        break;
                    case 3:
                        billCode = inputBillCode(scanner, true);
                        updateBillOfUser(scanner, billCode, true);
                        break;
                    case 4:
                        billCode = inputBillCode(scanner, true);
                        findBillByCodeOfUser(scanner, billCode, true);
                        break;
                    case 5:
                        getBillByStatus(scanner);
                        break;
                    case 6:
                        billManagement.createBills(scanner);
                        break;
                    case 7:
                        billCode = inputBillCode(scanner, false);
                        updateBillOfUser(scanner, billCode, false);
                        break;
                    case 8:
                        billCode = inputBillCode(scanner, false);
                        findBillByCodeOfUser(scanner, billCode, false);
                        break;
                    default:
                        System.exit(0);
                }
            } else {
                System.err.println("Nhập vào số nguyên trong phạm vi 1-9");
            }
        }
    }

    public void getReceiptByStatus(Scanner scanner) {
        String empId = AccountManagement.currentAccount.getEmpId();
        System.out.println("Nhập vào trạng thái phiếu nhập muốn tìm kiếm (0. Tạo | 1. Hủy | 2. Duyệt):");
        String status = scanner.nextLine();
        if (Validation.isIntegerInRange(status, 0, 2)) {
            Bill receipt = new Bill();
            receipt.setEmpIdCreated(empId);
            receipt.setBillStatus(Short.parseShort(status));
            receipt.setBillType(true);

            PaginationPresentation.getListPagination(scanner, userBusiness, "bills", receipt);
        } else {
            System.err.println("Trạng thái nhập vào không hợp lệ. Nhập số từ 0-2");
        }
    }

    public void getBillByStatus(Scanner scanner) {
        String empId = AccountManagement.currentAccount.getEmpId();
        System.out.println("Nhập vào trạng thái phiếu xuất muốn tìm kiếm (0. Tạo | 1. Hủy | 2. Duyệt):");
        String status = scanner.nextLine();
        if (Validation.isIntegerInRange(status, 0, 2)) {
            Bill receipt = new Bill();
            receipt.setEmpIdCreated(empId);
            receipt.setBillStatus(Short.parseShort(status));
            receipt.setBillType(false);

            PaginationPresentation.getListPagination(scanner, userBusiness, "bills", receipt);
        } else {
            System.err.println("Trạng thái nhập vào không hợp lệ. Nhập số từ 0-2");
        }
    }

    public void updateBillOfUser(Scanner scanner, String billCode, boolean billType) {
        Bill billUpdate = userBusiness.findBillByCodeForUser(billCode, billType, AccountManagement.currentAccount.getEmpId());
        PaginationPresentation.printTableHeader("bills");
        System.out.printf("| %-5s %s\n", 1, billUpdate);
        PaginationPresentation.printDivider();

        boolean exit = false;
        while (!exit) {
            System.out.println("1. Cập nhật ngày tạo phiếu");
            System.out.println("2. Cập nhật trạng thái phiếu xuất");
            System.out.println("3. Cập nhật chi tiết phiếu xuất");
            System.out.println("4. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 4)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        billUpdate.setCreated(billManagement.inputCreated(scanner));
                        break;
                    case 2:
                        short status = billManagement.inputStatus(scanner);
                        if (status != 2) {
                            billUpdate.setBillStatus(status);
                        } else {
                            System.err.println("Không thể cập nhật trạng thái sang 'Duyệt' ở chức năng này!");
                        }
                        break;
                    case 3:
                        billManagement.updateBillDetails(scanner, billUpdate.getBillId());
                        break;
                    default:
                        exit = true;
                }
            } else {
                System.err.println("Chỉ được nhập vào số nguyên từ 1-7.");
            }
        }
        boolean success = userBusiness.updateBillForUser(billUpdate);
        if (success) {
            System.out.println("Cập nhật phiếu nhập thành công.");
        } else {
            System.out.println("Cập nhật phiếu nhập thất bại!");
        }
    }

    public void findBillByCodeOfUser(Scanner scanner, String billCode, boolean billType) {
        Bill billSearch = userBusiness.findBillByCodeForUser(billCode, billType, AccountManagement.currentAccount.getEmpId());
        PaginationPresentation.printTableHeader("bills");
        System.out.printf("| %-5s %s\n", 1, billSearch);
        PaginationPresentation.printDivider();

        while (true) {
            System.out.print("Bạn có muốn cập nhật phiếu nhập này không (1. Có || 2. Không): ");
            String number = scanner.nextLine();
            if (Validation.isIntegerInRange(number, 1, 2)) {
                if (Integer.parseInt(number) == 1) {
                    updateBillOfUser(scanner, billCode, billType);
                } else {
                    break;
                }
            } else {
                System.err.println("Chỉ được nhập vào số nguyên 1 hoặc 2");
            }
        }
    }

    public String inputBillCode(Scanner scanner, boolean billType) {
        while (true) {
            System.out.println("Nhập vào mã code: ");
            String billCode = scanner.nextLine();
            if (Validation.isValidLength(billCode, CODE_MAX_LENGTH)) {
                if (userBusiness.checkExistBillCode(billCode, billType, AccountManagement.currentAccount.getEmpId())) {
                    return billCode;
                } else {
                    System.err.println("Mã Code này KHÔNG tồn tại. Hãy nhập vào mã khác!");
                }
            } else {
                System.err.println("Mã code nhập vào không hợp lệ. Vui lòng nhập lại!");
            }
        }
    }
}
