package com.ra.validator;

import com.ra.model.Customer;
import com.ra.service.CustomerService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValid implements ConstraintValidator<EmailUnique, Customer> {
    private final CustomerService customerService;

    public EmailValid(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void initialize(EmailUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Customer customer, ConstraintValidatorContext context) {
        return customerService.isPhoneNumberUnique(
                customer.getId() == null ? 0 : customer.getId(),
                customer.getEmail()
        );
    }
}
