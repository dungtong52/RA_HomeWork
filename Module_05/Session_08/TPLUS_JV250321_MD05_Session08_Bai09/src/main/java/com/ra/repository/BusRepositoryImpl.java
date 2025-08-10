package com.ra.repository;

import com.ra.model.Bus;
import com.ra.model.BusType;
import com.ra.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BusRepositoryImpl implements BusRepository {
    public List<Bus> getAllBus() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Bus> busList = new ArrayList<>();
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_all_bus()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Bus bus = new Bus();
                bus.setId(resultSet.getLong("id"));
                bus.setLicensePlate(resultSet.getString("license_plate"));
                bus.setBusType(BusType.valueOf(resultSet.getString("bus_type")));
                bus.setRowSeat(resultSet.getInt("row_seat"));
                bus.setColSeat(resultSet.getInt("col_seat"));
                bus.setTotalSeat(resultSet.getInt("total_seat"));
                bus.setImage(resultSet.getString("image"));
                busList.add(bus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return busList;
    }

    public boolean addBus(Bus bus) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call add_bus(?,?,?,?,?)}");
            callableStatement.setString(1, bus.getLicensePlate());
            callableStatement.setString(2, String.valueOf(bus.getBusType()));
            callableStatement.setInt(3, bus.getRowSeat());
            callableStatement.setInt(4, bus.getColSeat());
            callableStatement.setString(5, bus.getImage());
            return callableStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    public boolean updateBus(Bus bus) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call update_bus(?,?,?,?,?,?)}");
            callableStatement.setLong(1, bus.getId());
            callableStatement.setString(2, bus.getLicensePlate());
            callableStatement.setString(3, String.valueOf(bus.getBusType()));
            callableStatement.setInt(4, bus.getRowSeat());
            callableStatement.setInt(5, bus.getColSeat());
            callableStatement.setString(6, bus.getImage());
            return callableStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    public boolean deleteBus(long id) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call delete_bus(?)}");
            callableStatement.setLong(1, id);
            return callableStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    public Bus getBusById(long id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Bus bus = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_bus_by_id(?)}");
            callableStatement.setLong(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                bus = new Bus();
                bus.setId(resultSet.getLong("id"));
                bus.setLicensePlate(resultSet.getString("license_plate"));
                bus.setBusType(BusType.valueOf(resultSet.getString("bus_type")));
                bus.setRowSeat(resultSet.getInt("row_seat"));
                bus.setColSeat(resultSet.getInt("col_seat"));
                bus.setTotalSeat(resultSet.getInt("total_seat"));
                bus.setImage(resultSet.getString("image"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return bus;
    }

    @Override
    public boolean isLicensePlateUnique(long id, String licensePlate) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call check_license_plate_unique(?,?)}");
            callableStatement.setLong(1, id);
            callableStatement.setString(2, licensePlate);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("cnt");
                return count == 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }
}
