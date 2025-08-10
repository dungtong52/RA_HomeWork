package com.ra.model;

import com.ra.validator.PlateUnique;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@PlateUnique
public class Bus {
    private Long id;

    @NotBlank(message = "Biển số xe không được để trống.")
    @Size(min = 1, max = 20, message = "Số ký tự phải ít hơn 20")
    private String licensePlate;

    @NotNull(message = "Loại xe không được để trống.")
    private BusType busType;

    @NotNull(message = "Số hàng ghế không được để trống.")
    @Min(value = 1, message = "Số hàng ghế phải lớn hơn 1")

    @Max(value = 50, message = "Số hàng ghế phải nhỏ hơn 50")
    private Integer rowSeat;

    @NotNull(message = "Số cột ghế không được để trống.")
    @Min(value = 1, message = "Số cột ghế phải lớn hơn 1")
    @Max(value = 5, message = "Số cột ghế phải nhỏ hơn 5")
    private Integer colSeat;

    private Integer totalSeat;

    @Size(max = 250, message = "Phải ít hơn 250 ký tự")
    private String image;
}
