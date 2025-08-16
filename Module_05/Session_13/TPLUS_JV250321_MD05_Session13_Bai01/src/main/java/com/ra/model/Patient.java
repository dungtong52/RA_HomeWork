package com.ra.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "patients", uniqueConstraints =
@UniqueConstraint(columnNames = {"phone"}))
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Họ tên không được để trống")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "dob")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @NotEmpty(message = "Liên hệ không được để trống")
    @Column(name = "contact", nullable = false)
    private String contact;

    @NotEmpty(message = "Số điện thoại không được để trống")
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotNull(message = "Trạng thái không được để trống")
    @Column(name = "status", nullable = false)
    private Boolean status = true;

}
