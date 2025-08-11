package com.ra.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {
    private Integer id;

    @NotBlank(message = "Tên không được để trống")
    @Size(max = 100, message = "Độ dài không được vượt quá 100 ký tự")
    private String name;

    @Min(value = 18, message = "Độ tuổi tối thiểu là 18 tuổi")
    private Integer age;
    private String className;
    private String email;
    private String address;
    private String phone;
}
