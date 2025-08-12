package com.ra.service.impl;

import com.ra.model.Customer;
import com.ra.repository.CustomerRepository;
import com.ra.model.PaginationResult;
import com.ra.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer isLogin(String input, String password) {
        return customerRepository.isLogin(input, password);
    }

    @Override
    public boolean isPhoneNumberUnique(long id, String phoneNumber) {
        return customerRepository.isPhoneNumberUnique(id, phoneNumber);
    }

    @Override
    public boolean isEmailUnique(long id, String email) {
        return customerRepository.isEmailUnique(id, email);
    }

    @Override
    public boolean saveCustomer(Customer customer) {
        return customerRepository.saveCustomer(customer);
    }

    @Override
    public PaginationResult<Customer> getPaginationData(Customer item, int size, int currentPage) {
        return customerRepository.getAllCustomer(size, currentPage);
    }
}
