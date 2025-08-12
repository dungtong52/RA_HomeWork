package com.ra.controller;

import com.ra.model.Customer;
import com.ra.model.PaginationResult;
import com.ra.model.CustomerRole;
import com.ra.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String initSignup(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        model.addAttribute("roles", CustomerRole.values());
        return "register";
    }

    @PostMapping
    public String signup(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roles", CustomerRole.values());
            return "register";
        }
        boolean registerSuccess = customerService.saveCustomer(customer);
        if (registerSuccess) {
            model.addAttribute("successMsg", "Đăng ký thành công");
            return "signin";
        } else {
            model.addAttribute("errorMsg", "Đăng ký thất bại");
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("customer", new Customer());
        return "signin";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("customer") Customer customer, Model model, HttpSession session) {
        Customer customerLogin = customerService.isLogin(customer.getEmail(), customer.getPassword());
        if (customerLogin == null) {
            model.addAttribute("error", "Sai thông tin đăng nhập");
            return "signin";
        }
        session.setAttribute("loggedInUser", customerLogin);
        if(customerLogin.getRole() == CustomerRole.ADMIN){
            return "redirect:/room";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/customer")
    public String listCustomers(@RequestParam(name = "page", defaultValue = "1") int page,
                                @RequestParam(name = "size", defaultValue = "10") int size,
                                Model model) {
        PaginationResult<Customer> pagination = customerService.getPaginationData(null, size, page);

        model.addAttribute("customers", pagination.getDataList());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pagination.getTotalPages());
        model.addAttribute("pageSize", size);

        return "customer/list";
    }
}
