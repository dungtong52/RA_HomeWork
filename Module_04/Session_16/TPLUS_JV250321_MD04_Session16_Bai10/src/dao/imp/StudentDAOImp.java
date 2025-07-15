package dao.imp;

import dao.StudentDAO;
import entity.Student;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImp implements StudentDAO {
    @Override
    public List<Student> getAllStudents() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Student> students = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_all_students()}");
            ResultSet resultSet = callableStatement.executeQuery();
            students = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                students.add(student);
            }

            connection.commit();
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách sinh viên: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return students;
    }

    @Override
    public boolean addStudent(Student student) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();

            callableStatement = connection.prepareCall("{call add_student(?, ?)}");
            callableStatement.setString(1, student.getName());
            callableStatement.setString(2, student.getEmail());

            int rows = callableStatement.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm sinh viên: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        boolean exists = false;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call check_student_exists_by_email(?, ?)}");
            callableStatement.setString(1, email);
            callableStatement.registerOutParameter(2, java.sql.Types.BOOLEAN);
            callableStatement.execute();
            exists = callableStatement.getBoolean(2);
            connection.commit();
        } catch (Exception e) {
            System.err.println("Lỗi khi kiểm tra email sinh viên: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return exists;
    }


}
