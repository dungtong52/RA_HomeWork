package dao;

import entity.Ticket;

import java.util.List;

public interface TicketDAO {
    boolean createTicket(Ticket ticket);

    List<Ticket> getTicketByShowtime(int showtimeId);
}
