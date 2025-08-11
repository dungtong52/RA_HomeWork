package com.ra.model;

import com.ra.validator.EmailUnique;
import com.ra.validator.PhoneNumberUnique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    private Long id;

    @Size(max = 100, message = "Tên có độ dài không vượt quá 100 ký tự")
    private String fullName;

    @PhoneNumberUnique
    @Size(max = 20, message = "Số điện thoại có độ dài không vượt quá 20 ký tự")
    private String phoneNumber;

    @EmailUnique
    @Size(max = 100, message = "Email có độ dài không vượt quá 100 ký tự")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(max = 100, message = "Mật khẩu có độ dài không vượt quá 100 ký tự")
    private String password;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;

    @NotNull(message = "Role không được để trống")
    private CustomerRole role;
}
