package business.imp;

import business.CustomerBusiness;
import dao.CustomerDAO;
import dao.imp.CustomerDAOImp;
import entity.Customer;
import validation.Validation;

import java.util.Scanner;

public class CustomerBusinessImp implements CustomerBusiness {
    private final CustomerDAO customerDAO = new CustomerDAOImp();

    @Override
    public void updateCustomer(Scanner scanner) {
        System.out.print("Nhập ID khách hàng cần cập nhật: ");
        String customerId = scanner.nextLine();
        if (Validation.isPositiveInteger(customerId)) {
            Customer existing = customerDAO.findCustomerById(Integer.parseInt(customerId));
            if (existing != null) {
                Customer customer = new Customer();
                customer.inputData(scanner);

                boolean success = customerDAO.updateCustomer(Integer.parseInt(customerId), customer);
                if (success) {
                    System.out.println("Cập nhật khách hàng thành công.");
                } else {
                    System.err.println("Cập nhật thất bại.");
                }
            } else {
                System.err.println("Không tìm thấy khách hàng.");
            }
        } else {
            System.err.println("Mã sản phẩm không hợp lệ!");
        }
    }
}
