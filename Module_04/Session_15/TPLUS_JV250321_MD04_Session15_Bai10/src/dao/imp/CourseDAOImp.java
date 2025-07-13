package dao.imp;

import dao.CourseDAO;
import entity.Course;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImp implements CourseDAO {
    @Override
    public boolean addCourse(Course course) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sp_add_course(?, ?)}");
            callSt.setString(1, course.getTitle());
            callSt.setInt(2, course.getCredits());
            return callSt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi thêm khóa học: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public Course findByTitle(String title) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        Course course = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sp_find_course_by_title(?)}");
            callSt.setString(1, title);
            rs = callSt.executeQuery();
            if (rs.next()) {
                course = new Course();
                course.setId(rs.getInt("id"));
                course.setTitle(rs.getString("title"));
                course.setCredits(rs.getInt("credits"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi tìm khóa học theo tiêu đề: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return course;
    }

    @Override
    public Course findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        Course course = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sp_find_course_by_id(?)}");
            callSt.setInt(1, id);
            rs = callSt.executeQuery();
            if (rs.next()) {
                course = new Course();
                course.setId(rs.getInt("id"));
                course.setTitle(rs.getString("title"));
                course.setCredits(rs.getInt("credits"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi tìm khóa học theo ID: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return course;
    }

    @Override
    public List<Course> getAllCourses() {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Course> list = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sp_get_all_courses()}");
            rs = callSt.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setId(rs.getInt("id"));
                c.setTitle(rs.getString("title"));
                c.setCredits(rs.getInt("credits"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi lấy danh sách khóa học: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }
}
