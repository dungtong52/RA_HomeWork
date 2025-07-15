package dao.imp;

import dao.CourseDAO;
import entity.Course;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

public class CourseDAOImp implements CourseDAO {
    @Override
    public boolean addCourse(Course course) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        boolean result = false;

        try {
            connection = ConnectionDB.openConnection();

            callableStatement = connection.prepareCall("{call add_course(?)}");
            callableStatement.setString(1, course.getTitle());

            int rows = callableStatement.executeUpdate();
            result = rows > 0;

        } catch (Exception e) {
            System.err.println("Lỗi thêm khóa học: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return result;
    }

    @Override
    public boolean existsByTitle(String title) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        boolean exists = false;

        try {
            connection = ConnectionDB.openConnection();
            connection.setAutoCommit(false);

            callableStatement = connection.prepareCall("{call check_course_exists_by_title(?, ?)}");
            callableStatement.setString(1, title);
            callableStatement.registerOutParameter(2, Types.BOOLEAN);

            callableStatement.execute();
            exists = callableStatement.getBoolean(2);

            connection.commit();
        } catch (Exception e) {
            System.err.println("Lỗi kiểm tra khóa học tồn tại: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return exists;
    }
}
