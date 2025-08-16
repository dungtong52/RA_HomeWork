package com.ra.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentDto {
    @NotNull(message = "Ngày khám không được để trống")
    private LocalDate date;

    @NotNull(message = "Giờ khám không được để trống")
    @Min(value = 0, message = "Giờ phải từ 0 đến 23")
    @Max(value = 23, message = "Giờ phải từ 0 đến 23")
    private Integer hour;

    @NotNull(message = "Phải chọn bệnh nhân")
    private Long doctorId;

    @NotNull(message = "Phải chọn bác sĩ")
    private Long patientId;
}
