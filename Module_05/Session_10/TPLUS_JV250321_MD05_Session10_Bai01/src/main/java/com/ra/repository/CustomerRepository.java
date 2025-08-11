package com.ra.repository;

import com.ra.model.Customer;
import com.ra.model.PaginationResult;

public interface CustomerRepository {
    Customer isLogin(String input, String password);

    PaginationResult<Customer> getAllCustomer(int size, int currentPage);

    boolean isPhoneNumberUnique(long id, String phoneNumber);

    boolean isEmailUnique(long id, String email);

    boolean saveCustomer(Customer customer);

}
