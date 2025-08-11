package com.ra.repository;

import com.ra.model.Customer;
import com.ra.model.PaginationResult;
import com.ra.model.Room;

import java.util.List;

public interface RoomRepository {
    boolean addRoom(Room room);

    boolean updateRoom(Room room);

    boolean deleteRoom(long id);

    boolean updateRoomStatus(long id, String status);

    PaginationResult<Room> getRooms(int limit, int offset);

    boolean isRoomNameUnique(Long id, String roomName);

    Room findById(long id);
}
