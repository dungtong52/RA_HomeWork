package dao.imp;

import dao.EnrollmentDAO;
import entity.Enrollment;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImp implements EnrollmentDAO {
    @Override
    public boolean enrollStudent(int studentId, int courseId) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sp_enroll_student(?, ?)}");
            callSt.setInt(1, studentId);
            callSt.setInt(2, courseId);
            return callSt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi ghi danh sinh viên: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public boolean updateGrade(int studentId, int courseId, double grade) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sp_update_grade(?, ?, ?)}");
            callSt.setInt(1, studentId);
            callSt.setInt(2, courseId);
            callSt.setDouble(3, grade);
            return callSt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi cập nhật điểm: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Enrollment> list = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sp_get_all_enrollments()}");
            rs = callSt.executeQuery();
            while (rs.next()) {
                Enrollment e = new Enrollment();
                e.setGrade(rs.getDouble("grade"));
                System.out.println("Sinh viên: " + rs.getString("student_name")
                        + " - Khóa học: " + rs.getString("course_title")
                        + " - Điểm: " + (rs.getObject("grade") == null ? "Chưa có" : rs.getDouble("grade")));
                list.add(e);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi hiển thị danh sách ghi danh: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }
}
