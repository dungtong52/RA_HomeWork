package presentation;

import business.*;
import business.imp.EmployeeBusinessImp;
import business.imp.ProductBusinessImp;
import business.imp.ReceiptBusinessImp;
import business.imp.BillReceiptDetailsBusinessImp;
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
        billReceiptDetailsBusiness = new BillReceiptDetailsBusinessImp();
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
            System.out.println("Lựa chọn của bạn: ");
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
                        updateReceipt(scanner);
                        break;
                    case 4:
                        getReceiptDetails(scanner);
                        break;
                    case 5:
                        acceptReceipt(scanner);
                        break;
                    case 6:
                        findReceiptByCode(scanner);
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
        bill.setBillCode(inputNewBillCode(scanner));
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
        List<BillDetail> billDetailList = new ArrayList<>();
        while (true) {
            System.out.println("Nhập vào số lượng chi tiết phiếu nhập: ");
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
                    System.out.println("Tạo chi tiết phiếu nhập thành công");
                    break;
                } else {
                    System.err.println("Tạo chi tiết phiếu nhập thất bại!");
                }
            } else {
                System.err.println("Số lượng nhập vào không đúng định dạng. Vui lòng nhập lại!");
            }
        }
    }

    public void updateReceipt(Scanner scanner) {
        String billCode = inputExistBillCode(scanner);
        Bill receipt = receiptBusiness.findBillByCode(billCode);
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Cập nhật mã nhân viên nhập");
            System.out.println("2. Cập nhật ngày tạo phiếu");
            System.out.println("3. Cập nhật mã nhân viên duyệt");
            System.out.println("4. Cập nhật ngày duyệt");
            System.out.println("5. Cập nhật trạng thái phiếu nhập");
            System.out.println("6. Cập nhật chi tiết phiếu nhập");
            System.out.println("7. Thoát");
            System.out.println("Lựa chọn của bạn: ");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 7)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        receipt.setEmpIdCreated(inputEmpIdCreated(scanner));
                        break;
                    case 2:
                        receipt.setCreated(inputCreated(scanner));
                        break;
                    case 3:
                        receipt.setEmpIdAuth(inputEmpIdAuth(scanner));
                        break;
                    case 4:
                        receipt.setAuthDate(inputAuthDate(scanner));
                        break;
                    case 5:
                        short status = inputStatus(scanner);
                        if (status != 2) {
                            receipt.setBillStatus(status);
                        } else {
                            System.err.println("Không thể cập nhật trạng thái sang 'Duyệt' ở chức năng này!");
                        }
                        break;
                    case 6:
                        updateReceiptDetails(scanner, receipt.getBillId());
                        break;
                    default:
                        exit = true;
                }
            } else {
                System.err.println("Chỉ được nhập vào số nguyên từ 1-7.");
            }
        }
        boolean success = receiptBusiness.updateBill(receipt);
        if (success) {
            System.out.println("Cập nhật phiếu nhập thành công.");
        } else {
            System.out.println("Cập nhật phiếu nhập thất bại!");
        }
    }

    public void updateReceiptDetails(Scanner scanner, long billId) {
        System.out.println("Danh sách chi tiết phiếu nhập: ");
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
                System.out.print("Lựa chọn của bạn: ");
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
                        System.out.println("Cập nhật chi tiết phiếu nhập thành công.");
                    } else {
                        System.out.println("Cập nhật chi tiết phiếu nhập thất bại!");
                    }
                } else {
                    System.err.println("Chỉ được nhập vào số nguyên từ 1-4.");
                }
            }
        } else {
            System.err.println("Mã phiếu chi tiết không hợp lệ. Vui lòng nhập lại!");
        }
    }

    public void getReceiptDetails(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào mã phiếu nhập: ");
            String billId = scanner.nextLine();
            if (Validation.isValidType(billId, "Long")) {
                System.out.println("Danh sách chi tiết phiếu nhập có mã phiếu " + billId);
                PaginationPresentation.getListPagination(scanner, billPaginationBusiness, "bill details", billId);
                break;
            } else {
                System.err.println("Mã phiếu nhập không hợp lệ. Vui lòng nhập lại!");
            }
        }
    }

    public void acceptReceipt(Scanner scanner) {
        String billCode = inputExistBillCode(scanner);
        Bill receipt = receiptBusiness.findBillByCode(billCode);

        receipt.setEmpIdAuth(inputEmpIdAuth(scanner));
        receipt.setAuthDate(inputAuthDate(scanner));

        long billId = receipt.getBillId();
        boolean accepted = billReceiptDetailsBusiness.acceptBill(billId);
        if (accepted) {
            System.out.println("Duyệt phiếu nhập và cập nhật số lượng sản phẩm thành công");

            boolean success = receiptBusiness.updateBill(receipt);
            if (success) {
                System.out.println("Cập nhật mã nhân viên duyệt và ngày duyệt thành công.");
            } else {
                System.out.println("Cập nhật mã nhân viên duyệt và ngày duyệt thất bại!");
            }
        } else {
            System.err.printf("Duyệt phiếu có mã code %s thất bại!\n", billCode);
        }
    }

    public void findReceiptByCode(Scanner scanner) {
        String billCode = inputExistBillCode(scanner);
        Bill receipt = receiptBusiness.findBillByCode(billCode);
        System.out.println(receipt);

        long billId = receipt.getBillId();
        System.out.println("Phiếu nhập có bill code: " + billCode);
        PaginationPresentation.getListPagination(scanner, billPaginationBusiness, "bill details", billId + "");
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Cập nhật phiếu nhập trên");
            System.out.println("2. Duyệt phiếu nhập trên");
            System.out.println("3. Thoát");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 3)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        updateReceipt(scanner);
                        break;
                    case 2:
                        acceptReceipt(scanner);
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
            System.out.println("Nhập vào mã code phiếu nhập: ");
            String billCode = scanner.nextLine();
            if (Validation.isValidLength(billCode, CODE_MAX_LENGTH)) {
                if (receiptBusiness.checkExistBillCode(billCode)) {
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
            System.out.println("Nhập vào mã code phiếu nhập: ");
            String billCode = scanner.nextLine();
            if (Validation.isValidLength(billCode, CODE_MAX_LENGTH)) {
                if (!receiptBusiness.checkExistBillCode(billCode)) {
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
            System.out.println("Nhập vào mã nhân viên nhập: ");
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
