package com.ra.service;

import com.ra.model.Customer;

public interface CustomerService extends PaginationService<Customer> {
    Customer isLogin(String input, String password);

    boolean isPhoneNumberUnique(long id, String phoneNumber);

    boolean isEmailUnique(long id, String email);

    boolean saveCustomer(Customer customer);
}
