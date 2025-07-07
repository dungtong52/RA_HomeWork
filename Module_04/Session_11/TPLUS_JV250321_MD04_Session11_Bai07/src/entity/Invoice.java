package entity;

import business.InvoiceManager;

import java.util.Scanner;

public class Invoice {
    private int id;
    private String invoiceCode;
    private double amount;

    public Invoice() {
    }

    public Invoice(int id, String invoiceCode, double amount) {
        this.id = id;
        this.invoiceCode = invoiceCode;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void inputData(Scanner scanner) {
        this.id = inputId();
        this.invoiceCode = inputInvoiceCode(scanner);
        this.amount = inputAmount(scanner);
    }

    public int inputId() {
        InvoiceManager invoiceManager = new InvoiceManager();
        int maxIndex = 0;
        for (Invoice invoice : invoiceManager.invoiceList) {
            if (invoice.getId() > maxIndex) {
                maxIndex = invoice.getId();
            }
        }
        return maxIndex + 1;
    }

    public String inputInvoiceCode(Scanner scanner) {
        System.out.print("Nhập mã hóa đơn: ");
        return scanner.nextLine();
    }

    public double inputAmount(Scanner scanner) {
        System.out.print("Nhập số tiền: ");
        do {
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount < 0) {
                System.err.println("Vui lòng nhập số thực >= 0!");
            } else {
                return amount;
            }
        } while (true);
    }

    public void displayInvoiceElement() {
        System.out.printf("ID: %d - Invoice Code: %s - Amount: %.1f", this.id, this.invoiceCode, this.amount);
    }
}
