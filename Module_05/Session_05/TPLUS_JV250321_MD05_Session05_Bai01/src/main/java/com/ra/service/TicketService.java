package com.ra.service;

import java.util.List;

public interface TicketService {
    List<String> getSeatByScheduleId(long scheduleId);

    boolean bookTicket(long scheduleId, long accountId, String seatNumber);
}
