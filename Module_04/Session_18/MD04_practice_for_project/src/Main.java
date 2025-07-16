import dao.AccountDAO;
import dao.imp.AccountDAOImp;
import entity.Account;
import presentation.WarehouseManagement;
import validation.Validation;

import java.util.Scanner;

public class Main {
    private final AccountDAO accountDAO;
    private final WarehouseManagement warehouseManagement;

    public Main() {
        accountDAO = new AccountDAOImp();
        warehouseManagement = new WarehouseManagement();
    }

    public boolean accountLogin(Scanner scanner) {
        while (true) {
            System.out.print("Nhập tên tài khoản: ");
            String userName = scanner.nextLine();
            System.out.print("Nhập mật khẩu: ");
            String password = scanner.nextLine();

            if (Validation.isValidUserName(userName) && Validation.isValidPassword(password)) {
                Account account = accountDAO.getAccountByUserName(userName);
                if (account != null) {
                    if (account.getPassword().equals(password)) {
                        return account.isPermission();
                    } else {
                        System.out.println("Nhập sai mật khẩu!");
                    }
                } else {
                    System.out.println("Không tồn tại tài khoản: " + userName);
                }
            } else {
                System.out.println("Tên người dùng và mật khẩu không được để trống, không quá 30 ký tự");
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
