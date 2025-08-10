package com.ra.repository;

import com.ra.model.Bus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository {
    List<Bus> getAllBus();

    boolean addBus(Bus bus);

    boolean updateBus(Bus bus);

    boolean deleteBus(long id);

    Bus getBusById(long id);

    boolean isLicensePlateUnique(long id, String licensePlate);
}
