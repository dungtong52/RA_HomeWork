package business.imp;

import business.BillReceiptDetailsBusiness;
import business.PaginationBusiness;
import dao.BillReceiptDetailDAO;
import dao.imp.ReceiptDetailsDAOImp;
import entity.BillDetail;
import entity.PaginationResult;

import java.util.List;

public class ReceiptDetailsBusinessImp implements BillReceiptDetailsBusiness, PaginationBusiness<BillDetail> {
    private final BillReceiptDetailDAO billReceiptDetailDAO;

    public ReceiptDetailsBusinessImp() {
        billReceiptDetailDAO = new ReceiptDetailsDAOImp();
    }

    @Override
    public boolean createBatchDetails(List<BillDetail> billDetailList) {
        return billReceiptDetailDAO.createBatchDetails(billDetailList);
    }

    @Override
    public boolean updateReceiptDetails(List<BillDetail> billDetailList) {
        return billReceiptDetailDAO.updateReceiptDetails(billDetailList);
    }

    @Override
    public PaginationResult<BillDetail> getPaginationData(String key, int size, int currentPage) {
        return billReceiptDetailDAO.getReceiptDetailsByBillId(Long.parseLong(key), size, currentPage);
    }
}
