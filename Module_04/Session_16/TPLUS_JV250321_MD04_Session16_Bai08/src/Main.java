import business.AccountBusiness;
import business.TransactionBusiness;
import business.imp.AccountBusinessImp;
import business.imp.TransactionBusinessImp;
import dao.imp.AccountDAOImp;
import entity.Account;
import entity.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final AccountBusiness accountBusiness;
    private final TransactionBusiness transactionBusiness;

    public Main() {
        accountBusiness = new AccountBusinessImp();
        transactionBusiness = new TransactionBusinessImp();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        while (true) {
            System.out.println("************** Account Manager ****************");
            System.out.println("1. Chuyển tiền");
            System.out.println("2. Lấy thông tin tài khoản theo ID");
            System.out.println("3. Hiển thị lịch sử giao dịch");
            System.out.println("4. Thoát");
            System.out.print("Lựa chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    int fromId;
                    int toId;

                    while(true){
                        System.out.println("Nhập vào ID tài khoản chuyển tiền");
                        fromId = Integer.parseInt(scanner.nextLine());
                        if(AccountDAOImp.isExists(fromId)){
                            break;
                        } else {
                            System.err.println("Tài khoản không tồn tại!");
                        }
                    }
                    while(true){
                        System.out.println("Nhập vào ID tài khoản nhận tiền");
                        toId = Integer.parseInt(scanner.nextLine());
                        if(AccountDAOImp.isExists(toId)){
                            break;
                        } else if (fromId == toId) {
                            System.err.println("Tài khoản gửi và nhận không được trùng");
                        } else {
                            System.err.println("Tài khoản không tồn tại!");
                        }
                    }
                    System.out.println("Nhập số tiền muốn chuyển");
                    BigDecimal amount = new BigDecimal(scanner.nextLine());
                    boolean success = main.transactionBusiness.transferMoney(fromId, toId, amount);
                    if (success) {
                        System.out.println("Giao dịch thành công");
                    } else {
                        System.err.println("Giao dịch thất bại!");
                    }
                    break;
                case 2:
                    System.out.print("Nhập ID tài khoản muốn lấy thông tin: ");
                    Account account = main.accountBusiness.getAccountInfo(Integer.parseInt(scanner.nextLine()));
                    if (account == null) {
                        System.err.println("Không tồn tại!");
                    } else {
                        System.out.println(account);
                    }
                    break;
                case 3:
                    System.out.print("Nhập ID tài khoản muốn lấy thông tin: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    List<Transaction> transactionList = main.transactionBusiness.listTransactions(id);
                    if (transactionList.isEmpty()) {
                        System.out.println("Không có giao dịch nào!");
                    } else {
                        System.out.println("Danh sách các giao dịch:");
                        transactionList.forEach(System.out::println);
                    }
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.err.println("Nhập số nguyên 1-4");
            }
        }
    }
}
