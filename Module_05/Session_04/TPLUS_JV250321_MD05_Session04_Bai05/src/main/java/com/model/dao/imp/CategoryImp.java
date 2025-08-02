package com.model.dao.imp;

import com.model.dao.CategoryDAO;
import com.model.entity.Category;
import com.utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryImp implements CategoryDAO {
    @Override
    public List<Category> getAllCategories() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Category> categoryList = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_all_categories()}");
            ResultSet resultSet = callableStatement.executeQuery();
            categoryList = new ArrayList<>();
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setCateName(resultSet.getString("cate_name"));
                category.setDescription(resultSet.getString("description"));
                category.setStatus(resultSet.getBoolean("status"));
                categoryList.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return categoryList;
    }

    @Override
    public Category getCategoryById(int id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Category category = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_category_by_id(?)}");
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setCateName(resultSet.getString("cate_name"));
                category.setDescription(resultSet.getString("description"));
                category.setStatus(resultSet.getBoolean("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return category;
    }

    @Override
    public boolean createCategory(Category category) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call create_category(?,?,?)}");
            callableStatement.setString(1, category.getCateName());
            callableStatement.setString(2, category.getDescription());
            callableStatement.setBoolean(3, category.isStatus());
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
    public boolean updateCategory(Category category) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call update_category(?,?,?,?)}");
            callableStatement.setInt(1, category.getId());
            callableStatement.setString(2, category.getCateName());
            callableStatement.setString(3, category.getDescription());
            callableStatement.setBoolean(4, category.isStatus());
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
    public boolean deleteCategory(int id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call delete_category(?)}");
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
