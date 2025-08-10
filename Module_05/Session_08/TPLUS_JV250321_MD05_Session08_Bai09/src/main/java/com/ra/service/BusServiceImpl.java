package com.ra.service;

import com.ra.model.Bus;
import com.ra.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServiceImpl implements BusService {
    @Autowired
    private BusRepository busRepository;

    @Override
    public List<Bus> getAllBus() {
        return busRepository.getAllBus();
    }

    @Override
    public boolean addBus(Bus bus) {
        return busRepository.addBus(bus);
    }

    @Override
    public boolean updateBus(Bus bus) {
        return busRepository.updateBus(bus);
    }

    @Override
    public boolean deleteBus(long id) {
        return busRepository.deleteBus(id);
    }

    @Override
    public Bus getBusById(long id) {
        return busRepository.getBusById(id);
    }

    @Override
    public boolean isLicensePlateUnique(long id, String licensePlate) {
        return busRepository.isLicensePlateUnique(id, licensePlate);
    }
}
