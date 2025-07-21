package business;

import entity.Bill;
import entity.PaginationResult;

public interface UserBusiness {
    PaginationResult<Bill> getAllReceiptForUser(String empId, int size, int currentPage);

    PaginationResult<Bill> getAllBillForUser(String empId, int size, int currentPage);
}
