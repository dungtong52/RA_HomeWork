package com.ra.service;

import com.ra.model.Bus;

import java.util.List;

public interface BusService {
    List<Bus> getAllBus();

    boolean addBus(Bus bus);

    boolean updateBus(Bus bus);

    boolean deleteBus(long id);

    Bus getBusById(long id);

    boolean isLicensePlateUnique(long id, String licensePlate);

}
