package com.example.demo.repository;

import com.example.demo.model.Product;
import com.example.demo.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImp implements IProductRepository {
    @Override
    public List<Product> getAllProducts() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Product> productList = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_all_product()}");
            ResultSet resultSet = callableStatement.executeQuery();
            productList = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setPrice(resultSet.getFloat("price"));
                product.setDescription(resultSet.getString("description"));
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return productList;
    }

    @Override
    public Product getProductById(int id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Product product = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_product_by_id(?)}");
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setPrice(resultSet.getFloat("price"));
                product.setDescription(resultSet.getString("description"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return product;
    }

    @Override
    public boolean addProduct(Product product) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call create_product(?,?,?,?)}");
            callableStatement.setString(1, product.getProductName());
            callableStatement.setInt(2, product.getQuantity());
            callableStatement.setDouble(3, product.getPrice());
            callableStatement.setString(4, product.getDescription());
            int rows = callableStatement.executeUpdate();
            if (rows > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call update_product(?,?,?,?,?)}");
            callableStatement.setInt(1, product.getId());
            callableStatement.setString(2, product.getProductName());
            callableStatement.setInt(3, product.getQuantity());
            callableStatement.setDouble(4, product.getPrice());
            callableStatement.setString(5, product.getDescription());
            int rows = callableStatement.executeUpdate();
            if (rows > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call delete_product(?)}");
            callableStatement.setInt(1, id);
            int rows = callableStatement.executeUpdate();
            if (rows > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }
}
