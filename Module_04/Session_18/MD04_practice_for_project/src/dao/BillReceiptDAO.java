package dao;

import entity.Bill;
import entity.PaginationResult;

public interface BillReceiptDAO {
    PaginationResult<Bill> getBillPagination(int size, int currentPage);

    boolean checkExistBillCode(String billCode);

    long createBill(Bill bill);

    Bill findBillByCode(String billCode);

    boolean updateBill(Bill bill);

}
