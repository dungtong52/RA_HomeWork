package com.ra.repository.impl;

import com.ra.model.Booking;
import com.ra.repository.BookingRepository;
import com.ra.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;

@Repository
public class BookingRepositoryImpl implements BookingRepository {

    @Override
    public boolean addBooking(Booking booking) {
        Connection connection = null;
        CallableStatement cs = null;
        try {
            connection = ConnectionDB.openConnection();
            cs = connection.prepareCall("{call add_booking(?,?,?,?)}");
            cs.setLong(1, booking.getCustomerId());
            cs.setLong(2, booking.getRoomId());
            cs.setDate(3, Date.valueOf(booking.getBookingDate()));
            cs.setDouble(4, booking.getPrice());
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, cs);
        }
        return false;
    }
}
