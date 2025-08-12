package com.ra.repository.impl;

import com.ra.model.Customer;
import com.ra.model.PaginationResult;
import com.ra.model.CustomerRole;
import com.ra.repository.CustomerRepository;
import com.ra.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public Customer isLogin(String input, String password) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Customer customer = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call is_login(?,?)}");
            callableStatement.setString(1, input);
            callableStatement.setString(2, password);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setFullName(resultSet.getString("full_name"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setEmail(resultSet.getString("email"));
                customer.setAddress(resultSet.getString("address"));
                customer.setRole(CustomerRole.valueOf(resultSet.getString("role")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return customer;
    }

    @Override
    public PaginationResult<Customer> getAllCustomer(int size, int currentPage) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        PaginationResult<Customer> customerPaginationResult = new PaginationResult<>();
        List<Customer> customerList = new ArrayList<>();
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_all_customers(?,?,?)}");
            callableStatement.setInt(1, size);
            callableStatement.setInt(2, currentPage);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setFullName(resultSet.getString("full_name"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setEmail(resultSet.getString("email"));
                customer.setAddress(resultSet.getString("address"));
                customer.setRole(CustomerRole.valueOf(resultSet.getString("role")));
                customerList.add(customer);
            }
            customerPaginationResult.setTotalPages(callableStatement.getInt(3));
            customerPaginationResult.setDataList(customerList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return customerPaginationResult;
    }

    @Override
    public boolean isPhoneNumberUnique(long id, String phoneNumber) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call is_phone_number_unique(?,?)}");
            callableStatement.setLong(1, id);
            callableStatement.setString(2, phoneNumber);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("cnt");
                return count == 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public boolean isEmailUnique(long id, String email) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call is_email_unique(?,?)}");
            callableStatement.setLong(1, id);
            callableStatement.setString(2, email);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("cnt");
                return count == 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public boolean saveCustomer(Customer customer) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call save_customer(?,?,?,?,?,?)}");
            callableStatement.setString(1, customer.getFullName());
            callableStatement.setString(2, customer.getPhoneNumber());
            callableStatement.setString(3, customer.getEmail());
            callableStatement.setString(4, customer.getPassword());
            callableStatement.setString(5, customer.getAddress());
            callableStatement.setString(6, String.valueOf(customer.getRole()));
            int rows = callableStatement.executeUpdate();
            if (rows > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }
}
