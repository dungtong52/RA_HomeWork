package com.ra.controller;

import com.ra.model.Customer;
import com.ra.model.Room;
import com.ra.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/statistic")
    public String statistical(Model model, HttpSession session) {
        int customerCount = adminService.countCustomers();
        double revenue = adminService.getRevenueCurrentMonth();
        List<Room> topRooms = adminService.getTop3RoomsBooked();
        List<Customer> topCustomers = adminService.getTop5CustomersBooked();

        model.addAttribute("customerCount", customerCount);
        model.addAttribute("monthlyRevenue", revenue);
        model.addAttribute("topRooms", topRooms);
        model.addAttribute("topCustomers", topCustomers);

        return "statistic";
    }
}
