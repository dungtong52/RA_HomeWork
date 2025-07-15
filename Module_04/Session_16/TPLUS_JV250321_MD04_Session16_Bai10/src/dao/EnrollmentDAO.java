package dao;

import entity.Enrollment;

import java.util.List;

public interface EnrollmentDAO {
    boolean updateEnrollment(int studentId, List<Enrollment> enrollments);
}
