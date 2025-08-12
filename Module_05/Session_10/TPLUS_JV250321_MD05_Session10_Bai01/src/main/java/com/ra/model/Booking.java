package com.ra.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Booking {
    private Long id;
    private Long customerId;
    private Long roomId;
    private LocalDate bookingDate;
    private Double price;
}
