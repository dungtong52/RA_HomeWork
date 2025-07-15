package dao.imp;

import dao.EnrollmentDAO;
import entity.Enrollment;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

public class EnrollmentDAOImp implements EnrollmentDAO {
    @Override
    public boolean updateEnrollment(int studentId, List<Enrollment> enrollments) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        boolean success = true;

        try {
            connection = ConnectionDB.openConnection();
            connection.setAutoCommit(false);

            for (Enrollment enrollment : enrollments) {
                callableStatement = connection.prepareCall("{call update_enrollment_grade(?, ?, ?)}");
                callableStatement.setInt(1, studentId);
                callableStatement.setInt(2, enrollment.getCourseId());
                callableStatement.setDouble(3, enrollment.getGrade());

                callableStatement.executeUpdate();
            }

            connection.commit();
        } catch (Exception e) {
            System.err.println("Lỗi kiểm tra cập nhật điểm: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }

        return success;
    }
}
