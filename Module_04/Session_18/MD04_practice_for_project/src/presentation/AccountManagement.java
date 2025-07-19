package presentation;

import business.AccountBusiness;
import business.EmployeeBusiness;
import business.imp.AccountBusinessImp;
import business.imp.EmployeeBusinessImp;
import business.PaginationBusiness;
import entity.Account;
import validation.Validation;

import java.util.Scanner;

public class AccountManagement {
    private final int STR_MAX_LENGTH = 30;
    private final int ID_MAX_LENGTH = 5;
    private final AccountBusiness accountBusiness;
    private final EmployeeBusiness employeeBusiness;
    private final PaginationBusiness<Account> accountPaginationBusiness;

    public AccountManagement() {
        accountBusiness = new AccountBusinessImp();
        employeeBusiness = new EmployeeBusinessImp();
        accountPaginationBusiness = new AccountBusinessImp();
    }

    public void accountMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n**************** ACCOUNT MANAGEMENT ***************");
            System.out.println("1. Danh sách tài khoản");
            System.out.println("2. Tạo tài khoản mới");
            System.out.println("3. Cập nhật trạng thái tài khoản");
            System.out.println("4. Tìm kiếm tài khoản");
            System.out.println("5. Thoát");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 6)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        PaginationPresentation.getListPagination(scanner, accountPaginationBusiness, "accounts", "");
                        break;
                    case 2:
                        createAccount(scanner);
                        break;
                    case 3:
                        updateAccountStatus(scanner);
                        break;
                    case 4:
                        getAccountByName(scanner);
                        break;
                    default:
                        exit = true;
                }
            } else {
                System.err.println("Nhập vào số nguyên trong phạm vi 1-5");
            }
        }
    }

    public void createAccount(Scanner scanner) {
        Account account = new Account();
        account.setUserName(inputUserName(scanner));
        account.setPassword(inputPassword(scanner));
        account.setEmpId(inputEmpId(scanner));
        boolean success = accountBusiness.createAccount(account);
        if (success) {
            System.out.println("Tạo mới thành công.");
        } else {
            System.err.println("Có lỗi trong quá trình tạo tài khoản!");
        }
    }

    public void updateAccountStatus(Scanner scanner) {
        while (true) {
            System.out.print("Nhập vào mã tài khoản muốn cập nhật: ");
            String accountIdInput = scanner.nextLine();
            if (Validation.isValidType(accountIdInput, "Integer")) {
                int accountId = Integer.parseInt(accountIdInput);
                Account account = accountBusiness.getAccountById(accountId);
                if (account != null) {
                    System.out.printf("Trạng thái hiện tại của tài khoản %d: %s\n", accountId, account.isAccStatus());
                    account.setAccStatus(inputAccStatus(scanner));
                    if (accountBusiness.updateAccountStatus(account)) {
                        System.out.println("Cập nhật thành công");
                    } else {
                        System.err.println("Cập nhật thất bại");
                    }
                    break;
                } else {
                    System.err.println("Không tồn tại mã tài khoản này!");
                }
            } else {
                System.err.println("Mã nhập vào không hợp lệ. Vui lòng nhập lại!");
            }
        }
    }

    public void getAccountByName(Scanner scanner) {
        while (true) {
            System.out.print("Nhập vào tên đăng nhập muốn tìm: ");
            String userName = scanner.nextLine();
            if (Validation.isValidLength(userName, STR_MAX_LENGTH)) {
                PaginationPresentation.getListPagination(scanner, accountPaginationBusiness, "accounts", userName);
                break;
            } else {
                System.err.printf("Tên đăng nhập không được để trống hoặc quá %d ký tự!\n", STR_MAX_LENGTH);
            }
        }
    }

    public String inputUserName(Scanner scanner) {
        while (true) {
            System.out.print("Nhập vào tên tài khoản: ");
            String userName = scanner.nextLine();
            if (Validation.isValidLength(userName, STR_MAX_LENGTH)) {
                boolean isExist = accountBusiness.checkExistAccountName(userName);
                if (!isExist) {
                    return userName;
                } else {
                    System.err.println("Tên tài khoản đã tồn tại. Vui lòng nhập lại!");
                }
            } else {
                System.err.printf("Thông tin nhập vào rỗng hoặc vượt quá %d ký tự. Vui lòng nhập lại!\n", STR_MAX_LENGTH);
            }
        }
    }

    public String inputPassword(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào mật khẩu: ");
            String password = scanner.nextLine();
            if (Validation.isValidPassword(password)) {
                return password;
            } else {
                System.err.println("Mật khẩu không hợp lệ. Vui lòng nhập lại!");
            }
        }
    }

    public String inputEmpId(Scanner scanner) {
        while (true) {
            System.out.print("Nhập vào mã nhân viên: ");
            String empId = scanner.nextLine();
            if (Validation.isValidLength(empId, ID_MAX_LENGTH)) {
                boolean isExistInAccountTable = accountBusiness.checkExistEmpId(empId);
                if (!isExistInAccountTable) {
                    if (employeeBusiness.getEmployeeById(empId) != null) {
                        return empId;
                    } else {
                        System.err.println("Không tồn tại mã nhân viên này. Vui lòng nhập lại!");
                    }
                } else {
                    System.err.println("Mã nhân viên này đã tồn tại ở 1 tài khoản khác. Vui lòng nhập mã khác!");
                }
            } else {
                System.err.println("Mã nhân viên rỗng hoặc vượt quá 5 ký tự. Vui lòng nhập lại!");
            }

        }
    }

    public boolean inputAccStatus(Scanner scanner) {
        while (true) {
            System.out.print("Nhập vào trạng thái tài khoản (true | false): ");
            String status = scanner.nextLine();
            if (Validation.isValidType(status, "Boolean")) {
                return Boolean.parseBoolean(status);
            } else {
                System.err.println("Trạng thái nhập vào không hợp lệ");
            }
        }
    }
}
