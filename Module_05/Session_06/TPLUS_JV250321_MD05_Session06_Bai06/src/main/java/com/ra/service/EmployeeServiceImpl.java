package com.ra.service;

import com.ra.model.Employee;
import com.ra.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.getAllEmployee();
    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeRepository.getEmployeeById(id);
    }

    @Override
    public boolean saveEmployee(Employee employee) {
        return employeeRepository.saveEmployee(employee);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return employeeRepository.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployee(long id) {
        return employeeRepository.deleteEmployee(id);
    }
}
