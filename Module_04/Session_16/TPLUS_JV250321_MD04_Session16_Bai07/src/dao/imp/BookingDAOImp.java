package dao.imp;

import dao.BookingDAO;
import entity.Room;
import utils.ConnectionDB;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImp implements BookingDAO {
    @Override
    public boolean bookRoom(int roomId, String customerName, LocalDate startDate, LocalDate endDate) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call book_room(?,?,?,?)}");
            callableStatement.setInt(1, roomId);
            callableStatement.setString(2, customerName);
            callableStatement.setDate(3, Date.valueOf(startDate));
            callableStatement.setDate(4, Date.valueOf(endDate));
            int rows = callableStatement.executeUpdate();
            if (rows > 0) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình đặt phòng: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public boolean cancelBooking(int bookingId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call cancel_booking(?)}");
            callableStatement.setInt(1, bookingId);
            int rows = callableStatement.executeUpdate();
            if (rows > 0) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình hủy đặt phòng: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public List<Room> listAvailableRooms() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Room> roomList = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call list_available_rooms}");
            ResultSet resultSet = callableStatement.executeQuery();
            roomList = new ArrayList<>();
            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setRoomNumber(resultSet.getString("room_number"));
                room.setType(resultSet.getString("type"));
                room.setPrice(resultSet.getBigDecimal("price"));
                room.setBooked(resultSet.getBoolean("is_booked"));
                roomList.add(room);
            }
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình hiển thị phòng trống: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return roomList;
    }
}
