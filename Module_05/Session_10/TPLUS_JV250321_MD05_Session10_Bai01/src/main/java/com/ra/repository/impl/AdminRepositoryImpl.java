package com.ra.repository.impl;

import com.ra.model.Customer;
import com.ra.model.Room;
import com.ra.repository.AdminRepository;
import com.ra.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

    @Override
    public int countCustomers() {
        int count = 0;
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call count_customers(?)}");
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            count = cs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return count;
    }

    @Override
    public double getRevenueCurrentMonth() {
        double revenue = 0;
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call get_revenue_current_month(?)}");
            cs.registerOutParameter(1, Types.DOUBLE);
            cs.execute();
            revenue = cs.getDouble(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return revenue;
    }

    @Override
    public List<Room> getTop3RoomsBooked() {
        List<Room> rooms = new ArrayList<>();
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call get_top3_rooms()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getLong("id"));
                room.setRoomName(rs.getString("room_name"));
                rooms.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return rooms;
    }

    @Override
    public List<Customer> getTop5CustomersBooked() {
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call get_top5_customers()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setFullName(rs.getString("full_name"));
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return customers;
    }
}
