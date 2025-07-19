package business.imp;

import business.BillReceiptDetailsBusiness;
import dao.BillReceiptDetailDAO;
import dao.imp.ReceiptDetailsDAOImp;
import entity.BillDetail;

import java.util.List;

public class ReceiptDetailsBusinessImp implements BillReceiptDetailsBusiness {
    private final BillReceiptDetailDAO billReceiptDetailDAO;

    public ReceiptDetailsBusinessImp() {
        billReceiptDetailDAO = new ReceiptDetailsDAOImp();
    }

    @Override
    public boolean createBatchDetails(List<BillDetail> billDetailList) {
        return billReceiptDetailDAO.createBatchDetails(billDetailList);
    }
}
