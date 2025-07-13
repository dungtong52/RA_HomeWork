package dao;

import entity.Enrollment;

import java.util.List;

public interface EnrollmentDAO {
    boolean enrollStudent(int studentId, int courseId);
    boolean updateGrade(int studentId, int courseId, double grade);
    List<Enrollment> getAllEnrollments();
}
