package com.ra.model;

import com.ra.model.enumClass.TicketStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ticket {
    private long id;
    private long scheduleId;
    private long customerId;
    private String seatNumber;
    private LocalDateTime bookingTime;
    private float price;
    private TicketStatusEnum status;
}
