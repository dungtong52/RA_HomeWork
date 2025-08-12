package com.ra.service;


import com.ra.model.Customer;
import com.ra.model.Room;

import java.util.List;

public interface AdminService {
    int countCustomers();

    double getRevenueCurrentMonth();

    List<Room> getTop3RoomsBooked();

    List<Customer> getTop5CustomersBooked();
}
