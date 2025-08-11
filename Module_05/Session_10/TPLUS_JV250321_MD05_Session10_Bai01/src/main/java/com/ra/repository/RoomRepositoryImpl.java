package com.ra.repository;

import com.ra.model.PaginationResult;
import com.ra.model.Room;
import com.ra.model.RoomStatus;
import com.ra.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomRepositoryImpl implements RoomRepository {
    @Override
    public boolean addRoom(Room room) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call add_room(?,?,?,?,?)}");
            callableStatement.setString(1, room.getRoomName());
            callableStatement.setString(2, room.getRoomType());
            callableStatement.setString(3, room.getStatus().name());
            callableStatement.setDouble(4, room.getPrice());
            callableStatement.setString(5, room.getImage());
            return callableStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public boolean updateRoom(Room room) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call update_room(?,?,?,?,?,?)}");
            callableStatement.setLong(1, room.getId());
            callableStatement.setString(2, room.getRoomName());
            callableStatement.setString(3, room.getRoomType());
            callableStatement.setString(4, room.getStatus().name());
            callableStatement.setDouble(5, room.getPrice());
            callableStatement.setString(6, room.getImage());
            return callableStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public boolean deleteRoom(long id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call delete_room(?)}");
            callableStatement.setLong(1, id);
            return callableStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public boolean updateRoomStatus(long id, String status) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call update_room_status(?,?)}");
            callableStatement.setLong(1, id);
            callableStatement.setString(2, status);
            return callableStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public PaginationResult<Room> getRooms(int size, int currentPage) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        PaginationResult<Room> roomPaginationResult = new PaginationResult<>();
        List<Room> roomList = new ArrayList<>();
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_rooms(?,?,?)}");
            callableStatement.setInt(1, size);
            callableStatement.setInt(2, currentPage);
            callableStatement.registerOutParameter(3, Types.INTEGER);

            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getLong("id"));
                room.setRoomName(rs.getString("room_name"));
                room.setRoomType(rs.getString("room_type"));
                room.setStatus(RoomStatus.valueOf(rs.getString("status")));
                room.setDelete(rs.getBoolean("is_delete"));
                room.setPrice(rs.getDouble("price"));
                room.setImage(rs.getString("image"));
                roomList.add(room);
            }

            roomPaginationResult.setTotalPages(callableStatement.getInt(3));
            roomPaginationResult.setDataList(roomList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return roomPaginationResult;
    }

    @Override
    public boolean isRoomNameUnique(Long id, String roomName) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call is_room_name_unique(?,?)}");
            if (id == null) {
                callableStatement.setNull(1, Types.BIGINT);
            } else {
                callableStatement.setLong(1, id);
            }
            callableStatement.setString(2, roomName);
            ResultSet rs = callableStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("cnt") == 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public Room findById(long id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Room room = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_room_by_id(?)}");
            callableStatement.setLong(1, id);
            ResultSet rs = callableStatement.executeQuery();
            if (rs.next()) {
                room = new Room();
                room.setId(rs.getLong("id"));
                room.setRoomName(rs.getString("room_name"));
                room.setRoomType(rs.getString("room_type"));
                room.setStatus(RoomStatus.valueOf(rs.getString("status")));
                room.setDelete(rs.getBoolean("is_delete"));
                room.setPrice(rs.getDouble("price"));
                room.setImage(rs.getString("image"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return room;
    }
}
