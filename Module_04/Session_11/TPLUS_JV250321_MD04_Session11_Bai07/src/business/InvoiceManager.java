package business;

import entity.IManage;
import entity.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InvoiceManager implements IManage<Invoice> {
    public List<Invoice> invoiceList = new ArrayList<>();

    @Override
    public void add(Invoice invoice) {
        invoiceList.add(invoice);
        System.out.println("Thêm thành công");
    }

    @Override
    public void update(int index, Invoice invoice) {
        if (index == -1) {
            System.err.println("Hóa đơn không tồn tại!");
        } else {
            invoiceList.get(index).setInvoiceCode(invoice.getInvoiceCode());
            invoiceList.get(index).setAmount(invoice.getAmount());
        }
    }

    @Override
    public void delete(int index) {
        if (index == -1) {
            System.err.println("Hóa đơn không tồn tại!");
        } else {
            invoiceList.remove(index);
        }
    }

    @Override
    public void display() {
        System.out.println("Danh sách sinh viên:");
        for (Invoice invoice : invoiceList) {
            invoice.displayInvoiceElement();
        }
    }

    public int findIndexById(int id) {
        for (int i = 0; i < invoiceList.size(); i++) {
            if (invoiceList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
