package com.ra.validator;

import com.ra.model.Customer;
import com.ra.service.CustomerService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValid implements ConstraintValidator<PhoneNumberUnique, Customer> {
    private final CustomerService customerService;

    public PhoneNumberValid(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void initialize(PhoneNumberUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Customer customer, ConstraintValidatorContext context) {
        return customerService.isPhoneNumberUnique(
                customer.getId() == null ? 0 : customer.getId(),
                customer.getPhoneNumber()
        );
    }
}
