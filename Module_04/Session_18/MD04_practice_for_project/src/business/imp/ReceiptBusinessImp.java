package business.imp;

import business.BillReceiptBusiness;
import business.PaginationBusiness;
import dao.BillReceiptDAO;
import dao.imp.ReceiptDAOImp;
import entity.Bill;
import entity.PaginationResult;

public class ReceiptBusinessImp implements BillReceiptBusiness, PaginationBusiness<Bill> {
    private final BillReceiptDAO billReceiptDAO;

    public ReceiptBusinessImp() {
        billReceiptDAO = new ReceiptDAOImp();
    }

    @Override
    public PaginationResult<Bill> getPaginationData(String key, int size, int currentPage) {
        if (key == null) {
            return billReceiptDAO.getBillPagination(size, currentPage);
        }
        return null;
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
