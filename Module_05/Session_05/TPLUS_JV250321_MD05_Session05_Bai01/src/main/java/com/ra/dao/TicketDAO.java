package com.ra.dao;

import java.util.List;

public interface TicketDAO {
    List<String> getSeatByScheduleId(long scheduleId);

    boolean bookTicket(long scheduleId, long accountId, String seatNumber);
}
