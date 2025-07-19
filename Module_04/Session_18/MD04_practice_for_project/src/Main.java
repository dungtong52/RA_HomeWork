import business.AccountBusiness;
import business.imp.AccountBusinessImp;
import dao.AccountDAO;
import dao.imp.AccountDAOImp;
import entity.Account;
import presentation.WarehouseManagement;
import validation.Validation;

import java.util.Scanner;

public class Main {
    private final AccountBusiness accountBusiness;
    private final WarehouseManagement warehouseManagement;

    public Main() {
        accountBusiness = new AccountBusinessImp();
        warehouseManagement = new WarehouseManagement();
    }

    public boolean accountLogin(Scanner scanner) {
        while (true) {
            System.out.print("Nhập tên tài khoản: ");
            String userName = scanner.nextLine();
            System.out.print("Nhập mật khẩu: ");
            String password = scanner.nextLine();

            if (Validation.isValidUserName(userName) && Validation.isValidPassword(password)) {
                Account account = accountBusiness.getAccountToLogin(userName, password);
                if (account != null) {
                    return account.isPermission();
                } else {
                    System.err.println("Tên tài khoản hoặc mật khẩu không đúng. Vui lòng nhập lại");
                }
            } else {
                System.err.println("Tên người dùng và mật khẩu không hợp lệ. Vui lòng nhập lại");
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        if (main.accountLogin(scanner)) {
            main.warehouseManagement.warehouseMenuForUser(scanner);
        } else {
            main.warehouseManagement.warehouseMenuForAdmin(scanner);
        }
    }
}
