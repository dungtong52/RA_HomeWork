package com.ra.controller;

import com.ra.model.Employee;
import com.ra.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/employee"})
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String listEmployee(Model model) {
        List<Employee> employeeList = employeeService.getAllEmployee();
        model.addAttribute("employees", employeeList);
        return "employee-list";
    }

    @GetMapping("/add")
    public String showEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String updateEmployee(@PathVariable("id") long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return "redirect:/";
        }
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }


}
