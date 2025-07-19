package dao;

import entity.BillDetail;
import entity.PaginationResult;

import java.util.List;

public interface BillReceiptDetailDAO {
    boolean createBatchDetails(List<BillDetail> billDetailList);

    PaginationResult<BillDetail> getReceiptDetailsByBillId(long billId, int size, int currentPage);

    boolean updateReceiptDetails(List<BillDetail> billDetailList);
}
