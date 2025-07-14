package dao.imp;

import dao.StudentDAO;
import entity.Student;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDAOImp implements StudentDAO {
    @Override
    public boolean addListStudent(List<Student> studentList) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            connection.setAutoCommit(false);
            callableStatement = connection.prepareCall("{call add_student(?, ?)}");
            for (Student student : studentList) {
                callableStatement.setString(1, student.getName());
                callableStatement.setInt(2, student.getAge());
                callableStatement.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            try {
                if (connection != null) connection.rollback();
                System.err.println("Lỗi khi thêm sinh viên: " + e.getMessage());
            } catch (SQLException ex) {
                System.err.println("Lỗi rollback: " + ex.getMessage());
            }
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public Student findStudentById(int id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Student student = null;
        try {
            connection = ConnectionDB.openConnection();
            connection.setAutoCommit(false);
            callableStatement = connection.prepareCall("{call find_student_by_id(?)}");
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            student = new Student();
            if (resultSet.next()) {
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
            }
            connection.commit();
        } catch (Exception e) {
            try {
                if (connection != null) connection.rollback();
                System.err.println("Lỗi khi tìm sinh viên theo ID: " + e.getMessage());
            } catch (SQLException ex) {
                System.err.println("Lỗi rollback: " + ex.getMessage());
            }
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return student;
    }

    @Override
    public boolean updateStudent(Student student) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            connection.setAutoCommit(false);
            callableStatement = connection.prepareCall("{call update_student(?, ?, ?)}");
            callableStatement.setInt(1, student.getId());
            callableStatement.setString(2, student.getName());
            callableStatement.setInt(3, student.getAge());
            callableStatement.executeUpdate();
            connection.commit();
            return true;
        } catch (Exception e) {
            try {
                if (connection != null) connection.rollback();
                System.err.println("Lỗi khi update sinh viên: " + e.getMessage());
            } catch (SQLException ex) {
                System.err.println("Lỗi rollback: " + ex.getMessage());
            }
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public int deleteListStudentUnderAge(int age) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        int rows = 0;
        try {
            connection = ConnectionDB.openConnection();
            connection.setAutoCommit(false);
            callableStatement = connection.prepareCall("{call delete_students_by_age(?)}");
            callableStatement.setInt(1, age);
            rows = callableStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            try {
                if (connection != null) connection.rollback();
                System.err.println("Lỗi khi xóa sinh viên: " + e.getMessage());
            } catch (SQLException ex) {
                System.err.println("Lỗi rollback: " + ex.getMessage());
            }
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return rows;
    }
}
