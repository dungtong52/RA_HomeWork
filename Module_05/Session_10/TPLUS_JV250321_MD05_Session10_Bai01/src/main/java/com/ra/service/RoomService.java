package com.ra.service;

import com.ra.model.Room;

public interface RoomService extends PaginationService<Room>{
    boolean addRoom(Room room);

    boolean updateRoom(Room room);

    boolean deleteRoom(long id);

    boolean updateRoomStatus(long id, String status);

    boolean isRoomNameUnique(Long id, String roomName);

    Room findById(long id);
}
