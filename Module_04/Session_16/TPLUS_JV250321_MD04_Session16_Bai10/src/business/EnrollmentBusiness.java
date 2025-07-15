package business;

import entity.Enrollment;

import java.util.List;

public interface EnrollmentBusiness {
    boolean updateEnrollment(int studentId, List<Enrollment> enrollments);
}
