package business;

import dao.BillReceiptDAO;
import dao.imp.ReceiptDAOImp;
import entity.Bill;

public class BaseBillReceiptBusinessImp implements BillReceiptBusiness {
    private final BillReceiptDAO billReceiptDAO;

    public BaseBillReceiptBusinessImp() {
        billReceiptDAO = new ReceiptDAOImp();
    }

    @Override
    public long createBill(Bill bill) {
        return billReceiptDAO.createBill(bill);
    }

    @Override
    public boolean checkExistBillCode(String billCode) {
        return billReceiptDAO.checkExistBillCode(billCode);
    }

    @Override
    public Bill findBillByCode(String billCode) {
        return billReceiptDAO.findBillByCode(billCode);
    }


    @Override
    public boolean updateBill(Bill bill) {
        return billReceiptDAO.updateBill(bill);
    }
}
