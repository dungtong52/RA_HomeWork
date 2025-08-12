package com.ra.service.impl;

import com.ra.model.Customer;
import com.ra.model.Room;
import com.ra.repository.AdminRepository;
import com.ra.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public int countCustomers() {
        return adminRepository.countCustomers();
    }

    @Override
    public double getRevenueCurrentMonth() {
        return adminRepository.getRevenueCurrentMonth();
    }

    @Override
    public List<Room> getTop3RoomsBooked() {
        return adminRepository.getTop3RoomsBooked();
    }

    @Override
    public List<Customer> getTop5CustomersBooked() {
        return adminRepository.getTop5CustomersBooked();
    }
}
