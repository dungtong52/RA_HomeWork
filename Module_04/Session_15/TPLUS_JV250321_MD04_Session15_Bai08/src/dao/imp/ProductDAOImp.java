package dao.imp;

import dao.ProductDAO;
import entity.Product;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class ProductDAOImp implements ProductDAO {

    @Override
    public boolean addProduct(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_product(?, ?)}");
            callSt.setString(1, product.getName());
            callSt.setDouble(2, product.getPrice());

            int rows = callSt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public Product findByName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        Product product = null;

        try {
            conn = util.ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_product_by_name(?)}");
            callSt.setString(1, name);
            rs = callSt.executeQuery();

            if (rs.next()) {
                product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return product;
    }
}
