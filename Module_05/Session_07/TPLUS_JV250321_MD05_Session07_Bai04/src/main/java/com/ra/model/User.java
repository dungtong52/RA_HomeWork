package com.ra.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotBlank(message = "Tên không được để trống")
    private String name;
    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "Email không đúng định dạng")
    private String email;
}
