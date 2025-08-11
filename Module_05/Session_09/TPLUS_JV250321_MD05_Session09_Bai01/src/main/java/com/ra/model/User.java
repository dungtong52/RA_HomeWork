package com.ra.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private Long id;

    @NotBlank(message = "Tên không được để trống")
    @Size(min = 1, max = 100, message = "Tên phải có độ dài dưới 100 ký tự")
    private String name;

    @NotBlank(message = "Email không được để trống")
    @Pattern(regexp = "^[a-zA-Z0-9._]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,}$", message = "Email không đúng định dạng")
    private String email;

    @NotBlank(message = "Password không được để trống")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,}$", message = "Password không đúng định dạng")
    private String password;
}
