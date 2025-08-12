package com.ra.repository;

import com.ra.model.Customer;
import com.ra.model.Room;

import java.util.List;

public interface AdminRepository {
    int countCustomers();

    double getRevenueCurrentMonth();

    List<Room> getTop3RoomsBooked();

    List<Customer> getTop5CustomersBooked();
}
