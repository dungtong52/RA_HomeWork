package business;

import entity.Ticket;

import java.util.List;

public interface TicketBusiness {
    boolean createTicket(Ticket ticket);

    List<Ticket> getTicketByShowtime(int showtimeId);
}
