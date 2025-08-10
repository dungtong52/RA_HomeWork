package com.ra.entity;

import com.ra.validator.ValidPhone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class userForm {
    @NotBlank(message = "Phone không được để trống")
    @ValidPhone
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public userForm(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public userForm() {
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
