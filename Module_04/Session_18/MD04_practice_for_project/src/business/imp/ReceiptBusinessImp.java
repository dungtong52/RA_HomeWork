package business.imp;

import business.BaseBillReceiptBusinessImp;
import business.PaginationBusiness;
import dao.BillReceiptDAO;
import dao.imp.ReceiptDAOImp;
import entity.Bill;
import entity.PaginationResult;

public class ReceiptBusinessImp extends BaseBillReceiptBusinessImp implements PaginationBusiness<Bill> {
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
}
