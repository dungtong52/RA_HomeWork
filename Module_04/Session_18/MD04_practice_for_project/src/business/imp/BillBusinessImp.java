package business.imp;

import business.BaseBillReceiptBusinessImp;
import business.PaginationBusiness;
import dao.BillReceiptDAO;
import dao.imp.BillDAOImp;
import entity.Bill;
import entity.PaginationResult;

public class BillBusinessImp extends BaseBillReceiptBusinessImp implements PaginationBusiness<Bill> {
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
}
