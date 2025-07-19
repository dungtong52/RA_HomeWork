package presentation;

import business.*;
import business.imp.EmployeeBusinessImp;
import business.imp.ProductBusinessImp;
import business.imp.ReceiptBusinessImp;
import business.imp.ReceiptDetailsBusinessImp;
import entity.Bill;
import entity.BillDetail;
import entity.Employee;
import entity.Product;
import validation.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReceiptManagement {
    private final int CODE_MAX_LENGTH = 10;
    private final int STR_MAX_LENGTH = 5;

    private final BillReceiptBusiness receiptBusiness;
    private final PaginationBusiness<Bill> billPaginationBusiness;
    private final EmployeeBusiness employeeBusiness;
    private final ProductBusiness productBusiness;
    private final BillReceiptDetailsBusiness billReceiptDetailsBusiness;

    public ReceiptManagement() {
        receiptBusiness = new ReceiptBusinessImp();
        billPaginationBusiness = new ReceiptBusinessImp();
        employeeBusiness = new EmployeeBusinessImp();
        productBusiness = new ProductBusinessImp();
        billReceiptDetailsBusiness = new ReceiptDetailsBusinessImp();
    }

    public void receiptMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n**************** RECEIPT MANAGEMENT ***************");
            System.out.println("1. Danh sách phiếu nhập");
            System.out.println("2. Tạo phiếu nhập");
            System.out.println("3. Cập nhật thông tin phiếu nhập");
            System.out.println("4. Chi tiết phiếu nhập");
            System.out.println("5. Duyệt phiếu nhập");
            System.out.println("6. Tìm kiếm phiếu nhập");
            System.out.println("7. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 7)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        PaginationPresentation.getListPagination(scanner, billPaginationBusiness, "bills", "");
                        break;
                    case 2:
                        createReceipt(scanner);
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

    public void createReceipt(Scanner scanner) {
        Bill bill = new Bill();

        bill.setBillCode(inputBillCode(scanner));
        bill.setBillType(true);
        bill.setEmpIdCreated(inputEmpIdCreated(scanner));
        long billId = receiptBusiness.createBill(bill);
        if (billId > 0) {
            createReceiptDetails(scanner, billId);
        } else {
            System.err.println("Tạo phiếu nhập thất bại!");
        }
    }

    public void createReceiptDetails(Scanner scanner, long billId) {
        BillDetail billDetail = new BillDetail();
        List<BillDetail> billDetailList = new ArrayList<>();
        while (true) {
            System.out.print("Nhập vào số lượng chi tiết phiếu nhập: ");
            String numberInput = scanner.nextLine();
            if (Validation.isValidType(numberInput, "Integer")) {
                int number = Integer.parseInt(numberInput);
                for (int i = 0; i < number; i++) {
                    billDetail.setBillId(billId);
                    billDetail.setProductId(inputProductId(scanner));
                    billDetail.setQuantity(inputQuantity(scanner));
                    billDetail.setPrice(inputPrice(scanner));
                    billDetailList.add(billDetail);
                }
                boolean success = billReceiptDetailsBusiness.createBatchDetails(billDetailList);
                if (success) {
                    System.out.println("Tạo chi tiết phiếu nhập thành công");
                } else {
                    System.err.println("Tạo chi tiết phiếu nhập thất bại!");
                }
            } else {
                System.err.println("Số lượng nhập vào không đúng định dạng. Vui lòng nhập lại!");
            }
        }
    }

    public String inputBillCode(Scanner scanner) {
        while (true) {
            System.out.print("Nhập vào mã code: ");
            String billCode = scanner.nextLine();
            if (Validation.isValidLength(billCode, CODE_MAX_LENGTH)) {
                return billCode;
            } else {
                System.err.println("Mã code nhập vào không hợp lệ. Vui lòng nhập lại!");
            }
        }
    }

    public String inputEmpIdCreated(Scanner scanner) {
        while (true) {
            System.out.print("Nhập vào mã nhân viên nhập: ");
            String empIdCreated = scanner.nextLine();
            if (Validation.isValidLength(empIdCreated, STR_MAX_LENGTH)) {
                Employee employee = employeeBusiness.getEmployeeById(empIdCreated);
                if (employee != null) {
                    return empIdCreated;
                } else {
                    System.err.println("Không tồn tại mã nhân viên này. Vui lòng nhập lại!");
                }
            } else {
                System.err.println("Thông tin nhập vào không hợp lệ. Vui lòng nhập lại!");
            }
        }
    }

    public String inputProductId(Scanner scanner) {
        while (true) {
            System.out.print("Nhập vào mã sản phẩm: ");
            String productId = scanner.nextLine();
            if (Validation.isValidLength(productId, STR_MAX_LENGTH)) {
                Product product = productBusiness.getProductById(productId);
                if (product != null) {
                    return productId;
                } else {
                    System.err.println("Không tồn tại mã sản phẩm này");
                }
            } else {
                System.err.printf("Mã sản phẩm không được để trống hoặc vượt quá %d ký tự. Vui lòng nập lại!\n", STR_MAX_LENGTH);
            }
        }
    }

    public int inputQuantity(Scanner scanner) {
        while (true) {
            System.out.print("Nhập vào số lượng: ");
            String quantity = scanner.nextLine();
            if (Validation.isValidType(quantity, "Integer")) {
                return Integer.parseInt(quantity);
            } else {
                System.err.println("Phải nhập vào số nguyên dương. Vui lòng nhập lại!");
            }
        }
    }

    public float inputPrice(Scanner scanner) {
        while (true) {
            System.out.print("Nhập vào giá sản phẩm: ");
            String price = scanner.nextLine();
            if (Validation.isValidType(price, "Float")) {
                return Float.parseFloat(price);
            } else {
                System.err.println("Phải nhập vào số dương. Vui lòng nhập lại!");
            }
        }
    }
}
