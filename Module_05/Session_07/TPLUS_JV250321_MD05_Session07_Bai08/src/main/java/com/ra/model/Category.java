package com.ra.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    private int id;

    @NotBlank(message = "Tên danh mục không được để trống")
    @Size(max = 50, message = "Độ dài không quá 50 ký tự")
    private String categoryName;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    private boolean status = true;
}
