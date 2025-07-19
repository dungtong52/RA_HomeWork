package business;

import entity.Bill;

public interface BillReceiptBusiness {
    long createBill(Bill bill);

    boolean checkExistBillCode(String billCode);

    Bill findBillByCode(String billCode);

    boolean updateBill(Bill bill);
}
