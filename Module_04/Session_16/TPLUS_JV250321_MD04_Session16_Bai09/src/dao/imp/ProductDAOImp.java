package dao.imp;

import dao.ProductDAO;
import entity.Product;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImp implements ProductDAO {
    @Override
    public boolean updateProductStock(int productId, int quantity) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call update_product_stock(?,?)}");
            callableStatement.setInt(1, productId);
            callableStatement.setInt(2, quantity);
            callableStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.getMessage();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public List<Product> getAllProduct() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Product> productList = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_all_products}");
            ResultSet resultSet = callableStatement.executeQuery();
            productList = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getFloat("price"));
                product.setStockQuantity(resultSet.getInt("stock_quantity"));
                productList.add(product);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return productList;
    }

    @Override
    public boolean checkStock(int productId, int quantity) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call check_stock(?,?)}");
            callableStatement.setInt(1, productId);
            callableStatement.setInt(2, quantity);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }
}
