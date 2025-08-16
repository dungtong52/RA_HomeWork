package com.ra.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "doctors", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"phone"})
})
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Họ tên không được để trống")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotEmpty(message = "Chuyên khoa không được để trống")
    @Column(name = "specialization", nullable = false)
    private String specialization;

    @NotEmpty(message = "Thông tin liên lạc không được để trống")
    @Column(name = "contact", nullable = false)
    private String contact;

    @NotEmpty(message = "Số điện thoại không được để trống")
    @Column(name = "phone", nullable = false, unique = true, length = 15)
    private String phone;

    @NotNull
    @Column(name = "status", nullable = false)
    private Boolean status = true;
}
