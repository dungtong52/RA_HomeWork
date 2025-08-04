package com.ra.dao.imp;

import com.ra.dao.TicketDAO;
import com.ra.utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImp implements TicketDAO {
    @Override
    public List<String> getSeatByScheduleId(long scheduleId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<String> bookedSeats = new ArrayList<>();

        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_seat_by_schedule_id(?)}");
            callableStatement.setLong(1, scheduleId);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                bookedSeats.add(resultSet.getString("seat_number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }

        return bookedSeats;
    }

    @Override
    public boolean bookTicket(long scheduleId, long customerId, String seatNumber) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        boolean isBooked = false;

        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call book_ticket(?, ?, ?)}");
            callableStatement.setLong(1, scheduleId);
            callableStatement.setLong(2, accountId);
            callableStatement.setString(3, seatNumber);

            callableStatement.executeUpdate();
            isBooked = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }

        return isBooked;
    }
}
