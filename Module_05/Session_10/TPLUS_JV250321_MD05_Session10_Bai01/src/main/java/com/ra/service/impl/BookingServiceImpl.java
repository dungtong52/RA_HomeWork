package com.ra.service.impl;

import com.ra.model.Booking;
import com.ra.repository.BookingRepository;
import com.ra.service.BookingService;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public boolean addBooking(Booking booking) {
        return bookingRepository.addBooking(booking);
    }
}
