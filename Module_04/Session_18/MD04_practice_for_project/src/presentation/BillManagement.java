package presentation;

import business.*;
import business.imp.*;
import entity.Bill;
import entity.BillDetail;
import entity.Employee;
import entity.Product;
import validation.Validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BillManagement {
    private final int CODE_MAX_LENGTH = 10;
    private final int STR_MAX_LENGTH = 5;

    private final BillReceiptBusiness billBusiness;
    private final PaginationBusiness<Bill> billPaginationBusiness;
    private final EmployeeBusiness employeeBusiness;
    private final ProductBusiness productBusiness;
    private final BillReceiptDetailsBusiness billReceiptDetailsBusiness;

    public BillManagement() {
        billBusiness = new BillBusinessImp();
        billPaginationBusiness = new ReceiptBusinessImp();
        employeeBusiness = new EmployeeBusinessImp();
        productBusiness = new ProductBusinessImp();
        billReceiptDetailsBusiness = new BillReceiptDetailsBusinessImp();
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
            System.out.println("Lựa chọn của bạn: ");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 7)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        PaginationPresentation.getListPagination(scanner, billPaginationBusiness, "bills", "");
                        break;
                    case 2:
                        createBills(scanner);
                        break;
                    case 3:
                        updateBill(scanner);
                        break;
                    case 4:
                        getBillDetails(scanner);
                        break;
                    case 5:
                        acceptBill(scanner);
                        break;
                    case 6:
                        findBillByCode(scanner);
                        break;
                    default:
                        exit = true;
                }
            } else {
                System.err.println("Nhập vào số nguyên trong phạm vi 1-7");
            }
        }
    }

    public void createBills(Scanner scanner) {
        Bill bill = new Bill();

        bill.setBillCode(inputNewBillCode(scanner));
        bill.setBillType(true);
        bill.setEmpIdCreated(AccountManagement.currentAccount.getEmpId());
        long billId = billBusiness.createBill(bill);
        if (billId > 0) {
            createBillDetails(scanner, billId);
        } else {
            System.err.println("Tạo phiếu xuất thất bại!");
        }
    }

    public void createBillDetails(Scanner scanner, long billId) {
        List<BillDetail> billDetailList = new ArrayList<>();
        while (true) {
            System.out.println("Nhập vào số lượng chi tiết phiếu xuất: ");
            String numberInput = scanner.nextLine();
            if (Validation.isValidType(numberInput, "Integer")) {
                int number = Integer.parseInt(numberInput);
                for (int i = 0; i < number; i++) {
                    BillDetail billDetail = new BillDetail();
                    billDetail.setBillId(billId);
                    billDetail.setProductId(inputProductId(scanner));
                    billDetail.setQuantity(inputQuantity(scanner));
                    billDetail.setPrice(inputPrice(scanner));
                    billDetailList.add(billDetail);
                }
                boolean success = billReceiptDetailsBusiness.createBatchDetails(billDetailList);
                if (success) {
                    System.out.println("Tạo chi tiết phiếu xuất thành công");
                    break;
                } else {
                    System.err.println("Tạo chi tiết phiếu xuất thất bại!");
                }
            } else {
                System.err.println("Số lượng nhập vào không đúng định dạng. Vui lòng nhập lại!");
            }
        }
    }

    public void updateBill(Scanner scanner) {
        String billCode = inputExistBillCode(scanner);
        Bill bill = billBusiness.findBillByCode(billCode);
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Cập nhật mã nhân viên xuất");
            System.out.println("2. Cập nhật ngày tạo phiếu");
            System.out.println("3. Cập nhật mã nhân viên duyệt");
            System.out.println("4. Cập nhật ngày duyệt");
            System.out.println("5. Cập nhật trạng thái phiếu xuất");
            System.out.println("6. Cập nhật chi tiết phiếu xuất");
            System.out.println("7. Thoát");
            System.out.println("Lựa chọn của bạn: ");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 7)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        bill.setEmpIdCreated(inputEmpIdCreated(scanner));
                        break;
                    case 2:
                        bill.setCreated(inputCreated(scanner));
                        break;
                    case 3:
                        bill.setEmpIdAuth(inputEmpIdAuth(scanner));
                        break;
                    case 4:
                        bill.setAuthDate(inputAuthDate(scanner));
                        break;
                    case 5:
                        short status = inputStatus(scanner);
                        if (status != 2) {
                            bill.setBillStatus(status);
                        } else {
                            System.err.println("Không thể cập nhật trạng thái sang 'Duyệt' ở chức năng này!");
                        }
                        break;
                    case 6:
                        updateReceiptDetails(scanner, bill.getBillId());
                        break;
                    default:
                        exit = true;
                }
            } else {
                System.err.println("Chỉ được nhập vào số nguyên từ 1-7.");
            }
        }
        boolean success = billBusiness.updateBill(bill);
        if (success) {
            System.out.println("Cập nhật phiếu xuất thành công.");
        } else {
            System.out.println("Cập nhật phiếu xuất thất bại!");
        }
    }

    public void updateReceiptDetails(Scanner scanner, long billId) {
        System.out.println("Danh sách chi tiết phiếu xuất: ");
        PaginationPresentation.getListPagination(scanner, billPaginationBusiness, "bill details", billId + "");
        System.out.println("Nhập vào mã phiếu chi tiết muốn cập nhật: ");
        String billDetailId = scanner.nextLine();
        if (Validation.isValidType(billDetailId, "Long")) {
            BillDetail billDetail = billReceiptDetailsBusiness.findBillDetailById(Long.parseLong(billDetailId));
            boolean exit = false;
            while (!exit) {
                System.out.println("1. Cập nhật mã sản phẩm");
                System.out.println("2. Cập nhật số lượng");
                System.out.println("3. Cập nhật giá");
                System.out.println("4. Thoát");
                System.out.println("Lựa chọn của bạn: ");
                String choice = scanner.nextLine();
                if (Validation.isIntegerInRange(choice, 1, 4)) {
                    switch (Integer.parseInt(choice)) {
                        case 1:
                            billDetail.setProductId(inputProductId(scanner));
                            break;
                        case 2:
                            billDetail.setQuantity(inputQuantity(scanner));
                            break;
                        case 3:
                            billDetail.setPrice(inputPrice(scanner));
                            break;
                        default:
                            exit = true;
                    }
                    boolean success = billReceiptDetailsBusiness.updateReceiptDetails(billDetail);
                    if (success) {
                        System.out.println("Cập nhật chi tiết phiếu xuất thành công.");
                    } else {
                        System.out.println("Cập nhật chi tiết phiếu xuất thất bại!");
                    }
                } else {
                    System.err.println("Chỉ được nhập vào số nguyên từ 1-4.");
                }
            }
        } else {
            System.err.println("Mã phiếu chi tiết không hợp lệ. Vui lòng nhập lại!");
        }
    }

    public void getBillDetails(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào mã phiếu xuất: ");
            String billId = scanner.nextLine();
            if (Validation.isValidType(billId, "Long")) {
                System.out.println("Danh sách chi tiết phiếu xuất có mã phiếu " + billId);
                PaginationPresentation.getListPagination(scanner, billPaginationBusiness, "bill details", billId);
                break;
            } else {
                System.err.println("Mã phiếu nhập không hợp lệ. Vui lòng nhập lại!");
            }
        }
    }

    public void acceptBill(Scanner scanner) {
        String billCode = inputExistBillCode(scanner);
        Bill bill = billBusiness.findBillByCode(billCode);

        bill.setEmpIdAuth(AccountManagement.currentAccount.getEmpId());
        bill.setAuthDate(LocalDate.now());

        long billId = bill.getBillId();
        boolean accepted = billReceiptDetailsBusiness.acceptBill(billId);
        if (accepted) {
            System.out.println("Duyệt phiếu xuất và cập nhật số lượng sản phẩm thành công");
            boolean success = billBusiness.updateBill(bill);
            if (success) {
                System.out.println("Cập nhật mã nhân viên duyệt và ngày duyệt thành công.");
            } else {
                System.out.println("Cập nhật mã nhân viên duyệt và ngày duyệt thất bại!");
            }
        } else {
            System.err.printf("Duyệt phiếu có mã code %s thất bại!\n", billCode);
        }
    }

    public void findBillByCode(Scanner scanner) {
        String billCode = inputExistBillCode(scanner);
        Bill bill = billBusiness.findBillByCode(billCode);
        System.out.println(bill);

        long billId = bill.getBillId();
        System.out.println("Danh sách chi tiết phiếu xuất");
        PaginationPresentation.getListPagination(scanner, billPaginationBusiness, "bill details", billId + "");
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Cập nhật phiếu xuất trên");
            System.out.println("2. Duyệt phiếu xuất trên");
            System.out.println("3. Thoát");
            System.out.println("Lựa chọn:");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 3)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        updateBill(scanner);
                        break;
                    case 2:
                        acceptBill(scanner);
                        break;
                    default:
                        exit = true;
                }
            } else {
                System.err.println("Nhập vào số nguyên trong phạm vi 1-3");
            }
        }
    }

    public String inputExistBillCode(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào mã code: ");
            String billCode = scanner.nextLine();
            if (Validation.isValidLength(billCode, CODE_MAX_LENGTH)) {
                if (billBusiness.checkExistBillCode(billCode)) {
                    return billCode;
                } else {
                    System.err.println("Mã Code này KHÔNG tồn tại. Hãy nhập vào mã khác!");
                }
            } else {
                System.err.println("Mã code nhập vào không hợp lệ. Vui lòng nhập lại!");
            }
        }
    }

    public String inputNewBillCode(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào mã code phiếu xuất: ");
            String billCode = scanner.nextLine();
            if (Validation.isValidLength(billCode, CODE_MAX_LENGTH)) {
                if (!billBusiness.checkExistBillCode(billCode)) {
                    return billCode;
                } else {
                    System.err.println("Mã Code này đã tồn tại. Hãy nhập vào mã khác!");
                }
            } else {
                System.err.println("Mã code nhập vào không hợp lệ. Vui lòng nhập lại!");
            }
        }
    }

    public String inputEmpIdCreated(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào mã nhân viên xuất: ");
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

    public LocalDate inputCreated(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.println("Nhập vào ngày tạo phiếu (yyyy-MM-dd): ");
            String date = scanner.nextLine();
            if (Validation.isValidDate(date, "yyyy-MM-dd")) {
                return LocalDate.parse(date, formatter);
            } else {
                System.err.println("Ngày nhập vào không hợp lệ. Vui lòng nhập lại!");
            }
        }
    }

    public String inputEmpIdAuth(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào mã nhân viên duyệt: ");
            String empIdAuth = scanner.nextLine();
            if (Validation.isValidLength(empIdAuth, STR_MAX_LENGTH)) {
                Employee employee = employeeBusiness.getEmployeeById(empIdAuth);
                if (employee != null) {
                    return empIdAuth;
                } else {
                    System.err.println("Không tồn tại nhân viên này. Vui lòng nhập lại!");
                }
            } else {
                System.err.printf("Mã nhân viên nhập vào không được rỗng hoặc vượt quá %d ký tự\n", STR_MAX_LENGTH);
            }
        }
    }

    public LocalDate inputAuthDate(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.println("Nhập vào ngày duyệt phiếu (yyyy-MM-dd): ");
            String date = scanner.nextLine();
            if (Validation.isValidDate(date, "yyyy-MM-dd")) {
                return LocalDate.parse(date, formatter);
            } else {
                System.err.println("Ngày nhập vào không hợp lệ. Vui lòng nhập lại!");
            }
        }
    }

    public short inputStatus(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào trạng thái phiếu nhập (0. Tạo || 1. Hủy || 2. Duyệt): ");
            String status = scanner.nextLine();
            if (Validation.isIntegerInRange(status, 0, 2)) {
                return Short.parseShort(status);
            } else {
                System.err.println("Nhập vào số 0 (Tạo) hoặc 1 (Hủy) hoặc 2 (Duyệt)");
            }
        }
    }

    public String inputProductId(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào mã sản phẩm: ");
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
            System.out.println("Nhập vào số lượng: ");
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
            System.out.println("Nhập vào giá sản phẩm: ");
            String price = scanner.nextLine();
            if (Validation.isValidType(price, "Float")) {
                return Float.parseFloat(price);
            } else {
                System.err.println("Phải nhập vào số dương. Vui lòng nhập lại!");
            }
        }
    }
}
