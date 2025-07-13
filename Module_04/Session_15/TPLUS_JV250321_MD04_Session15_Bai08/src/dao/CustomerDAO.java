package dao;

import entity.Customer;

import java.util.List;

public interface CustomerDAO {
    boolean updateCustomer(int id, Customer customer);
    Customer findCustomerById(int id);
}
