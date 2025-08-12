package com.ra.controller;

import com.ra.model.*;
import com.ra.service.BookingService;
import com.ra.service.CustomerService;
import com.ra.service.RoomService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Controller
@RequestMapping("/home")
public class HomeController {
    private final RoomService roomService;
    private final BookingService bookingService;

    public HomeController(RoomService roomService, CustomerService customerService, BookingService bookingService) {
        this.roomService = roomService;
        this.bookingService = bookingService;
    }

    @GetMapping
    public String home(Model model) {
        PaginationResult<Room> pagination = roomService.getPaginationData(null, 10, 1);

        model.addAttribute("rooms", pagination.getDataList());
        return "home";
    }

    @GetMapping("/bookings")
    public String showBookingForm(@RequestParam("roomId") Long roomId, Model model, HttpSession session) {
        Customer loggedInCustomer = (Customer) session.getAttribute("loggedInUser");

        if (loggedInCustomer == null) {
            return "redirect:/signin";
        }

        Room room = roomService.findById(roomId);
        if (room == null || room.getStatus() == RoomStatus.PLACED) {
            model.addAttribute("errorMsg", "Phòng không hợp lệ hoặc đã được đặt.");
            return "redirect:/home";
        }

        Booking booking = new Booking();
        booking.setRoomId(roomId);
        booking.setCustomerId(loggedInCustomer.getId());
        booking.setBookingDate(LocalDate.now());

        model.addAttribute("booking", booking);
        model.addAttribute("customer", loggedInCustomer);
        model.addAttribute("room", room);
        System.out.println(147);
        return "bookRoom";
    }

    @PostMapping("/bookings")
    public String processBooking(@ModelAttribute("booking") Booking booking,
                                 Model model,
                                 HttpSession session) {

        Customer loggedInCustomer = (Customer) session.getAttribute("loggedInUser");
        if (loggedInCustomer == null) {
            return "redirect:/signin";
        }

        booking.setCustomerId(loggedInCustomer.getId());

        boolean success = bookingService.addBooking(booking);
        if (success) {
            roomService.updateRoomStatus(booking.getRoomId(), RoomStatus.PLACED.name());
            System.out.println(123);
            return "redirect:/home?bookingSuccess";
        } else {
            model.addAttribute("errorMsg", "Đặt phòng thất bại, vui lòng thử lại.");
            Room room = roomService.findById(booking.getRoomId());
            model.addAttribute("customer", loggedInCustomer);
            model.addAttribute("room", room);
            System.out.println(456);
            return "bookRoom";
        }
    }
}
