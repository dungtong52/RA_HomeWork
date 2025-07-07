package presentation;

import business.InvoiceManager;
import entity.Invoice;

import java.util.Scanner;

public class AppManager {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InvoiceManager invoiceManager = new InvoiceManager();
        do {
            System.out.println("\n------------ MENU QUẢN LÝ HÓA ĐƠN -------------");
            System.out.println("1. Thêm hóa đơn");
            System.out.println("2. Sửa hóa đơn");
            System.out.println("3. Xóa hóa đơn");
            System.out.println("4. Hiển thị thông tin hóa đơn");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    Invoice invoice = new Invoice();
                    invoice.inputData(scanner);
                    invoiceManager.add(invoice);
                    break;
                case 2:
                    invoiceManager.display();
                    System.out.print("Nhập vào id hóa đơn muốn sửa: ");
                    int idUpdate = Integer.parseInt(scanner.nextLine());
                    int indexUpdate = invoiceManager.findIndexById(idUpdate);

                    Invoice updateInvoice = invoiceManager.invoiceList.get(indexUpdate);
                    updateInvoice.setInvoiceCode(updateInvoice.inputInvoiceCode(scanner));
                    updateInvoice.setAmount(updateInvoice.inputAmount(scanner));

                    invoiceManager.update(indexUpdate, updateInvoice);
                    break;
                case 3:
                    System.out.print("Nhập vào id hóa đơn muốn sửa: ");
                    int idDelete = Integer.parseInt(scanner.nextLine());
                    int indexDelete = invoiceManager.findIndexById(idDelete);

                    invoiceManager.delete(indexDelete);
                    break;
                case 4:
                    invoiceManager.display();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Nhập số từ 1-5");
            }
        } while (true);
    }
}
