package business;

import entity.BillDetail;

import java.util.List;

public interface BillReceiptDetailsBusiness {
    boolean createBatchDetails(List<BillDetail> billDetailList);

    BillDetail findBillDetailById(long billDetailId);

    boolean updateReceiptDetails(BillDetail billDetail);

    boolean acceptBill(long billId);
}
