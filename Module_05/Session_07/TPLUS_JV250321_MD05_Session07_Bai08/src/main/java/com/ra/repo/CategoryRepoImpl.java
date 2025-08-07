package com.ra.repo;

import com.ra.model.Category;
import com.ra.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepoImpl implements CategoryRepo{
    @Override
    public List<Category> getAllCategory() {
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
                category.setCategoryName(resultSet.getString("category_name"));
                category.setDescription(resultSet.getString("description"));
                category.setStatus(resultSet.getBoolean("status"));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return categoryList;
    }

    @Override
    public boolean checkExistCategoryName(String name) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call check_exist_category_name(?)}");
            callableStatement.setString(1, name);
            ResultSet resultSet = callableStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public boolean saveCategory(Category category) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call create_category(?, ?, ?)}");
            callableStatement.setString(1, category.getCategoryName());
            callableStatement.setString(2, category.getDescription());
            callableStatement.setBoolean(3, category.isStatus());
            callableStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
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
            callableStatement = connection.prepareCall("{call update_category(?, ?, ?, ?)}");
            callableStatement.setInt(1, category.getId());
            callableStatement.setString(2, category.getCategoryName());
            callableStatement.setString(3, category.getDescription());
            callableStatement.setBoolean(4, category.isStatus());
            callableStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
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
            callableStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
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
                category.setCategoryName(resultSet.getString("category_name"));
                category.setDescription(resultSet.getString("description"));
                category.setStatus(resultSet.getBoolean("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return category;
    }
}
