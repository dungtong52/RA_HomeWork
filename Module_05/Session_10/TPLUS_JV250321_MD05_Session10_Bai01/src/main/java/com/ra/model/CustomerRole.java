package com.ra.model;

public enum CustomerRole {
    ADMIN("Admin"),
    CUSTOMER("Customer");

    private final String showRole;

    CustomerRole(String showRole) {
        this.showRole = showRole;
    }
}
