package dao.imp;

import dao.TicketDAO;
import entity.Movie;
import entity.Ticket;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImp implements TicketDAO {
    @Override
    public boolean createTicket(Ticket ticket) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call create_ticket(?,?,?)}");
            callableStatement.setInt(1, ticket.getShowtimeId());
            callableStatement.setString(2, ticket.getSeatNumber());
            callableStatement.setString(3, ticket.getCustomerName());
            callableStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public List<Ticket> getTicketByShowtime(int showtimeId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Ticket> ticketList = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_ticket(?)}");
            callableStatement.setInt(1, showtimeId);
            ResultSet resultSet = callableStatement.executeQuery();
            ticketList = new ArrayList<>();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setTicketId(resultSet.getInt("ticket_id"));
                ticket.setShowtimeId(resultSet.getInt("showtime_id"));
                ticket.setSeatNumber(resultSet.getString("seat_number"));
                ticket.setCustomerName(resultSet.getString("customer_name"));
                ticketList.add(ticket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return ticketList;
    }
}
