package business.imp;

import business.EnrollmentBusiness;
import dao.CourseDAO;
import dao.EnrollmentDAO;
import dao.StudentDAO;
import dao.imp.CourseDAOImp;
import dao.imp.EnrollmentDAOImp;
import dao.imp.StudentDAOImp;
import entity.Enrollment;

import java.util.Scanner;

public class EnrollmentBusinessImp implements EnrollmentBusiness {
    private final EnrollmentDAO enrollmentDAO = new EnrollmentDAOImp();
    private final StudentDAO studentDAO = new StudentDAOImp();
    private final CourseDAO courseDAO = new CourseDAOImp();

    @Override
    public void enrollStudent(Scanner scanner) {
        Enrollment e = new Enrollment();
        e.inputEnrollInfo(scanner);

        if (studentDAO.findById(e.getStudentId()) == null) {
            System.err.println("Không tồn tại sinh viên ID " + e.getStudentId());
            return;
        }

        if (courseDAO.findById(e.getCourseId()) == null) {
            System.err.println("Không tồn tại khóa học ID " + e.getCourseId());
            return;
        }

        boolean result = enrollmentDAO.enrollStudent(e.getStudentId(), e.getCourseId());
        if (result) {
            System.out.println("Ghi danh thành công.");
        } else {
            System.err.println("Ghi danh thất bại.");
        }
    }

    @Override
    public void updateGrade(Scanner scanner) {
        Enrollment e = new Enrollment();
        e.inputEnrollInfo(scanner);
        e.inputGrade(scanner);

        boolean result = enrollmentDAO.updateGrade(e.getStudentId(), e.getCourseId(), e.getGrade());
        if (result) {
            System.out.println("Cập nhật điểm thành công.");
        } else {
            System.err.println("Cập nhật điểm thất bại.");
        }
    }

}
