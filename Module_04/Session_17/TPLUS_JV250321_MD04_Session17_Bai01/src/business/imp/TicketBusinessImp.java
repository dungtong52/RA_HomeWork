package business.imp;

import business.TicketBusiness;
import dao.TicketDAO;
import dao.imp.TicketDAOImp;
import entity.Ticket;

import java.util.List;

public class TicketBusinessImp implements TicketBusiness {
    private final TicketDAO ticketDAO = new TicketDAOImp();

    @Override
    public boolean createTicket(Ticket ticket) {
        return ticketDAO.createTicket(ticket);
    }

    @Override
    public List<Ticket> getTicketByShowtime(int showtimeId) {
        return ticketDAO.getTicketByShowtime(showtimeId);
    }
}
