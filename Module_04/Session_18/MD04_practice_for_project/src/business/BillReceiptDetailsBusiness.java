package business;

import entity.BillDetail;

import java.util.List;

public interface BillReceiptDetailsBusiness {
    boolean createBatchDetails(List<BillDetail> billDetailList);

    boolean updateReceiptDetails(List<BillDetail> billDetailList);
}
