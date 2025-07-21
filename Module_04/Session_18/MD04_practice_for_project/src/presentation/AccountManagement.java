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
    public static Account currentAccount = new Account();

    private final int EMP_NAME_MAX_LENGTH = 100;
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
            System.out.println("Lựa chọn của bạn:");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 5)) {
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
                        Account accountSearch = getAccountByName(scanner);
                        if (accountSearch == null) {
                            System.err.println("Không tìm thấy tài khoản nào!");
                            break;
                        }
                        System.out.println("Bạn có muốn cập nhật trạng thái tài khoản không? (True|False)");
                        while (true) {
                            String input = scanner.nextLine();
                            if (Validation.isValidType(input, "Boolean")) {
                                if (Boolean.parseBoolean(input)) {
                                    System.out.printf("Trạng thái hiện tại của tài khoản: %s\n", accountSearch.isAccStatus());
                                    accountSearch.setAccStatus(inputAccStatus(scanner));
                                    if (accountBusiness.updateAccountStatus(accountSearch)) {
                                        System.out.println("Cập nhật thành công");
                                        break;
                                    } else {
                                        System.err.println("Cập nhật thất bại");
                                    }
                                }
                            } else {
                                System.err.println("Giá trị nhập không hợp lệ. Vui lòng nhập lại!");
                            }
                        }
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
            System.out.println("Nhập vào mã tài khoản muốn cập nhật: ");
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

    public Account getAccountByName(Scanner scanner) {
        Account account;
        while (true) {
            System.out.println("1. Tìm tài khoản theo tên đăng nhập");
            System.out.println("2. Tìm tài khoản theo tên nhân viên");
            System.out.println("Lựa chọn:");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 2)) {
                if (Integer.parseInt(choice) == 1) {
                    String userName = inputUserNameForSearch(scanner);
                    account = accountBusiness.getAccountByUserName(userName);
                } else {
                    String empName = inputEmpNameForSearch(scanner);
                    account = accountBusiness.getAccountByEmpName(empName);
                }
                return account;
            } else {
                System.err.println("Số nhập vào không đúng!");
            }
        }
    }

    public String inputUserName(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào tên tài khoản: ");
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

    public String inputUserNameForSearch(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào tên tài khoản: ");
            String userName = scanner.nextLine();
            if (Validation.isValidLength(userName, STR_MAX_LENGTH)) {
                boolean isExist = accountBusiness.checkExistAccountName(userName);
                if (isExist) {
                    return userName;
                } else {
                    System.err.println("Tên tài khoản KHÔNG tồn tại. Vui lòng nhập lại!");
                }
            } else {
                System.err.printf("Thông tin nhập vào rỗng hoặc vượt quá %d ký tự. Vui lòng nhập lại!\n", STR_MAX_LENGTH);
            }
        }
    }

    public String inputEmpNameForSearch(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào tên nhân viên: ");
            String empName = scanner.nextLine();
            if (Validation.isValidLength(empName, EMP_NAME_MAX_LENGTH)) {
                boolean isExist = employeeBusiness.checkExistEmployeeName(empName);
                if (isExist) {
                    return empName;
                } else {
                    System.err.println("Tên nhân viên KHÔNG tồn tại. Vui lòng nhập lại!");
                }
            } else {
                System.err.printf("Thông tin nhập vào rỗng hoặc vượt quá %d ký tự. Vui lòng nhập lại!\n", EMP_NAME_MAX_LENGTH);
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
            System.out.println("Nhập vào mã nhân viên: ");
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
            System.out.println("Nhập vào trạng thái tài khoản (true - 'Active' | false - 'Block'): ");
            String status = scanner.nextLine();
            if (Validation.isValidType(status, "Boolean")) {
                return Boolean.parseBoolean(status);
            } else {
                System.err.println("Trạng thái nhập vào không hợp lệ");
            }
        }
    }


}
