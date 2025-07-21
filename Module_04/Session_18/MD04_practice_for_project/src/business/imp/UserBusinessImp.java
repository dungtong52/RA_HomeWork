package business.imp;

import business.UserBusiness;
import entity.Bill;
import entity.PaginationResult;

public class UserBusinessImp implements UserBusiness {
    @Override
    public PaginationResult<Bill> getAllReceiptForUser(String empId, int size, int currentPage) {
        return ;
    }

    @Override
    public PaginationResult<Bill> getAllBillForUser(String empId, int size, int currentPage) {
        return null;
    }
}
