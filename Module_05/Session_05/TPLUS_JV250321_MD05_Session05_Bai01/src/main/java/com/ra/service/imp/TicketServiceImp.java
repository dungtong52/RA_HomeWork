package com.ra.service.imp;

import com.ra.dao.TicketDAO;
import com.ra.dao.imp.TicketDAOImp;
import com.ra.service.TicketService;

import java.util.List;

public class TicketServiceImp implements TicketService {
    private final TicketDAO ticketDAO;

    public TicketServiceImp() {
        ticketDAO = new TicketDAOImp();
    }

    @Override
    public List<String> getSeatByScheduleId(long scheduleId) {
        return ticketDAO.getSeatByScheduleId(scheduleId);
    }

    @Override
    public boolean bookTicket(long scheduleId, long accountId, String seatNumber) {
        return ticketDAO.bookTicket(scheduleId, accountId, seatNumber);
    }
}
