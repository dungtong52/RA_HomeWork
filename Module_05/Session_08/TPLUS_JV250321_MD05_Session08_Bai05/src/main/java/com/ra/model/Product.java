package com.ra.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    private Integer id;

    @NotBlank(message = "Tên sản phẩm không được để trống.")
    @Size(max = 100, message = "Tên sản phẩm phải ít hơn 100 ký tự.")
    private String name;

    @NotNull(message = "Giá sản phẩm không được để trống.")
    @DecimalMin(value = "0.01", message = "Giá sản phẩm phải lớn hơn 0.")
    private BigDecimal price;

    @NotNull(message = "Số lượng sản phẩm không được để trống.")
    @Min(value = 1, message = "Số lượng phải lớn hơn hoặc bằng 1.")
    private Integer quantity;

    @Size(max = 255, message = "Đường dẫn hình ảnh phải ít hơn 255 ký tự.")
    private String image;
}
