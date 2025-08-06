package com.ra.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @NotBlank(message = "Tên người dùng không được để trống")
    private String username;

    @NotBlank(message = "Email không được để trống")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email không đúng định dạng")
    private String email;

    @Pattern(regexp = "^(0|\\\\+84)[0-9]{9}$", message = "Số điện thoại không đúng định dạng")
    private String phoneNumber;
}
