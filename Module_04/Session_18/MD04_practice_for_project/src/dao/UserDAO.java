package dao;

import entity.Bill;
import entity.PaginationResult;

public interface UserDAO {
    PaginationResult<Bill> getAllReceiptForUser(String empId, int size, int currentPage);

    PaginationResult<Bill> getAllBillForUser(String empId, int size, int currentPage);
}
