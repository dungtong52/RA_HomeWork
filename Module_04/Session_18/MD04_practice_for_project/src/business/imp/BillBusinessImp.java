package business.imp;

import business.PaginationBusiness;
import business.BillReceiptBusiness;
import dao.BillReceiptDAO;
import dao.imp.BillDAOImp;
import entity.Bill;
import entity.PaginationResult;

public class BillBusinessImp implements BillReceiptBusiness, PaginationBusiness<Bill> {
    private final BillReceiptDAO billReceiptDAO;

    public BillBusinessImp() {
        billReceiptDAO = new BillDAOImp();
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
        return false;
    }

    @Override
    public Bill findBillByCode(String billCode) {
        return null;
    }

    @Override
    public boolean updateBill(Bill bill) {
        return false;
    }
}
