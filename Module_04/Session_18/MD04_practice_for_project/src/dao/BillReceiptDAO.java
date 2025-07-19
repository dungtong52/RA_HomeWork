package dao;

import entity.Bill;
import entity.PaginationResult;

public interface BillReceiptDAO {
    PaginationResult<Bill> getBillPagination(int size, int currentPage);

    long createBill(Bill bill);

    boolean updateBill(Bill bill);

}
