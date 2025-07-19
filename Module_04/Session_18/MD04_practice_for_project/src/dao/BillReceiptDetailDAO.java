package dao;

import entity.BillDetail;

import java.util.List;

public interface BillReceiptDetailDAO {
    boolean createBatchDetails(List<BillDetail> billDetailList);
}
