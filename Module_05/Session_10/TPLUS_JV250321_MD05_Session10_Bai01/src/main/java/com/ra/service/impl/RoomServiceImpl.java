package com.ra.service.impl;

import com.ra.model.PaginationResult;
import com.ra.model.Room;
import com.ra.repository.RoomRepository;
import com.ra.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public boolean addRoom(Room room) {
        return roomRepository.addRoom(room);
    }

    @Override
    public boolean updateRoom(Room room) {
        return roomRepository.updateRoom(room);
    }

    @Override
    public boolean deleteRoom(long id) {
        return roomRepository.deleteRoom(id);
    }

    @Override
    public boolean updateRoomStatus(long id, String status) {
        return roomRepository.updateRoomStatus(id, status);
    }

    @Override
    public boolean isRoomNameUnique(Long id, String roomName) {
        return roomRepository.isRoomNameUnique(id, roomName);
    }

    @Override
    public Room findById(long id) {
        return roomRepository.findById(id);
    }

    @Override
    public PaginationResult<Room> getPaginationData(Room item, int size, int current) {
        return roomRepository.getRooms(size, current);
    }
}
