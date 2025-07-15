package business.imp;

import business.EnrollmentBusiness;
import dao.EnrollmentDAO;
import dao.imp.EnrollmentDAOImp;
import entity.Enrollment;

import java.util.List;

public class EnrollmentBusinessImp implements EnrollmentBusiness {
    private final EnrollmentDAO enrollmentDAO = new EnrollmentDAOImp();

    @Override
    public boolean updateEnrollment(int studentId, List<Enrollment> enrollments) {
        return enrollmentDAO.updateEnrollment(studentId, enrollments);
    }
}
